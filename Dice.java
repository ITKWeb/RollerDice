package com.example.rollerdicecuong;

import java.util.Date;
import java.util.Random;

import android.graphics.drawable.Drawable;

public abstract class Dice {
	private DiceFace diceFace;

	

	private int facesCount;

	private int value;

	private String name;



	private Randomizer random;
	
	public Dice(String name, int facesCount) {
		this.name = name;
		this.facesCount = facesCount;
	}

	public void roll(int strength) {

		random = Randomizer.getInstance();
		
		value = random.nextInt(facesCount);
		diceFace = new DiceFace(this, getFaceName(value), getFaceImage(value));
	}

	public DiceFace getDiceFace() {
		return diceFace;
	}

	public abstract String getFaceName(int n);

	public abstract Drawable getFaceImage(int n);

	public String getName() {
		return name;
	}

	
	public static class Randomizer{
		private static  Randomizer ran;
		
		private Random random;
		
		private Randomizer(){
			random = new Random(new Date().getTime()); 
		}
		
		public int nextInt(int facesCount) {
			return random.nextInt(facesCount);
		}

		public static Randomizer getInstance() {
			if(ran == null) {
				ran = new Randomizer();
			}
			return ran;
		}
		
		
	}
}
