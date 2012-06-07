package gp.world.camera;

import gp.world.Entity;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public class EntityFollowerCamera extends DefaultCamera {

	private Entity followingEntity;
	
	private float offsetX = 0f;
	private float offsetY = 0f;
	private float offsetZ = 0f;
	
	public Entity getFollowingEntity() {
		return followingEntity;
	}
	public void setFollowingEntity(Entity followingEntity) {
		this.followingEntity = followingEntity;
	}
	public float getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}
	public float getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
	public float getOffsetZ() {
		return offsetZ;
	}
	public void setOffsetZ(float offsetZ) {
		this.offsetZ = offsetZ;
	}
	
	public EntityFollowerCamera(Entity followingEntity, float offsetX, float offsetY, float offsetZ) {
		this.followingEntity = followingEntity;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}
	public EntityFollowerCamera(Entity followingEntity) {
		this(followingEntity,0f,0f,0f);
	}

	@Override
	public void prepare(GL2 gl, GLU glu, GLUT glut) {

		setEyeXYZ(followingEntity.getCurX() + offsetX, followingEntity.getCurY() + offsetY, followingEntity.getCurZ() + offsetZ);
		setCenterXYZ(followingEntity.getDirX(), followingEntity.getDirY(), eyeZ);
		setViewUpXYZ(0, 0, 1f);
		
		super.prepare(gl, glu, glut);
	}
	
	
}
