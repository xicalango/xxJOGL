package gp.utils;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import gp.world.Renderable;

/**
 * Adapted sourcecode from http://www.opengl.org/resources/libraries/glut/ and
 * http
 * ://stackoverflow.com/questions/327043/how-to-apply-texture-to-glutsolidcube
 * 
 * @author alexx
 * 
 */
public class TexturedSolidCube implements Renderable {

	private static float[][] n = new float[][] { { -1.0f, 0.0f, 0.0f }, { 0.0f, 1.0f, 0.0f }, { 1.0f, 0.0f, 0.0f }, { 0.0f, -1.0f, 0.0f }, { 0.0f, 0.0f, 1.0f },
			{ 0.0f, 0.0f, -1.0f } };

	private static int[][] faces = new int[][] { { 0, 1, 2, 3 }, { 3, 2, 6, 7 }, { 7, 6, 5, 4 }, { 4, 5, 1, 0 }, { 5, 6, 2, 1 }, { 7, 4, 0, 3 } };

	private float size = 1f;

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	@Override
	public void render(GL2 gl, GLU glu, GLUT glut) {
		// TODO Auto-generated method stub
		float v[][] = new float[8][3];
		int i;

		v[0][0] = v[1][0] = v[2][0] = v[3][0] = -size / 2;
		v[4][0] = v[5][0] = v[6][0] = v[7][0] = size / 2;
		v[0][1] = v[1][1] = v[4][1] = v[5][1] = -size / 2;
		v[2][1] = v[3][1] = v[6][1] = v[7][1] = size / 2;
		v[0][2] = v[3][2] = v[4][2] = v[7][2] = -size / 2;
		v[1][2] = v[2][2] = v[5][2] = v[6][2] = size / 2;

		for (i = 5; i >= 0; i--) {
			gl.glBegin(GL2.GL_QUADS);
			gl.glNormal3f(n[i][0], n[i][1], n[i][2]);

			gl.glTexCoord2f(0, 0);
			gl.glVertex3f(v[faces[i][0]][0], v[faces[i][0]][1], v[faces[i][0]][2]);

			gl.glTexCoord2f(0, 1);
			gl.glVertex3f(v[faces[i][1]][0], v[faces[i][1]][1], v[faces[i][1]][2]);

			gl.glTexCoord2f(1, 1);
			gl.glVertex3f(v[faces[i][2]][0], v[faces[i][2]][1], v[faces[i][2]][2]);

			gl.glTexCoord2f(1, 0);
			gl.glVertex3f(v[faces[i][3]][0], v[faces[i][3]][1], v[faces[i][3]][2]);
			gl.glEnd();
		}
	}

}
