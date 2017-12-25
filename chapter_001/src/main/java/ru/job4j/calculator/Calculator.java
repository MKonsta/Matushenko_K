package ru.job4j.calculator;

public class Calculator {
	private double result;
	void add(double first, double second) {
		this.result = first + second;
	}
	
	void subtract(double first, double second) {
		this.result = first - second;
	}
	
	void div(double first, double second) {
		this.result = first / second;
	}
	
	void multiple(double first, double second) {
		this.result = first * second;
	}
	
	public double getResult() {
		return this.result;
	}
}