package adiitya.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.Objects;
import java.util.Optional;

public class Cell {

	private float x;
	private float y;
	private CellType cellType;

	private float clickElapsed = 0F;
	private boolean clicked = false;
	private Animation<AtlasRegion> clickAnimation = null;

	private float winElapsed = 0F;
	private Animation<AtlasRegion> winAnimation = null;

	private PlayerType playerType;

	public Cell(float x, float y, CellType cellType) {
		this(x, y, cellType, PlayerType.NONE);
	}

	public Cell(float x, float y, CellType cellType, PlayerType playerType) {
		this.x = x;
		this.y = y;
		this.cellType = Objects.requireNonNull(cellType);
		this.playerType = Optional.ofNullable(playerType).orElse(PlayerType.NONE);
	}

	public void render(SpriteBatch batch) {
		render(batch, x, y, cellType);
	}

	public void render(SpriteBatch batch, float x, float y, CellType type) {

		AtlasRegion emptyTexture = Resources.getTexture(type.getEmptyTextureName());
		batch.draw(emptyTexture, x, y, emptyTexture.getRegionWidth() * TicTacToe.SCALE, emptyTexture.getRegionHeight() * TicTacToe.SCALE);

		renderPlayer(batch);
	}

	private void renderPlayer(SpriteBatch batch) {

		if (clickAnimation != null) {

			clickElapsed = MathUtils.clamp(clickElapsed + Gdx.graphics.getDeltaTime(), 0, clickAnimation.getAnimationDuration());
			RenderUtils.renderAnimation(batch, clickAnimation, clickElapsed, x, y);
		}

		if (winAnimation != null) {

			winElapsed = MathUtils.clamp(winElapsed + Gdx.graphics.getDeltaTime(), 0, winAnimation.getAnimationDuration());
			RenderUtils.renderAnimation(batch, winAnimation, winElapsed, x, y);
		}
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = Optional.ofNullable(playerType).orElse(PlayerType.NONE);
	}

	public void win() {
		winAnimation = new Animation<>(0.1F, Resources.getAnimation(cellType.getWinAnimationName(playerType)));
	}

	private void click() {

		clicked = true;
		clickAnimation = new Animation<>(0.1F, Resources.getAnimation(cellType.getClickAnimationName(playerType)));
	}

	public boolean onClick(int x, int y, PlayerType playerType) {

		AtlasRegion texture = Resources.getTexture(cellType.getEmptyTextureName());
		Rectangle bounds = new Rectangle(this.x, this.y, texture.getRegionWidth() * TicTacToe.SCALE, texture.getRegionHeight() * TicTacToe.SCALE);

		if (bounds.contains(x, y) && !clicked) {

			setPlayerType(playerType);
			click();

			return true;
		}

		return false;
	}

	public PlayerType getOccupation() {
		return Optional.ofNullable(playerType)
				.orElse(PlayerType.NONE);
	}

}
