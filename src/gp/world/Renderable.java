package gp.world;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public interface Renderable {
	public void render( GL2 gl, GLU glu, GLUT glut );
}
