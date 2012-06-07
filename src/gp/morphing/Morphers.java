package gp.morphing;

public enum Morphers implements MorphFactory {
	LINEAR(new LinearMorpher.Factory()),
	QUADRATIC(new QuadraticMorpher.Factory());
	
	private MorphFactory factory;

	public Morpher getMorpher( float[][] points ) {
		return factory.getMorpher(points);
	}
	
	private Morphers(MorphFactory factory) {
		this.factory = factory;
	}
	
	
}
