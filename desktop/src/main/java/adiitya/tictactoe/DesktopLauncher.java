package adiitya.tictactoe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

	public static void main(String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 500;
		config.resizable = false;
		config.backgroundFPS = 5;
		config.title = "Tic Tac Toe";

		new LwjglApplication(new TicTacToe(), config);
	}
}
