package gp.world;


import gp.world.camera.Camera;
import gp.world.camera.DefaultCamera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public class World implements Renderable, Updateable, Iterable<Entity>, KeyListener, Initializable {

	private Camera camera;
	private List<Entity> entities = new ArrayList<Entity>();

	private TextureStore textureStore = new DefaultTextureStore();

	public World() {
		this(new DefaultCamera());
	}

	public World(Camera camera) {
		this.camera = camera;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
		camera.init();
	}
	
	public boolean add(Entity arg0) {
		arg0.setTextureStore(textureStore);
		return entities.add(arg0);
	}

	public Entity get(int arg0) {
		return entities.get(arg0);
	}

	public boolean addAll(Collection<? extends Entity> c) {
		for(Entity e : c) {
			e.setTextureStore(textureStore);
		}
		return entities.addAll(c);
	}

	public boolean remove(Entity arg0) {
		arg0.setTextureStore(null);
		return entities.remove(arg0);
	}

	@Override
	public void render(GL2 gl, GLU glu, GLUT glut) {
		
		//camera.prepare(gl, glu, glut);
		
		
		for(Renderable r : this) {
			
			gl.glLoadIdentity();
			
			camera.prepare(gl, glu, glut);
			
			setupWorld( gl, glu, glut);

			r.render(gl, glu, glut);
		}
	}
	
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

	public void init(GL2 gl, GLU glu, GLUT glut) {
		initializeTextureStore(gl,glu,glut,textureStore);
		
		camera.init();
		
		for(Entity e : entities) {
			e.init(gl, glu, glut);
		}
	}

	protected void initializeTextureStore(GL2 gl, GLU glu, GLUT glut, TextureStore store) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}


	
}
