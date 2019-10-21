package adiitya.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class PlayerObject {

	private static final float SCALE = 500F / 64F;

	private final Animation<AtlasRegion> animation;
	private final PlayerType type;

	private float elapsed = 0F;

	private PlayerObject(Animation<AtlasRegion> animation, PlayerType type) {

		this.animation = animation;
		this.type = type;
	}


	public void render(SpriteBatch batch, float x, float y) {

		if (!batch.isDrawing()) return;

		elapsed = MathUtils.clamp(elapsed + Gdx.graphics.getDeltaTime(), 0, animation.getAnimationDuration());
		RenderUtils.renderAnimation(batch, animation, elapsed, x, y, SCALE, false);
	}

	public float getTextureWidth() {
		return animation.getKeyFrame(0F).getRegionWidth();
	}

	public float getTextureHeight() {
		return animation.getKeyFrame(0F).getRegionHeight();
	}

	public static PlayerObject fromPlayerType(PlayerType type) {

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("game.atlas"), true);
		Array<AtlasRegion> frames = atlas.findRegions(type.animationName);

		return new PlayerObject(new Animation<>(0.05F, frames), type);
	}
}
