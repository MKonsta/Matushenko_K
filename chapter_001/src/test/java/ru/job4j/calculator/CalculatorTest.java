package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expect = 2D;
		assertThat(result, is(expect));
	}
	
	public void whenSubtractOneMinesOneThenZero() {
		Calculator calc = new Calculator();
		calc.subtract(1D, 1D);
		double result = calc.getResult();
		double expect = 0D;
		assertThat(result, is(expect));
	}
	
	public void whenOneDivOneThenOne() {
		Calculator calc = new Calculator();
		calc.div(1D, 1D);
		double result = calc.getResult();
		double expect = 1D;
		assertThat(result, is(expect));
	}
	
	public void whenOneMultipleOneThenOne() {
		Calculator calc = new Calculator();
		calc.multiple(1D, 1D);
		double result = calc.getResult();
		double expect = 1D;
		assertThat(result, is(expect));
	}
}