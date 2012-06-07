package gp.gui;

import gp.world.World;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public class WinRenderer extends GLCanvas implements GLEventListener, java.awt.event.KeyListener {

	private static final long serialVersionUID = 1L;

	private World world;

	public WinRenderer(GLCapabilities cap, World world) {
		super(cap);
		this.world = world;
		addGLEventListener(this);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		
		final GL2 gl = drawable.getGL().getGL2();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();
		
		world.init(gl,glu,glut);
		
		addKeyListener(world);
//		addKeyListener(this);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(GLAutoDrawable drawable) {

		world.update();
		
		final GL2 gl = drawable.getGL().getGL2();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();
		
		initGL(gl,glu,glut);
		
		gl.glLoadIdentity();

		world.render(gl, glu, glut);

		gl.glFlush();
		gl.glDisable(GL.GL_TEXTURE_2D);
	}

	private void initGL(final GL2 gl, GLU glu, GLUT glut) {
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_DECAL);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		final GL2 gl = drawable.getGL().getGL2();
		final GLU glu = new GLU();

		// Bildausschnitt im Fenster positionieren
		gl.glViewport(0, 0, width, height);
		// Auf Projektionsmatrix umschalten
		gl.glMatrixMode(GL2.GL_PROJECTION);
		// Projektionsmwtrix initialisieren
		gl.glLoadIdentity();
		// Zentralprojektion waehlen
		glu.gluPerspective(90, (float) width / (float) height, 1, 100);
		//gl.glOrthof(-1, 1, -1, 1, -1, 1);
		// und wieder auf Modellmatrix zuruecksetzen
		gl.glMatrixMode(GL2.GL_MODELVIEW);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		display();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}