package com.anthonybhasin.nohp.util;

public class Timer {
	
	private int time;
	
	public Timer() {
		
		this.time = 0;
	}
	
	public void tick() {
		
		if (this.time > 0) {
			
			this.time--;
		}
	}
	
	public void set(int time) {
		
		this.time = time;
	}
	
	public boolean ready() {
		
		return this.time == 0;
	}
	
	public int getTime() {
		
		return this.time;
	}
}
