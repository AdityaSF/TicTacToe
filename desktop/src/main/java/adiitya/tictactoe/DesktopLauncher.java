package adiitya.tictactoe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {

	public static void main (String[] args) {

		TexturePacker.process("../textures", "../assets", "game");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 500;
		config.resizable = false;
		config.backgroundFPS = 5;
		config.title = "Tic Tac Toe";

		new LwjglApplication(new TicTacToe(), config);
	}
}
