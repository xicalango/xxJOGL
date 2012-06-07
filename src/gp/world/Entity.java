package gp.world;

import gp.morphing.MorphFactory;
import gp.morphing.Morphers;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public abstract class Entity implements Renderable, Updateable, Initializable {

	protected float x;
	protected float y;
	protected float z;
	
	private float[] curPos;

	protected float alpha; // blickrichtung

	private Movement movement;
	private MorphFactory usedMorpher = Morphers.QUADRATIC;
	
	private TextureProvider textureStore;
	
	void setTextureStore(TextureProvider textureStore) {
		this.textureStore = textureStore;
	}

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

	@Override
	public void init(GL2 gl, GLU glu, GLUT glut) {	}
	
	@Override
	public void render(GL2 gl, GLU glu, GLUT glut) {

		// gl.glLoadIdentity();

		gl.glTranslatef(getCurX(), getCurY(), getCurZ());

		gl.glRotatef(alpha, 0f, 0f, 1f);
		
		_render(gl, glu, glut);

	}
	
	public void bindTexture(GL2 gl, String textureName) {
		textureStore.bindTexture(gl, textureName);
	}

	public void unbindTexture(GL2 gl) {
		textureStore.unbindTexture(gl);
	}

	protected void setMaterial(GL2 gl, float r, float g, float b) {
		float mat_ambient[] = { r / 3f, g / 3f, b / 3f, 1.0f };
		float mat_diffuse[] = { r, g, b, 1.0f };
		float mat_specular[] = { 0.70f, 0.70f, 0.70f, 1.0f };
		float mat_shininess = 32f;

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
		gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, mat_shininess);

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

	public void moveTo(float gx, float gy, float gz) {
		if (movement == null) {
			
			if(!canWalkTo(gx,gy,gz)) {
				gx = x;
				gy = y;
			}
			
			final float xm = (gx+x)/2;
			final float ym = (gy+y)/2;
			
						
			movement = new Movement(x, y, z, gx, gy, gz, 0.1f, usedMorpher.getMorpher(new float[][]{new float[]{ xm, ym, 2f}}) );
			curPos = movement.getCurrentPos();
		}
	}

	protected boolean canWalkTo(float gx, float gy, float gz) {
		return true;
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
