package adiitya.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public final class Resources {

	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("game.atlas"), true);
	private static final ObjectMap<String, Array<AtlasRegion>> cache = new ObjectMap<>();

	public static AtlasRegion getTexture(String name) {

		if (isCached(name))
			return getCachedTexture(name);

		AtlasRegion texture = atlas.findRegion(name);
		addToCache(texture, name);

		return texture;
	}

	public static Array<AtlasRegion> getAnimation(String name) {

		if (isCached(name))
			return cache.get(name);

		Array<AtlasRegion> textures = atlas.findRegions(name);
		cache.put(name, textures);

		return textures;
	}

	private static boolean isCached(String name) {
		return cache.containsKey(name);
	}

	private static AtlasRegion getCachedTexture(String name) {
		return cache.get(name, new Array<>()).get(0);
	}

	private static void addToCache(AtlasRegion texture, String name) {

		Array<AtlasRegion> cachedTextures = cache.get(name, new Array<>());
		cachedTextures.add(texture);
		cache.put(name, cachedTextures);
	}

	public static TextureAtlas getAtlas() {
		return atlas;
	}

	private Resources() {}
}
