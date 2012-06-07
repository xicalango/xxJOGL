package gp.morphing;

public class LinearMorpher implements Morpher{

	public static class Factory implements MorphFactory {
		@Override
		public Morpher getMorpher(float[][] points) {
			return new LinearMorpher();
		}
	}
	
	@Override
	public float[] morph(float t, float x1, float y1, float z1, float x2, float y2, float z2) {
		final float tt = 1-t;
		
		return new float[] {
				tt * x1 + t * x2,
				tt * y1 + t * y2,
				tt * z1 + t * z2
		};
	}

}
