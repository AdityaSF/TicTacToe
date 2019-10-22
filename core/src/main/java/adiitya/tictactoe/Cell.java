package adiitya.tictactoe;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Optional;

public class Cell {

	private boolean clicked = false;

	private float x;
	private float y;
	private CellType type;

	private PlayerObject player = null;

	public Cell(float x, float y, CellType type) {

		this.x = x;
		this.y = y;
		this.type = type;
	}

	public void render(SpriteBatch batch) {

		render(batch, x, y, type);
	}

	public void render(SpriteBatch batch, float x, float y, CellType type) {

		AtlasRegion texture = Resources.getTexture(type.textureName);
		batch.draw(texture, x, y, texture.getRegionWidth() * TicTacToe.SCALE, texture.getRegionHeight() * TicTacToe.SCALE);
		renderPlayer(batch);
	}

	private void renderPlayer(SpriteBatch batch) {

		if (player == null)
			return;

		AtlasRegion cell = Resources.getTexture(type.textureName);
		Vector2 playerOff = new Vector2(TicTacToe.SCALE * (cell.getRegionWidth() - player.getTextureWidth()) / 2F, TicTacToe.SCALE * (cell.getRegionHeight() - player.getTextureHeight()) / 2F);

		player.render(batch, x + playerOff.x, y + playerOff.y);
	}

	public boolean onClick(int x, int y, PlayerType playerType) {

		AtlasRegion texture = Resources.getTexture(type.textureName);
		Rectangle bounds = new Rectangle(this.x, this.y, texture.getRegionWidth() * TicTacToe.SCALE, texture.getRegionHeight() * TicTacToe.SCALE);

		if (bounds.contains(x, y) && !clicked) {

			player = PlayerObject.fromPlayerType(playerType);
			clicked = true;

			return true;
		}

		return false;
	}

	public PlayerType getOccupation() {
		return Optional.ofNullable(player)
				.map(PlayerObject::getType)
				.orElse(PlayerType.NONE);
	}

	public enum CellType {

		CORNER("cell_corner"),
		SIDE("cell_side"),
		TOP("cell_top"),
		CENTER("cell_center");

		private String textureName;

		CellType(String textureName) {

			this.textureName = textureName;
		}
	}
}
