package com.anthonybhasin.nohp.math;

public class DegreeMath {

	/**
	 * Pre-calculated values for each integer degree.
	 */
	public static final float[] SIN = new float[360], COS = new float[360], ROT_ANGLE = new float[360];

	static {

		for (int i = 0; i < 360; i++) {

			double rotAngle = Math.toRadians(i);

			DegreeMath.ROT_ANGLE[i] = (float) rotAngle;

			DegreeMath.SIN[i] = (float) Math.sin(rotAngle);

			DegreeMath.COS[i] = (float) Math.cos(rotAngle);
		}
	}
}
