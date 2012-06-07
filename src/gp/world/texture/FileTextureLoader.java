package gp.world.texture;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GLException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class FileTextureLoader extends TextureLoader {

	public FileTextureLoader(TextureStore store) {
		super(store);
	}

	public Texture load(String file, String key) {
		try {
			Texture tex = TextureIO.newTexture(new File(file), false);
			store.putTexture(key, tex);
			
			return tex;
			
		} catch (GLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Texture load(String file) {
		return load(file, file);
	}

	public Texture[] load(String... files) {
		Texture[] texts = new Texture[files.length];
		
		int i = 0;
		
		for(String file : files) {
			texts[i++] = load(file);
		}
		
		return texts;
	}
	
}
