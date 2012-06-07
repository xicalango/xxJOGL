package gp.world;

import gp.morphing.QuadraticMorpher;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public abstract class Entity implements Renderable, Updateable {

	protected float x;
	protected float y;
	protected float z;
	
	private float[] curPos;
	
	private boolean visible = true;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	protected float alpha; // blickrichtung

	private Movement movement;

	private GL2 gl;
	private GLU glu;
	private GLUT glut;
	
	private Margins margins = null;

	public Entity() {
		this(0f, 0f, 0f, 0f);
	}

	public Entity(float x, float y, float z, float alpha) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.alpha = alpha;

		this.movement = null;
	}


	public Entity( float x, float y, float z) {
		this(x, y, z, 0f);
	}
	
	public Margins getMargins() {
		return margins;
	}

	public void setMargins(Margins margins) {
		this.margins = margins;
	}


	@Override
	public void render(GL2 gl, GLU glu, GLUT glut) {

		if(!visible)
			return;
		
		// gl.glLoadIdentity();

		gl.glTranslatef(getCurX(), getCurY(), getCurZ());

		gl.glRotatef(alpha, 0f, 0f, 1f);

		this.gl = gl;
		this.glu = glu;
		this.glut = glut;

		_render(gl, glu, glut);

	}

	@Override
	public void update() {

		if (movement != null) {
			movement.update();
			
			curPos = movement.getCurrentPos();
			
			if (movement.isFinished()) {
				x = movement.getX2();
				y = movement.getY2();
				z = movement.getZ2();
				movement = null;
				curPos = null;
			}
		}

	}

	protected abstract void _render(GL2 gl, GLU glu, GLUT glut);

	protected void setMaterial(float r, float g, float b) {
		float mat_ambient[] = { r / 3f, g / 3f, b / 3f, 1.0f };
		float mat_diffuse[] = { r, g, b, 1.0f };
		float mat_specular[] = { 0.70f, 0.70f, 0.70f, 1.0f };
		float mat_shininess = 32f;

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
		gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, mat_shininess);

	}

	public void moveTo(float gx, float gy, float gz) {
		if (movement == null) {
			
			if(margins != null) {
				if(gx < margins.getMarginX1() || gx > margins.getMarginX2()) {
					gx = x;
				}
				if(gy < margins.getMarginY1() || gy > margins.getMarginY2()) {
					gy = y;
				}
			}
			
			final float xm = (gx+x)/2;
			final float ym = (gy+y)/2;
			
			movement = new Movement(x, y, z, gx, gy, gz, 0.1f, new QuadraticMorpher(xm, ym, 2f) );
			curPos = movement.getCurrentPos();
		}
	}

	public void moveRel(float dx, float dy, float dz) {
		moveTo(x + dx, y + dy, z + dz);
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float getCurX() {
		if(movement != null) {
			return curPos[0];
		} else { 
			return x;
		}
	}

	public float getCurY() {
		if(movement != null) {
			return curPos[1];
		} else { 
			return y;
		}
	}

	public float getCurZ() {
		if(movement != null) {
			return curPos[2];
		} else { 
			return z;
		}
	}
	public float getDirX() {
		return getCurX() + (float) Math.cos(alpha);
	}

	public float getDirY() {
		return getCurY() + (float) Math.sin(alpha);
	}

}
