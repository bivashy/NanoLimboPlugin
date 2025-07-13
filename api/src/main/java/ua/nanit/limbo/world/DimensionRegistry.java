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
package ua.nanit.limbo.world;

import java.io.IOException;
import java.io.InputStream;

import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import ua.nanit.limbo.server.Log;

public final class DimensionRegistry {

    private final ClassLoader classLoader;
    private Dimension defaultDimension_1_16;
    private Dimension defaultDimension_1_16_2;
    private Dimension defaultDimension_1_17;
    private Dimension defaultDimension_1_18_2;
    private Dimension dimension_1_20_5;
    private Dimension dimension_1_21;
    private Dimension dimension_1_21_2;
    private Dimension dimension_1_21_4;
    private Dimension dimension_1_21_5;
    private Dimension dimension_1_21_6;

    private CompoundBinaryTag codec_1_16;
    private CompoundBinaryTag codec_1_16_2;
    private CompoundBinaryTag codec_1_17;
    private CompoundBinaryTag codec_1_18_2;
    private CompoundBinaryTag codec_1_19;
    private CompoundBinaryTag codec_1_19_1;
    private CompoundBinaryTag codec_1_19_4;
    private CompoundBinaryTag codec_1_20;
    private CompoundBinaryTag codec_1_20_5;
    private CompoundBinaryTag codec_1_21;
    private CompoundBinaryTag codec_1_21_2;
    private CompoundBinaryTag codec_1_21_4;
    private CompoundBinaryTag codec_1_21_5;
    private CompoundBinaryTag codec_1_21_6;
    private CompoundBinaryTag codec_1_21_7;

    private CompoundBinaryTag tags_1_20_5;
    private CompoundBinaryTag tags_1_21;
    private CompoundBinaryTag tags_1_21_2;
    private CompoundBinaryTag tags_1_21_4;
    private CompoundBinaryTag tags_1_21_5;
    private CompoundBinaryTag tags_1_21_6;
    private CompoundBinaryTag tags_1_21_7;

    public DimensionRegistry(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public CompoundBinaryTag getCodec_1_16() {
        return codec_1_16;
    }

    public CompoundBinaryTag getCodec_1_16_2() {
        return codec_1_16_2;
    }

    public CompoundBinaryTag getCodec_1_17() {
        return codec_1_17;
    }

    public CompoundBinaryTag getCodec_1_18_2() {
        return codec_1_18_2;
    }

    public CompoundBinaryTag getCodec_1_19() {
        return codec_1_19;
    }

    public CompoundBinaryTag getCodec_1_19_1() {
        return codec_1_19_1;
    }

    public CompoundBinaryTag getCodec_1_19_4() {
        return codec_1_19_4;
    }

    public CompoundBinaryTag getCodec_1_20() {
        return codec_1_20;
    }

    public CompoundBinaryTag getCodec_1_20_5() {
        return codec_1_20_5;
    }

    public CompoundBinaryTag getCodec_1_21() {
        return codec_1_21;
    }

    public CompoundBinaryTag getCodec_1_21_2() {
        return codec_1_21_2;
    }

    public CompoundBinaryTag getCodec_1_21_4() {
        return codec_1_21_4;
    }

    public CompoundBinaryTag getCodec_1_21_5() {
        return codec_1_21_5;
    }

    public CompoundBinaryTag getCodec_1_21_6() {
        return codec_1_21_6;
    }

    public CompoundBinaryTag getCodec_1_21_7() {
        return codec_1_21_7;
    }

    public Dimension getDefaultDimension_1_16() {
        return defaultDimension_1_16;
    }

    public Dimension getDefaultDimension_1_16_2() {
        return defaultDimension_1_16_2;
    }

    public Dimension getDefaultDimension_1_17() {
        return defaultDimension_1_17;
    }

    public Dimension getDefaultDimension_1_18_2() {
        return defaultDimension_1_18_2;
    }

    public Dimension getDimension_1_20_5() {
        return dimension_1_20_5;
    }

    public Dimension getDimension_1_21() {
        return dimension_1_21;
    }

    public Dimension getDimension_1_21_2() {
        return dimension_1_21_2;
    }

    public Dimension getDimension_1_21_4() {
        return dimension_1_21_4;
    }

    public Dimension getDimension_1_21_5() {
        return dimension_1_21_5;
    }

    public Dimension getDimension_1_21_6() {
        return dimension_1_21_6;
    }

    public CompoundBinaryTag getTags_1_20_5() {
        return tags_1_20_5;
    }

    public CompoundBinaryTag getTags_1_21() {
        return tags_1_21;
    }

    public CompoundBinaryTag getTags_1_21_2() {
        return tags_1_21_2;
    }

    public CompoundBinaryTag getTags_1_21_4() {
        return tags_1_21_4;
    }

    public CompoundBinaryTag getTags_1_21_5() {
        return tags_1_21_5;
    }

    public CompoundBinaryTag getTags_1_21_6() {
        return tags_1_21_6;
    }

    public CompoundBinaryTag getTags_1_21_7() {
        return tags_1_21_7;
    }

    public void load(String def) throws IOException {
        codec_1_16 = readCompoundBinaryTag("dimension/codec_1_16.nbt");
        codec_1_16_2 = readCompoundBinaryTag("dimension/codec_1_16_2.nbt");
        codec_1_17 = readCompoundBinaryTag("dimension/codec_1_17.nbt");
        codec_1_18_2 = readCompoundBinaryTag("dimension/codec_1_18_2.nbt");
        codec_1_19 = readCompoundBinaryTag("dimension/codec_1_19.nbt");
        codec_1_19_1 = readCompoundBinaryTag("dimension/codec_1_19_1.nbt");
        codec_1_19_4 = readCompoundBinaryTag("dimension/codec_1_19_4.nbt");
        codec_1_20 = readCompoundBinaryTag("dimension/codec_1_20.nbt");
        codec_1_20_5 = readCompoundBinaryTag("dimension/codec_1_20_5.nbt");
        codec_1_21 = readCompoundBinaryTag("dimension/codec_1_21.nbt");
        codec_1_21_2 = readCompoundBinaryTag("dimension/codec_1_21_2.nbt");
        codec_1_21_4 = readCompoundBinaryTag("dimension/codec_1_21_4.nbt");
        codec_1_21_5 = readCompoundBinaryTag("dimension/codec_1_21_5.nbt");
        codec_1_21_6 = readCompoundBinaryTag("dimension/codec_1_21_6.nbt");
        codec_1_21_7 = readCompoundBinaryTag("dimension/codec_1_21_7.nbt");

        tags_1_20_5 = readCompoundBinaryTag("dimension/tags_1_20_5.nbt");
        tags_1_21 = readCompoundBinaryTag("dimension/tags_1_21.nbt");
        tags_1_21_2 = readCompoundBinaryTag("dimension/tags_1_21_2.nbt");
        tags_1_21_4 = readCompoundBinaryTag("dimension/tags_1_21_4.nbt");
        tags_1_21_5 = readCompoundBinaryTag("dimension/tags_1_21_5.nbt");
        tags_1_21_6 = readCompoundBinaryTag("dimension/tags_1_21_6.nbt");
        tags_1_21_7 = readCompoundBinaryTag("dimension/tags_1_21_7.nbt");

        defaultDimension_1_16 = getLegacyDimension(def);
        defaultDimension_1_16_2 = getModernDimension(def, codec_1_16_2);
        defaultDimension_1_17 = getModernDimension(def, codec_1_17);
        defaultDimension_1_18_2 = getModernDimension(def, codec_1_18_2);

        dimension_1_20_5 = getModernDimension(def, codec_1_20_5);
        dimension_1_21 = getModernDimension(def, codec_1_21);
        dimension_1_21_2 = getModernDimension(def, codec_1_21_2);
        dimension_1_21_4 = getModernDimension(def, codec_1_21_4);
        dimension_1_21_5 = getModernDimension(def, codec_1_21_5);
        dimension_1_21_6 = getModernDimension(def, codec_1_21_6);
    }

    private Dimension getLegacyDimension(String def) {
        switch (def) {
            case "minecraft:overworld": {
                return new Dimension(0, def, null);
            }
            case "minecraft:the_nether": {
                return new Dimension(-1, def, null);
            }
            case "minecraft:the_end": {
                return new Dimension(1, def, null);
            }
            default: {
                Log.warning("Undefined dimension type: '%s'. Using 'minecraft:overworld' as default", def);
                return new Dimension(0, "minecraft:overworld", null);
            }
        }
    }

    private Dimension getModernDimension(String def, CompoundBinaryTag tag) {
        ListBinaryTag dimensions = tag.getCompound("minecraft:dimension_type").getList("value");

        for (int i = 0; i < dimensions.size(); i++) {
            CompoundBinaryTag dimension = (CompoundBinaryTag) dimensions.get(i);

            String name = dimension.getString("name");
            CompoundBinaryTag world = (CompoundBinaryTag) dimension.get("element");

            if (name.startsWith(def)) {
                return new Dimension(i, name, world);
            }
        }

        CompoundBinaryTag overWorld = (CompoundBinaryTag) ((CompoundBinaryTag) dimensions.get(0)).get("element");
        Log.warning("Undefined dimension type: '%s'. Using 'minecraft:overworld' as default", def);
        return new Dimension(0, "minecraft:overworld", overWorld);
    }

    private CompoundBinaryTag readCompoundBinaryTag(String resPath) throws IOException {
        try (InputStream in = classLoader.getResourceAsStream(resPath)) {
            return BinaryTagIO.unlimitedReader().read(in, BinaryTagIO.Compression.GZIP);
        }
    }

}
