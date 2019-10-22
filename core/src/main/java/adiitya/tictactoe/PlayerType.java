package adiitya.tictactoe;

public enum PlayerType {

	X("x1", 1),
	O("o1", -1),
	NONE("", 0);

	String animationName;
	public int score;

	PlayerType(String animationName, int score) {
		this.animationName = animationName;
		this.score = score;
	}

	public static PlayerType flip(PlayerType type) {
		return type == X ? O : X;
	}
}
