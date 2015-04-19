package com.exilegl.forest.engine.window;

import com.exilegl.forest.engine.Game;

public class WindowHandler {

	static int previousWidth;
	static int previousHeight;
	
	/**
	 * Honestly, I think I shouldn't resize the images.
	 * The entities don't look bad in like 800x600, and they spawn
	 * based on the current window size.
	 * 
	 * What I need is to constantly set everything's X/Y based on the resolution
	 */
	
	public static void update(){
		
		//If the window is not it's previous size, relocate and resize images to fit
		if(previousWidth != Game.WIDTH){
			rescale();
		}
		if(previousHeight != Game.HEIGHT){
			rescale();
		}
		
		previousWidth = (Game.WIDTH);
		previousHeight = (Game.HEIGHT);
	}
	
	//If I don't get it done by the last 2 hours, I can port to LbiGDX for resizing.
	
	public static void rescale(){
		
	}
	
}
