package adiitya.tictactoe;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Cell {

	private static Array<TextureRegion> textures = new Array<>();

	private TicTacToe ttt;

	private boolean clicked = false;

	private float x;
	private float y;
	private CellType type;

	private PlayerObject player = null;

	public Cell(TicTacToe ttt, float x, float y, CellType type) {

		this.ttt = ttt;
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public void render(SpriteBatch batch) {

		render(batch, x, y, type);
	}

	public void render(SpriteBatch batch, float x, float y, CellType type) {

		TextureRegion texture = textures.get(type.ordinal());
		batch.draw(texture, x, y, texture.getRegionWidth() * TicTacToe.SCALE, texture.getRegionHeight() * TicTacToe.SCALE);
		renderPlayer(batch);
	}

	private void renderPlayer(SpriteBatch batch) {

		if (player == null)
			return;

		TextureRegion cell = textures.get(type.ordinal());
		Vector2 playerOff = new Vector2(TicTacToe.SCALE * (cell.getRegionWidth() - player.getTextureWidth()) / 2F, TicTacToe.SCALE * (cell.getRegionHeight() - player.getTextureHeight()) / 2F);

		player.render(batch, x + playerOff.x, y + playerOff.y);
	}

	public boolean onClick(int x, int y, PlayerType playerType) {

		TextureRegion texture = textures.get(type.ordinal());
		Rectangle bounds = new Rectangle(this.x, this.y, texture.getRegionWidth() * TicTacToe.SCALE, texture.getRegionHeight() * TicTacToe.SCALE);

		if (bounds.contains(x, y) && !clicked) {

			player = PlayerObject.fromPlayerType(ttt, playerType);
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

	public static void initialize(TicTacToe ttt) {

		for (CellType type : CellType.values())
			textures.add(ttt.atlas.findRegion(type.textureName));
	}
}
