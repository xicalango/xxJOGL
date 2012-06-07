package gp.world.texture;


import com.jogamp.opengl.util.texture.Texture;

public interface TextureStore extends TextureProvider {

	Texture putTexture(String key, Texture value);

	Texture getTexture(String key);

	Texture getCurrentTexture();

}