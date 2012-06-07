package gp.morphing;

public class QuadraticMorpher implements Morpher {

	private float xm;
	private float ym;
	private float zm;
		
	public QuadraticMorpher(float xm, float ym, float zm) {
		this.xm = xm;
		this.ym = ym;
		this.zm = zm;
	}

	@Override
	public float[] morph(float t, float x1, float y1, float z1, float x2, float y2, float z2) {
		final float tt = 1-t;
		return new float[] {
			tt*tt * x1 + 2*tt*t*xm + t*t*x2,
			tt*tt * y1 + 2*tt*t*ym + t*t*y2,
			tt*tt * z1 + 2*tt*t*zm + t*t*z2,
		};
	}

}
