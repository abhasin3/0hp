package com.anthonybhasin.nohp.io;

public class Log {

	/**
	 * Only log messages with a severity GREATER THAN OR EQUAL TO output.
	 */
	public static Severity output = Severity.LOW;

	public enum Severity {
		LOW(0), DEFAULT(1), HIGH(2);

		public int value;

		private Severity(int value) {

			this.value = value;
		}
	}

	public static void print(String s, Severity severity) {

		if (severity.value < Log.output.value) {

			return;
		}

		if (severity.value > Severity.DEFAULT.value) {

			s = ">>> " + s + " <<<";
		}

		System.out.println(s);
	}

	public static void print(String s) {

		Log.print(s, Severity.LOW);
	}
}
