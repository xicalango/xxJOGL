package gp.world.camera;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import gp.world.Entity;

public class EntityFollowerSphereCamera extends SphereCamera {

	private Entity followingEntity = null;
	
	private boolean holdPosition = false;
	
	public Entity getFollowingEntity() {
		return followingEntity;
	}

	public void setFollowingEntity(Entity followingEntity) {
		this.followingEntity = followingEntity;
		recalculateCamera();
	}

	public boolean isHoldPosition() {
		return holdPosition;
	}

	public void setHoldPosition(boolean holdPosition) {
		this.holdPosition = holdPosition;
	}

	@Override
	public void prepare(GL2 gl, GLU glu, GLUT glut) {
		
		recalculateCamera();
		
		if(followingEntity == null) {
			setCenterXYZ(0,0,0);
		} else {
			setCenterXYZ(followingEntity.getCurX(), followingEntity.getCurY(), followingEntity.getCurZ());
		}

		super.prepare(gl, glu, glut);
	}
	
	@Override
	public void recalculateCamera() {
		if(holdPosition) {
			super.recalculateCamera();
		} else {
			eyeX = centerX + radius * (float) Math.cos(theta) * (float) Math.cos(phi);
			eyeY = centerY + radius * (float) Math.cos(theta) * (float) Math.sin(phi);
			eyeZ = centerZ + radius * (float) Math.sin(theta);
		}
	}
	
}
