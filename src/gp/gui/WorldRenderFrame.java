package gp.gui;

import gp.world.World;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;

public class WorldRenderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private WinRenderer renderer;
	private FPSAnimator animator;

	public WorldRenderFrame(World world) {
		
		GLCapabilities cap = new GLCapabilities(GLProfile.getDefault());
		cap.setDoubleBuffered(true);
		
		renderer = new WinRenderer(cap, world);
		animator = new FPSAnimator(renderer,20);
		
		setSize(640,480);
		
		add(renderer);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				animator.stop();
				super.windowClosing(e);
			}
		});
		
		animator.start();
		setVisible(true);
		renderer.requestFocus();
	}


	
	
	
}
