package adiitya.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;

public class PlayerObject {

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
		RenderUtils.renderAnimation(batch, animation, elapsed, x, y, false);
	}

	public PlayerType getType() {
		return type;
	}

	public float getTextureWidth() {
		return animation.getKeyFrame(0F).getRegionWidth();
	}

	public float getTextureHeight() {
		return animation.getKeyFrame(0F).getRegionHeight();
	}

	public static PlayerObject fromPlayerType(PlayerType type) {

		if (type == PlayerType.NONE)
			return null;

		return new PlayerObject(new Animation<>(0.05F, Resources.getAnimation(type.animationName)), type);
	}
}
