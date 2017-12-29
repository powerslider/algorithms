package com.ceco.algorithms.problems.warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Simple units convertor.
 *
 * Example input:
 * 5
 * mi
 * yd
 * <p>
 * Output:
 * 8800.000016737176
 * <p>
 * Example input:
 * 500
 * cm
 * dm
 * <p>
 * Output:
 * 50.0
 * @author Tsvetan Dimitrov (tsvetan.dimitrov23@gmail.com)
 * @since 29-Dec-2017
 */
public class UnitsConverter {

	private static final Map<String, Integer> METRIC_UNITS_MAPPING = new HashMap<String, Integer>() {{
		put("m", 0);
		put("cm", -2);
		put("dm", -1);
		put("mm", -3);
		put("km", 3);
	}};

	private static final Map<String, Double> US_UNITS_MAPPING = new HashMap<String, Double>() {{
		put("mi", 0.000621371192);
		put("in", 39.3700787);
		put("ft", 3.2808399);
		put("yd", 1.0936133);
	}};

	private static final Map<String, Integer> UNIT_ORDER = new HashMap<String, Integer>() {{
		put("mm", 1);
		put("cm", 2);
		put("in", 3);
		put("dm", 4);
		put("ft", 5);
		put("yd", 6);
		put("m", 7);
		put("km", 8);
		put("mi", 9);
	}};

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		double input = scanner.nextDouble();
		scanner.nextLine();
		String inputUnit = scanner.nextLine();
		String outputUnit = scanner.nextLine();

		int metreOrder = UNIT_ORDER.get("m");
		Integer outputUnitOrder = UNIT_ORDER.get(outputUnit);
		boolean notSameOrder = outputUnitOrder > metreOrder || outputUnitOrder < metreOrder;

		double resultInMetres = convertUnits(input, inputUnit);
		double result = convertUnits(resultInMetres, outputUnit, notSameOrder);
		System.out.println(result);
	}

	private static double convertMetric(double value, String unit, boolean... notSameOrder) {
		double exponent = METRIC_UNITS_MAPPING.get(unit);
		if (notSameOrder.length > 0) {
			exponent = notSameOrder[0] ? -exponent : exponent;
		}
		return value * Math.pow(10, exponent);
	}

	private static double convertUS(double value, String unit, boolean... notSameOrder) {
		double coefficient = US_UNITS_MAPPING.get(unit);
		if (notSameOrder.length > 0) {
			if (notSameOrder[0]) {
				return value * coefficient;
			}
		}
		return value / coefficient;
	}

	private static double convertUnits(double value, String unit, boolean... notSameOrder) {
		if (METRIC_UNITS_MAPPING.containsKey(unit)) {
			return convertMetric(value, unit, notSameOrder);
		} else if (US_UNITS_MAPPING.containsKey(unit)) {
			return convertUS(value, unit, notSameOrder);
		} else {
			throw new IllegalArgumentException("Invalid unit: " + unit);
		}
	}
}
