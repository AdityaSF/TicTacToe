package adiitya.tictactoe;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class RenderUtils {

	public static void renderFlipable(SpriteBatch batch, TextureRegion texture, float x, float y, float width, float height, float scale, boolean flipX) {

		if (!batch.isDrawing())
			return;

		float xOff = flipX ? width * scale : 0F;
		float xScaleModifier = flipX ? -1F : 1F;

		batch.draw(texture, x + xOff, y, width * scale * xScaleModifier, height * scale);
	}

	public static void renderFlipable(SpriteBatch batch, AtlasRegion texture, float x, float y, float width, float height, float scale, boolean flipX) {

		if (!batch.isDrawing())
			return;

		float xOff = flipX ? width * scale : 0F;
		float xScaleModifier = flipX ? -1F : 1F;

		batch.draw(texture, x + xOff, y, width * scale * xScaleModifier, height * scale);
	}

	public static void renderAnimation(SpriteBatch batch, Animation<AtlasRegion> anim, float stateTime, float x, float y, float scale, boolean flip) {

		AtlasRegion frame = anim.getKeyFrame(stateTime);
		renderFlipable(batch, frame, x, y, frame.getRegionWidth(), frame.getRegionHeight(), scale, flip);
	}
}
