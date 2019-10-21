package adiitya.tictactoe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class DesktopLauncher {

	public static void main(String[] args) {

		if (args.length > 0 && shouldPack(args)) {

			Settings settings = new Settings();
			settings.useIndexes = true;
			TexturePacker.process(settings, "../textures", "../assets", "game");
		} else {
			System.out.println("Skipping packing...");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 500;
		config.resizable = false;
		config.backgroundFPS = 5;
		config.title = "Tic Tac Toe";

		new LwjglApplication(new TicTacToe(), config);
	}

	private static boolean shouldPack(String[] args) {

		for (String arg : args)
			if (arg.equals("-p"))
				return true;

		return false;
	}
}
