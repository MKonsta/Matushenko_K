package ru.job4j.max;

public class Max {
	public int max(int first, int second) {
		int result = 0;
		if (first > second) {
			result = first;
		} else if (first < second) {
			result = second;
		}
		return result;
	}
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}