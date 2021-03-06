package ru.job4j.condition;

public class Triangle {
	private Point a;
	private Point b;
	private Point c;
	
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	* Метод вычисления полупериметра по длинам сторон.
	*
	* Формула.
	*
	* (ab + ac + bc) / 2
	*
	* @param ab расстояние между точками a b
	* @param ac расстояние между точками a c
	* @param bc расстояние между точками b c
	* @return Перимент.
	*/
	public double period(double ab, double ac, double bc) {
		return (ab + ac + bc) / 2;
	}
	/**
	 * Метод должен вычислить площадь треугольника.
	 *
	 * @return Вернуть прощадь, если треугольник существует или -1, если треугольника нет.
	 */
	public double area() {
		double rsl = -1;
		double ab = this.a.distanceTo(this.b);
		double ac = this.a.distanceTo(this.c);
		double bc = this.b.distanceTo(this.c);
		double p = this.period(ab, ac, bc);
		if (ab != 0 && ac != 0 && bc != 0) {
			rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
		}
		return rsl;
	}

	/*public static void main(String[] args) {
		Point a = new Point(0, 0);
		Point b = new Point(0, 2);
		Point c = new Point(2, 0);
		Triangle triangle = new Triangle(a, b, c);
		System.out.println(triangle.area());
	}*/

}