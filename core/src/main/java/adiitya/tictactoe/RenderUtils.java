package adiitya.tictactoe;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public final class RenderUtils {

	public static void renderAnimation(SpriteBatch batch, Animation<AtlasRegion> anim, float stateTime, float x, float y) {

		AtlasRegion frame = anim.getKeyFrame(stateTime);
		batch.draw(frame, x, y, frame.getRegionWidth() * TicTacToe.SCALE, frame.getRegionHeight() * TicTacToe.SCALE);
	}

	private RenderUtils() {
		throw new IllegalStateException("Cannot instantiate utility class. Use static methods instead.");
	}
}
