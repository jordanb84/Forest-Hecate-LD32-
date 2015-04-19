package com.exilegl.forest.engine.math;

import java.util.Random;

import org.newdawn.slick.Color;

public class MathHelper {
	
	static Random random = new Random();
	
	/**
	 * Returns a random integer between
	 * two numbers
	 */
	public static int getRandom(int min, int max){
		int randomNum =(random.nextInt(max - min) + 1) + min;
		return randomNum;
	}
	
	/**
	 * Returns a random color
	 */
	public static Color getRandomColor(){
		
		int colorNum = (getRandom(0, 9));
		
		switch(colorNum){
		case 0:
			return Color.black;
		case 1:
			return Color.blue;
		case 2:
			return Color.cyan;
		case 3:
			return Color.darkGray;
		case 4:
			return Color.gray;
		case 5:
			return Color.green;
		case 6:
			return Color.lightGray;
		case 7:
			return Color.magenta;
		case 8:
			return Color.orange;
		case 9:
			return Color.pink;
		case 10:
			return Color.red;
		case 11:
			return Color.yellow;
		default:
			return Color.blue;
		}
		
	}

}
