package com.example.rollerdice;

import android.graphics.drawable.Drawable;

public class GreenDefenseDice extends Dice {

	
	   public static String vals[] ={"miss", "miss", "miss", "evade", "evade", "evade","focus","focus" 
	    };	
	
	
	public GreenDefenseDice() {
		super("Vert",  vals.length);
	}
	
	@Override
	public String getFaceName(int n) {
		return vals[n];
	}

	@Override
	public Drawable getFaceImage(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
