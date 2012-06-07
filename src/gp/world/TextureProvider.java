package gp.world;

import javax.media.opengl.GL2;

public interface TextureProvider {

	void bindTexture(GL2 gl, String textureName);

	void unbindTexture(GL2 gl);

}