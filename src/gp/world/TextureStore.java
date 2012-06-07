package gp.world;


import com.jogamp.opengl.util.texture.Texture;

public interface TextureStore extends TextureProvider {

	Texture putTexture(String key, Texture value);

	Texture getTexture(String key);

	Texture getCurrentTexture();

	void setCurrentTexture(Texture currentTexture);

	Texture putFromFile(String file, String key);

	Texture putFromFile(String file);
	
	Texture[] putFromFile(String... files);

}