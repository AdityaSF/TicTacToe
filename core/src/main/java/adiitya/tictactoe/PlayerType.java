package adiitya.tictactoe;

public enum PlayerType {

	X("x1"),
	O("o1"),
	NONE("");

	String animationName;

	PlayerType(String animationName) {

		this.animationName = animationName;
	}

	public static PlayerType flip(PlayerType type) {
		return type == X ? O : X;
	}
}
