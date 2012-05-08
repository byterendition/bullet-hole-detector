package controller;

public class CalcUtil {
	public static int modulo(int a, int b) {
		return a % b < 0 ? (a % b) + b : a % b;
	}
	
	public static double modulo(double a, double b) {
		return a % b < 0 ? (a % b) + b : a % b;
	}
}
