package adiitya.tictactoe;

public enum PlayerType {

	X("player_x", "x1"),
	O("player_o", "o1");

	public String textureName;
	public String animationName;

	PlayerType(String textureName, String animationName) {
		this.textureName = textureName;
		this.animationName = animationName;
	}

	public static PlayerType flip(PlayerType type) {
		return type == X ? O : X;
	}
}
