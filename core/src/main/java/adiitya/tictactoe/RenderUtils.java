package adiitya.tictactoe;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public final class RenderUtils {

	public static void renderFlipable(SpriteBatch batch, AtlasRegion texture, float x, float y, float width, float height, boolean flipX) {

		float xOff = flipX ? width * TicTacToe.SCALE : 0F;
		float xScaleModifier = flipX ? -1F : 1F;

		batch.draw(texture, x + xOff, y, width * TicTacToe.SCALE * xScaleModifier, height * TicTacToe.SCALE);
	}

	public static void renderAnimation(SpriteBatch batch, Animation<AtlasRegion> anim, float stateTime, float x, float y, boolean flip) {

		AtlasRegion frame = anim.getKeyFrame(stateTime);
		renderFlipable(batch, frame, x, y, frame.getRegionWidth(), frame.getRegionHeight(), flip);
	}
}
