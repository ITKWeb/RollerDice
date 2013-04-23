package com.example.rollerdice;

import android.graphics.drawable.Drawable;

public class DiceFace {

	private Drawable faceImage;
	private String faceName;
	private Dice dice;
	
	public DiceFace(Dice dice, String  faceName, Drawable faceImage) {
		this.dice = dice;
		this.setFaceName(faceName);
		this.setFaceImage(faceImage);
		
	}

	public String getValue() {
		return "De " + getDiceName() + " - " + getFaceName();
	}

	
	private String getDiceName() {
		return dice.getName();
	}

	public String getFaceName() {
		return faceName;
	}

	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}

	public Drawable getFaceImage() {
		return faceImage;
	}

	public void setFaceImage(Drawable faceImage) {
		this.faceImage = faceImage;
	}
	
	
	
}
