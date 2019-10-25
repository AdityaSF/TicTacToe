package adiitya.tictactoe;

public enum CellType {

    TOP_LEFT("TL"),
    TOP("CT"),
    TOP_RIGHT("TR"),
    LEFT("CL"),
    CENTER("C"),
    RIGHT("CR"),
    BOTTOM_LEFT("BL"),
    BOTTOM("CB"),
    BOTTOM_RIGHT("BR");

    private String textureName;

    CellType(String textureName) {
        this.textureName = textureName;
    }

    public String getEmptyTextureName() {
        return textureName;
    }

    public String getClickAnimationName(PlayerType type) {
        return String.format("%sClick%s", getEmptyTextureName(), type.name());
    }

    public String getWinAnimationName(PlayerType type) {
        return String.format("%sWin%s", getEmptyTextureName(), type.name());
    }

    public static CellType fromIndex(int index) {
        return values()[index];
    }
}
