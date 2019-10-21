package adiitya.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Cell {

	private static final float SCALE = 500F / 64F;
	private static Array<TextureRegion> textures = new Array<>();

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

		TextureRegion texture = textures.get(type.ordinal());
		batch.draw(texture, x, y, texture.getRegionWidth() * SCALE, texture.getRegionHeight() * SCALE);
		renderPlayer(batch);
	}

	private void renderPlayer(SpriteBatch batch) {

		if (player == null)
			return;

		TextureRegion cell = textures.get(type.ordinal());
		Vector2 playerOff = new Vector2(SCALE * (cell.getRegionWidth() - player.getTextureWidth()) / 2F, SCALE * (cell.getRegionHeight() - player.getTextureHeight()) / 2F);

		player.render(batch, x + playerOff.x, y + playerOff.y);
		//batch.draw(player, x + playerOff.x, y + playerOff.y, player.getTextureWidth() * SCALE, player.getTextureHeight() * SCALE);
	}

	public boolean onClick(int x, int y, PlayerType playerType) {

		TextureRegion texture = textures.get(type.ordinal());
		Rectangle bounds = new Rectangle(this.x, this.y, texture.getRegionWidth() * SCALE, texture.getRegionHeight() * SCALE);

		if (bounds.contains(x, y) && !clicked) {

			player = PlayerObject.fromPlayerType(playerType);
			clicked = true;

			return true;
		}

		return false;
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

	static {

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("game.atlas"), true);

		for (CellType type : CellType.values())
			textures.add(atlas.findRegion(type.textureName));
	}
}
