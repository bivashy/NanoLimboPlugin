/*
 * Copyright (C) 2020 Nan1t
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ua.nanit.limbo.connection.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.util.ByteProcessor;

import java.util.List;

public class VarIntFrameDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (!ctx.channel().isActive()) {
            in.clear();
            return;
        }

        // Skip any runs of 0x00 we might find
        final int packetStart = in.forEachByte(ByteProcessor.FIND_NON_NUL);
        if (packetStart == -1) {
            in.clear();
            return;
        }
        in.readerIndex(packetStart);

        // Try to read the length of the packet
        in.markReaderIndex();
        final int preIndex = in.readerIndex();
        final int length = readRawVarInt21(in);
        if (preIndex == in.readerIndex()) {
            return;
        }
        if (length <= 0) {
            throw new DecoderException("Bad VarInt length: " + length);
        }

        if (in.readableBytes() < length) {
            in.resetReaderIndex();
        } else {
            out.add(in.readRetainedSlice(length));
        }
    }

    private static int readRawVarInt21(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() < 4) {
            // we don't have enough that we can read a potentially full varint, so fall back to
            // the slow path.
            return readRawVarintSmallBuf(byteBuf);
        }
        int wholeOrMore = byteBuf.getIntLE(byteBuf.readerIndex());

        // take the last three bytes and check if any of them have the high bit set
        int atStop = ~wholeOrMore & 0x808080;
        if (atStop == 0) {
            // all bytes have the high bit set, so the varint we are trying to decode is too wide
            throw new DecoderException("VarInt too big");
        }

        int bitsToKeep = Integer.numberOfTrailingZeros(atStop) + 1;
        byteBuf.skipBytes(bitsToKeep >> 3);

        // remove all bits we don't need to keep, a trick from
        // https://github.com/netty/netty/pull/14050#issuecomment-2107750734:
        //
        // > The idea is that thisVarintMask has 0s above the first one of firstOneOnStop, and 1s at
        // > and below it. For example if firstOneOnStop is 0x800080 (where the last 0x80 is the only
        // > one that matters), then thisVarintMask is 0xFF.
        //
        // this is also documented in Hacker's Delight, section 2-1 "Manipulating Rightmost Bits"
        int preservedBytes = wholeOrMore & (atStop ^ (atStop - 1));

        // merge together using this trick: https://github.com/netty/netty/pull/14050#discussion_r1597896639
        preservedBytes = (preservedBytes & 0x007F007F) | ((preservedBytes & 0x00007F00) >> 1);
        preservedBytes = (preservedBytes & 0x00003FFF) | ((preservedBytes & 0x3FFF0000) >> 2);
        return preservedBytes;
    }

    private static int readRawVarintSmallBuf(ByteBuf byteBuf) {
        if (!byteBuf.isReadable()) {
            return 0;
        }
        byteBuf.markReaderIndex();

        byte tmp = byteBuf.readByte();
        if (tmp >= 0) {
            return tmp;
        }
        int result = tmp & 0x7F;
        if (!byteBuf.isReadable()) {
            byteBuf.resetReaderIndex();
            return 0;
        }
        if ((tmp = byteBuf.readByte()) >= 0) {
            return result | tmp << 7;
        }
        result |= (tmp & 0x7F) << 7;
        if (!byteBuf.isReadable()) {
            byteBuf.resetReaderIndex();
            return 0;
        }
        if ((tmp = byteBuf.readByte()) >= 0) {
            return result | tmp << 14;
        }
        return result | (tmp & 0x7F) << 14;
    }
}