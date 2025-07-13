package ua.nanit.limbo.protocol.packets.configuration;

import io.netty.handler.codec.DecoderException;
import ua.nanit.limbo.connection.ClientConnection;
import ua.nanit.limbo.protocol.ByteMessage;
import ua.nanit.limbo.protocol.PacketIn;
import ua.nanit.limbo.protocol.PacketOut;
import ua.nanit.limbo.protocol.registry.Version;
import ua.nanit.limbo.server.LimboServer;

import java.util.ArrayList;
import java.util.List;

public class PacketKnownPacks implements PacketIn, PacketOut {

    private List<KnownPack> knownPacks;

    public List<KnownPack> getKnownPacks() {
        return knownPacks;
    }

    public void setKnownPacks(List<KnownPack> knownPacks) {
        this.knownPacks = knownPacks;
    }

    @Override
    public void encode(ByteMessage msg, Version version) {
        msg.writeVarInt(this.knownPacks.size());
        for (KnownPack knownPack : this.knownPacks) {
            msg.writeString(knownPack.getNamespace());
            msg.writeString(knownPack.getId());
            msg.writeString(knownPack.getVersion());
        }
    }

    @Override
    public void decode(ByteMessage msg, Version version) {
        int size = msg.readVarInt();
        if (size > 16) {
            throw new DecoderException("Cannot receive known packs larger than 16");
        }
        List<KnownPack> knownPacks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String nameSpace = msg.readString(256);
            String id = msg.readString(256);
            String knownPackVersion = msg.readString(256);

            knownPacks.add(new KnownPack(nameSpace, id, knownPackVersion));
        }

        this.knownPacks = knownPacks;
    }

    @Override
    public void handle(ClientConnection conn, LimboServer server) {
        server.getPacketHandler().handle(conn, this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public static class KnownPack {
        private final String namespace;
        private final String id;
        private final String version;

        public KnownPack(String namespace, String id, String version) {
            this.namespace = namespace;
            this.id = id;
            this.version = version;
        }

        public String getNamespace() {
            return namespace;
        }

        public String getId() {
            return id;
        }

        public String getVersion() {
            return version;
        }
    }
}
