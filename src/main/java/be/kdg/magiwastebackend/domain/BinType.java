package be.kdg.magiwastebackend.domain;

public enum BinType {
    TALL_BIN(106),
    STANDARD_BIN(78),
    SHORT_BIN(56);

    private final double height;

    BinType(final int height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }
}
