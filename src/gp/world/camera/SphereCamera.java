package gp.world.camera;

public class SphereCamera extends DefaultCamera {

	// Radius der Kugelschale, auf der sich die Kamera bewegt
	private float radius = 10.0f;
	// Thetawinkel der Kamera (Breitengrad)
	private float theta = 0.0f;
	// Phiwinkel der Kamera (Laengengrad)
	private float phi = 0.0f;

	public float getTheta() {
		return theta;
	}

	public void setTheta(float theta) {
		this.theta = theta;
	}

	public float getPhi() {
		return phi;
	}

	public void setPhi(float phi) {
		this.phi = phi;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getRadius() {
		return radius;
	}

	public void rotateLng(float delta) {
		if(phi+delta < Math.PI * 2 && phi+delta > 0) {
			phi += delta;
			rotateView(0f, 0f, 1f, delta);
		}
	}

	public void rotateLat(float delta) {
		if(theta + delta < Math.PI / 2 && theta+delta > -Math.PI/2) {
			theta += delta;

			rotateView(radius * (float) Math.cos(theta) * (float) Math.sin(phi), -radius * (float) Math.cos(theta) * (float) Math.cos(phi), 0, delta);
		}
	}
	
	public void rotateLR(float delta) {
		rotateView(eyeX, eyeY, eyeZ, delta);
	}

	

	public void rotateView(float rx, float ry, float rz, float angle) {
		// Zunaechst die normalisierte Drehachse berechnen
		
		final float length = (float) Math.sqrt(rx * rx + ry * ry + rz * rz);
		final float nx = rx / length;
		final float ny = ry / length;
		final float nz = rz / length;
		
		// s, c und t kommen oefter vor, darum Vorabberechnung
		final float s = (float) Math.sin(angle);
		final float c = (float) Math.cos(angle);
		final float t = 1 - c;

		final float viewUpEndX = getViewUpEndX();
		final float viewUpEndY = getViewUpEndY();
		final float viewUpEndZ = getViewUpEndZ();

		// Jetzt ist es sicher die kameraposition neu zu bestimmen.
		recalculateCamera();
		
		// Rotationsmatrix anwenden
		setViewUpEndX( (t * nx * nx + c) * viewUpEndX + (t * nx * ny - s * nz) * viewUpEndY + (t * nx * nz + s * ny) * viewUpEndZ );
		setViewUpEndY( (t * nx * ny + s * nz) * viewUpEndX + (t * ny * ny + c) * viewUpEndY + (t * ny * nz - s * nx) * viewUpEndZ );
		setViewUpEndZ( (t * nx * nz - s * ny) * viewUpEndX + (t * ny * nz + s * nx) * viewUpEndY + (t * nz * nz + c) * viewUpEndZ );
	}

	public void recalculateCamera() {
		eyeX = radius * (float) Math.cos(theta) * (float) Math.cos(phi);
		eyeY = radius * (float) Math.cos(theta) * (float) Math.sin(phi);
		eyeZ = radius * (float) Math.sin(theta);
	}
	
	@Override
	public void init() {
		super.init();
		
		recalculateCamera();
	}
}
