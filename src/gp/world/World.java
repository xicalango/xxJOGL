package gp.world;


import gp.world.camera.Camera;
import gp.world.camera.DefaultCamera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public class World implements Renderable, Updateable, Iterable<Entity>, KeyListener, Margins {

	private Camera camera;
	private Perspective perspective = new CentralPerspective();
	
	public Perspective getPerspective() {
		return perspective;
	}

	public void setPerspective(Perspective perspective) {
		this.perspective = perspective;
	}

	private List<Entity> entities = new ArrayList<Entity>();
	
	private float marginX1 = -1f;
	private float marginY1 = -1f;
	private float marginX2 = 1f;
	private float marginY2 = 1f;
	
	public float getMarginX1() {
		return marginX1;
	}

	public void setMarginX1(float marginX1) {
		this.marginX1 = marginX1;
	}

	public float getMarginY1() {
		return marginY1;
	}

	public void setMarginY1(float marginY1) {
		this.marginY1 = marginY1;
	}

	public float getMarginX2() {
		return marginX2;
	}

	public void setMarginX2(float marginX2) {
		this.marginX2 = marginX2;
	}

	public float getMarginY2() {
		return marginY2;
	}

	public void setMarginY2(float marginY2) {
		this.marginY2 = marginY2;
	}

	public World() {
		this(new DefaultCamera());
	}

	public World(DefaultCamera camera) {
		this.camera = camera;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public boolean add(Entity arg0) {
		return entities.add(arg0);
	}

	public Entity get(int arg0) {
		return entities.get(arg0);
	}

	public boolean remove(Object arg0) {
		return entities.remove(arg0);
	}

	@Override
	public void render(GL2 gl, GLU glu, GLUT glut) {
		
		//camera.prepare(gl, glu, glut);
		
		//preRendering(gl, glu, glut);
		
		gl.glLoadIdentity();
		
		camera.prepare(gl, glu, glut);
		
		setupWorld( gl, glu, glut);
		
		for(Renderable r : this) {
			gl.glPushMatrix();

			r.render(gl, glu, glut);
			
			gl.glPopMatrix();
		}
	}
	
	//protected void preRendering(GL2 gl, GLU glu, GLUT glut) { }

	@Override
	public void update() {
		for(Updateable u : this) {
			u.update();
		}
	}

	protected void setupWorld( GL2 gl, GLU glu, GLUT glut) {}
	
	@Override
	public Iterator<Entity> iterator() {
		return entities.iterator();
	}

	public void init() {
		camera.init();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void setMargins(float x1, float y1, float x2, float y2) {
		this.marginX1 = x1;
		this.marginY1 = y1;
		this.marginX2 = x2;
		this.marginY2 = y2;
	}

	public void setupPerspective(GL2 gl, GLU glu, int width, int height) {
		if(perspective != null) {
			perspective.setupPerspective(gl, glu, width, height);
		}
	}
	
}
