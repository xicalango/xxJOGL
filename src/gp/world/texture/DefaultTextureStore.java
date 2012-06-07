package gp.world.texture;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class DefaultTextureStore implements TextureStore {
	
	private Map<String, Texture> textures = new HashMap<String, Texture>();
	
	private Texture currentTexture = null;
	
	public void bindTexture(GL2 gl, String textureName) {
		Texture tex = textures.get(textureName);
		if(tex != null) {
			tex.bind(gl);
			currentTexture = tex;
		}
	}
	
	public void unbindTexture(GL2 gl) {
		gl.glBindTexture(currentTexture.getTarget(), 0);
		currentTexture = null;
	}
	
	public Texture putTexture(String key, Texture value) {
		return textures.put(key, value);
	}

	public Texture getTexture(String key) {
		return textures.get(key);
	}

	public Texture getCurrentTexture() {
		return currentTexture;
	}

	public void setCurrentTexture(Texture currentTexture) {
		this.currentTexture = currentTexture;
	}
	



}
