package tema1Recursividad;

public record TuplaE7(Integer ac, Integer a, Integer b) {    // realmente no nos hace falta el b pero lo ponemos por ser la 1º vez

	public static TuplaE7 of(Integer ac, Integer a, Integer b) {
		return new TuplaE7(ac, a, b);
	}
	
	public static TuplaE7 seed(Integer a, Integer b) {
		return new TuplaE7(0, a, b);
	}
	
	public TuplaE7 next() {
		return TuplaE7.of(ac+b/2, a-2, b);
	}
		
}
