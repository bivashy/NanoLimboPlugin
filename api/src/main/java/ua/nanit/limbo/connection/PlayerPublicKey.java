package ua.nanit.limbo.connection;

public class PlayerPublicKey {

    private final long expiry;
    private final byte[] key;
    private final byte[] signature;

    public PlayerPublicKey(long expiry, byte[] key, byte[] signature) {
        this.expiry = expiry;
        this.key = key;
        this.signature = signature;
    }

    public long getExpiry() {
        return expiry;
    }

    public byte[] getKey() {
        return key;
    }

    public byte[] getSignature() {
        return signature;
    }
}

