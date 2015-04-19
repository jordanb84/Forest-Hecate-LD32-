package com.exilegl.forest.engine.map.text;

import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;


public class FontManager {
	
	static Font FONT_BOLD;
	static UnicodeFont FONT_BOLDU_GRAY;
	static UnicodeFont FONT_BOLDU_WHITE;
	static UnicodeFont FONT_BOLDU_GRAY_LARGE;
	static UnicodeFont FONT_BOLDU_WHITE_LARGE;
	
	public static void init() throws SlickException{
		
		FONT_BOLD = new Font("Serif", Font.BOLD, 24);
		FONT_BOLDU_GRAY = new UnicodeFont(FONT_BOLD, FONT_BOLD.getSize(), FONT_BOLD.isBold(), FONT_BOLD.isItalic());
		FONT_BOLDU_GRAY.addAsciiGlyphs();
		FONT_BOLDU_GRAY.addGlyphs(400, 600);
		FONT_BOLDU_GRAY.getEffects().add(new ColorEffect(Color.GRAY));
		FONT_BOLDU_GRAY.loadGlyphs();
		
		FONT_BOLDU_WHITE = new UnicodeFont(FONT_BOLD, FONT_BOLD.getSize(), FONT_BOLD.isBold(), FONT_BOLD.isItalic());
		FONT_BOLDU_WHITE.addAsciiGlyphs();
		FONT_BOLDU_WHITE.addGlyphs(400, 600);
		FONT_BOLDU_WHITE.getEffects().add(new ColorEffect(Color.WHITE));
		FONT_BOLDU_WHITE.loadGlyphs();
		
		FONT_BOLDU_GRAY_LARGE = new UnicodeFont(FONT_BOLD, 48, FONT_BOLD.isBold(), FONT_BOLD.isItalic());
		FONT_BOLDU_GRAY_LARGE.addAsciiGlyphs();
		FONT_BOLDU_GRAY_LARGE.addGlyphs(400, 600);
		FONT_BOLDU_GRAY_LARGE.getEffects().add(new ColorEffect(Color.GRAY));
		FONT_BOLDU_GRAY_LARGE.loadGlyphs();
		
		FONT_BOLDU_WHITE_LARGE = new UnicodeFont(FONT_BOLD, 48, FONT_BOLD.isBold(), FONT_BOLD.isItalic());
		FONT_BOLDU_WHITE_LARGE.addAsciiGlyphs();
		FONT_BOLDU_WHITE_LARGE.addGlyphs(400, 600);
		FONT_BOLDU_WHITE_LARGE.getEffects().add(new ColorEffect(Color.WHITE));
		FONT_BOLDU_WHITE_LARGE.loadGlyphs();
	}
	
	public static void drawBold(Graphics g, String text, int x, int y){
		FONT_BOLDU_GRAY.drawString(x - 2, y - 2, text);
		FONT_BOLDU_WHITE.drawString(x, y, text);
	}
	
	public static void drawLarge(Graphics g, String text, int x, int y){
		FONT_BOLDU_GRAY_LARGE.drawString(x - 2, y - 2, text);
		FONT_BOLDU_WHITE_LARGE.drawString(x, y, text);
	}

}
