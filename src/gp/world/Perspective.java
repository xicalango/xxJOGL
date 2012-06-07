package gp.world;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public interface Perspective {
	void setupPerspective(GL2 gl, GLU glu, int width, int height);
}
