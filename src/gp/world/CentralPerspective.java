package gp.world;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;


public class CentralPerspective implements Perspective {

	private float openingAngle = 90f;
	private float near = 1f;
	private float far = 100f;

	@Override
	public void setupPerspective(GL2 gl, GLU glu, int width, int height) {
		glu.gluPerspective(openingAngle, (float) width / (float) height, near, far);
	}

	public float getOpeningAngle() {
		return openingAngle;
	}

	public void setOpeningAngle(float openingAngle) {
		this.openingAngle = openingAngle;
	}

	public float getNear() {
		return near;
	}

	public void setNear(float near) {
		this.near = near;
	}

	public float getFar() {
		return far;
	}

	public void setFar(float far) {
		this.far = far;
	}

}
