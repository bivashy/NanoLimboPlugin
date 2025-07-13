package ua.nanit.limbo.protocol;

import net.kyori.adventure.nbt.CompoundBinaryTag;

public class NbtMessage {

    private String json;
    private CompoundBinaryTag tag;

    public NbtMessage(String json, CompoundBinaryTag tag) {
        this.json = json;
        this.tag = tag;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public CompoundBinaryTag getTag() {
        return tag;
    }

    public void setTag(CompoundBinaryTag tag) {
        this.tag = tag;
    }
}
