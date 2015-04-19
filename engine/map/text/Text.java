package com.exilegl.forest.engine.map.text;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Text {
	
	/**
	 * Draws bordered text
	 */
	public static void drawBordered(Graphics g, String text, int x, int y){
		
		g.setColor(Color.black);
		g.drawString(text, x - 2, y - 2);
		g.setColor(Color.white);
		g.drawString(text, x, y);
	}

}
