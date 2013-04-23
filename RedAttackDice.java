package com.example.rollerdicecuong;

import android.graphics.drawable.Drawable;

public class RedAttackDice extends Dice {

	
	public static  String vals[] ={"miss", "miss", "hit", "hit", "hit", "critical","focus","focus" 
	    };	
	
	
	public RedAttackDice() {
		super("Rouge", vals.length);
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
