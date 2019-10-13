package adiitya.tictactoe;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class TicTacToeHtml extends GwtApplication {

	@Override
	public ApplicationListener createApplicationListener() {
		return new TicTacToe();
	}

	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
