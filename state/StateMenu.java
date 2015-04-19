package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.gui.Button;
import com.exilegl.forest.engine.input.InputManager;
import com.exilegl.forest.engine.level.LevelManager;
import com.exilegl.forest.engine.map.text.FontManager;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.sound.SoundManager;

public class StateMenu {
	
	//Our buttons
	static Button buttonLevels;
	static Button buttonQuit;
	
	static boolean firstFrame = (true);
	
	//Mouse rectangle, used for collision between level buttons
	static Rectangle mouseRect;
	
	//Rectangles for each level icon
	static Rectangle RECT_LEVEL1;
	static Rectangle RECT_LEVEL2;
	static Rectangle RECT_LEVEL3;
	
	static Rectangle RECT_LEVEL4;
	
	//Arrow right/left rectangles
	static Rectangle ARROW_LEFT;
	static Rectangle ARROW_RIGHT;
	
	//Whether or not a level is hovered
	static boolean[] HOVERED = new boolean[5];
	
	//The page that the menu is on (Used to choose which buttons to render and update)
	static int PAGE = (1);
	
	//The max amount of pages we have, used to decide whether or not to switch to a page
	static int MAX_PAGES = (3);
	
	public static boolean playHover = (false);
	public static int hoverButton = (0);
	
	public static void init(){
		
		//Initiate icon rectangles
		RECT_LEVEL1 = new Rectangle(Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_1.getWidth() * 2, Game.HEIGHT / 2, TextureManager.TEXTURE_LEVEL_1.getWidth(), TextureManager.TEXTURE_LEVEL_1.getHeight());
		
		RECT_LEVEL2 = new Rectangle(Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_2.getWidth() / 2, Game.HEIGHT / 2, TextureManager.TEXTURE_LEVEL_2.getWidth(), TextureManager.TEXTURE_LEVEL_2.getHeight());
		
		RECT_LEVEL3 = new Rectangle(Game.WIDTH / 2 + TextureManager.TEXTURE_ICON_3.getWidth(), Game.HEIGHT / 2, TextureManager.TEXTURE_ICON_3.getWidth(), TextureManager.TEXTURE_ICON_3.getHeight());
	
		
		RECT_LEVEL4 = new Rectangle(Game.WIDTH / 2 - TextureManager.TEXTURE_ICON_LEVEL4.getWidth() * 2, Game.HEIGHT / 2, TextureManager.TEXTURE_ICON_LEVEL4.getWidth() * 2, TextureManager.TEXTURE_ICON_LEVEL4.getHeight());
		RECT_LEVEL4 = new Rectangle(1,1,1,1);
		
		//Arrow right/left rectangles
		ARROW_LEFT = new Rectangle(Game.WIDTH / 8, Game.HEIGHT / 2, 20, 20);
		ARROW_RIGHT = new Rectangle(Game.WIDTH - Game.WIDTH / 8, Game.HEIGHT / 2, 20, 20);
	}

	public static void draw(Graphics g){
		//g.drawString("Menu state!", 10, 100);
		
		g.drawImage(TextureManager.TEXTURE_MENUBG, 0, 0);
		
		String text = ("Level Select");
		FontManager.drawLarge(g, text, Game.WIDTH / 2 - text.length() * 20, Game.HEIGHT / 2 - Game.HEIGHT / 3);
		
		String textBosses = ("A new boss on each level!");
		FontManager.drawBold(g, textBosses, Game.WIDTH / 2 - text.length() * 20, Game.HEIGHT / 2 - Game.HEIGHT / 3 - textBosses.length() * 3);
		
		switch(PAGE){
		case 1:
			drawPageOne(g);
			break;
		case 2:
			drawPageTwo(g);
			break;
		}
		
		//Draw right/left arrows
		FontManager.drawLarge(g, "<", Math.round(ARROW_LEFT.getX()), Math.round(ARROW_LEFT.getY()));
		FontManager.drawLarge(g, ">", Math.round(ARROW_RIGHT.getX()), Math.round(ARROW_RIGHT.getY()));
		
		int arrowBoxLeftY = (Math.round(ARROW_LEFT.getY() + 25));
		int arrowBoxRightY = (Math.round(ARROW_RIGHT.getY() + 25));
		
		Rectangle rectLeft = new Rectangle(ARROW_LEFT.getX(), arrowBoxLeftY, ARROW_LEFT.getWidth(), ARROW_LEFT.getHeight());
		Rectangle rectRight = new Rectangle(ARROW_RIGHT.getX(), arrowBoxRightY, ARROW_LEFT.getWidth(), ARROW_LEFT.getHeight());
		
		if(mouseRect != null && mouseRect.intersects(rectLeft)){
			FontManager.drawLarge(g, "<", Math.round(ARROW_LEFT.getX() - 2), Math.round(ARROW_LEFT.getY() - 2));
			if(InputManager.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				switchPage(PAGE - 1);
			}
		}
		if(mouseRect != null && mouseRect.intersects(rectRight)){
			FontManager.drawLarge(g, ">", Math.round(ARROW_RIGHT.getX() - 2), Math.round(ARROW_RIGHT.getY() - 2));
			if(InputManager.input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				switchPage(PAGE + 1);
			}
		}
		
		//I'll make buttons and such tomorrow, too tired for that right now.
	}
	
	/**
	 * Switches the page, if said page
	 * is available
	 */
	public static void switchPage(int page){
		
		SoundManager.playSound(SoundManager.ID_ACONITE);
		
		//hard coding right now for sake of time
		
		if(page > 0 && page < MAX_PAGES){
			PAGE = (page);
		}
		
		/**if(page < MAX_PAGES + 1 && page > 1){
			PAGE = (page);
		}else{
			System.out.println("Can't switch, no next page!");
		}**/
		
	}
	
	//Add sound to the menu!
	
	public static void update(){
		
		//Update mouse rect
		mouseRect = new Rectangle(Game.MOUSE_X, Game.MOUSE_Y, 10, 10);
		
		switch(PAGE){
		case 1:
			updatePageOne();
			break;
		case 2:
			updatePageTwo();
			break;
		}
		
		//Play hover sound, if applicable
		if(playHover == true){
			SoundManager.playSound(SoundManager.ID_HOVER);
			playHover = (false);
		}
		
		//Update rectangles
		RECT_LEVEL1 = new Rectangle(Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_1.getWidth() * 2, Game.HEIGHT / 2, TextureManager.TEXTURE_LEVEL_1.getWidth(), TextureManager.TEXTURE_LEVEL_1.getHeight());
		
		RECT_LEVEL2 = new Rectangle(Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_2.getWidth() / 2, Game.HEIGHT / 2, TextureManager.TEXTURE_LEVEL_2.getWidth(), TextureManager.TEXTURE_LEVEL_2.getHeight());
		
		RECT_LEVEL3 = new Rectangle(Game.WIDTH / 2 + TextureManager.TEXTURE_ICON_3.getWidth(), Game.HEIGHT / 2, TextureManager.TEXTURE_ICON_3.getWidth(), TextureManager.TEXTURE_ICON_3.getHeight());
	
		
		//RECT_LEVEL4 = new Rectangle(Game.WIDTH / 2 - TextureManager.TEXTURE_ICON_LEVEL4.getWidth() * 2, Game.HEIGHT / 2, TextureManager.TEXTURE_ICON_LEVEL4.getWidth() + Game.WIDTH, TextureManager.TEXTURE_ICON_LEVEL4.getHeight());
		RECT_LEVEL4 = new Rectangle(Game.WIDTH / 2 - TextureManager.TEXTURE_ICON_LEVEL4.getWidth(), Game.HEIGHT / 2, TextureManager.TEXTURE_ICON_LEVEL4.getWidth(), TextureManager.TEXTURE_ICON_LEVEL4.getHeight());
		//RECT_LEVEL4 = new Rectangle(1, 1, 1, 1);
		
		//Arrow right/left rectangles
		ARROW_LEFT = new Rectangle(Game.WIDTH / 25, Game.HEIGHT - Game.HEIGHT / 2 - Game.HEIGHT / 8 , 20, 20);
		ARROW_RIGHT = new Rectangle(Game.WIDTH - Game.WIDTH / 20, Game.HEIGHT - Game.HEIGHT / 2 - Game.HEIGHT / 8, 20, 20);
	
	}
	
	/**
	 * Draws page one
	 */
	public static void drawPageOne(Graphics g){
		//Draw icons
				if(HOVERED[0] == false){
					g.drawImage(TextureManager.TEXTURE_LEVEL_1, Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_1.getWidth() * 2, Game.HEIGHT / 2);
					FontManager.drawBold(g, "#1", Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_1.getWidth() * 2, Game.HEIGHT / 2);
				}else{
					g.drawImage(TextureManager.TEXTURE_LEVEL_1_HOVERED, Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_1.getWidth() * 2, Game.HEIGHT / 2);
					FontManager.drawBold(g, "#1" + LevelManager.getLocked(LevelManager.LEVEL_1), Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_1.getWidth() * 2, Game.HEIGHT / 2);
				}
				
				if(HOVERED[1] == false){
					g.drawImage(TextureManager.TEXTURE_LEVEL_2, Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_2.getWidth() / 2 - 2, Game.HEIGHT / 2);
					FontManager.drawBold(g, "#2" + LevelManager.getLocked(LevelManager.LEVEL_2), Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_2.getWidth() / 2, Game.HEIGHT / 2);
				}else{
					g.drawImage(TextureManager.TEXTURE_LEVEL_2_HOVERED, Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_2.getWidth() / 2, Game.HEIGHT / 2);
					FontManager.drawBold(g, "#2" + LevelManager.getLocked(LevelManager.LEVEL_2), Game.WIDTH / 2 - TextureManager.TEXTURE_LEVEL_2.getWidth() / 2, Game.HEIGHT / 2);
				}
				
				if(HOVERED[2] == false){
					g.drawImage(TextureManager.TEXTURE_ICON_3, Game.WIDTH / 2 + TextureManager.TEXTURE_ICON_3.getWidth() - 2, Game.HEIGHT / 2);
					FontManager.drawBold(g, "#3" + LevelManager.getLocked(LevelManager.LEVEL_2), Game.WIDTH / 2 + TextureManager.TEXTURE_ICON_3.getWidth() - 2, Game.HEIGHT / 2);
				}else{
					g.drawImage(TextureManager.TEXTURE_ICON_3_HOVERED, Game.WIDTH / 2 + TextureManager.TEXTURE_ICON_3.getWidth(), Game.HEIGHT / 2);
					FontManager.drawBold(g, "#3" + LevelManager.getLocked(LevelManager.LEVEL_2), Game.WIDTH / 2 + TextureManager.TEXTURE_ICON_3.getWidth(), Game.HEIGHT / 2);
				}
	}
	
	/**
	 * Updates page one
	 */
	public static void updatePageOne(){
		if(mouseRect.intersects(RECT_LEVEL1)){
			HOVERED[0] = (true);
			playHover = (true);
			hoverButton = (0);
			if(InputManager.input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				LevelManager.playLevel(LevelManager.LEVEL_1);
			}
		}else{
			HOVERED[0] = (false);
		}
		
		if(mouseRect.intersects(RECT_LEVEL2)){
			HOVERED[1] = (true);
			playHover = (true);
			hoverButton = (1);
			if(InputManager.input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				if(LevelManager.UNLOCKED_2 == true){
				LevelManager.playLevel(LevelManager.LEVEL_2);
				}else{
					Announcer.addAnnouncement("That's locked!", 30, Game.WIDTH / 2, Game.HEIGHT / 2);
				}
			}
		}else{
			HOVERED[1] = (false);
		}
		
		if(mouseRect.intersects(RECT_LEVEL3)){
			HOVERED[2] = (true);
			playHover = (true);
			hoverButton = (2);
			if(InputManager.input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				if(LevelManager.UNLOCKED_3 == true){
				LevelManager.playLevel(LevelManager.LEVEL_3);
				}else{
					Announcer.addAnnouncement("That's locked!", 30, Game.WIDTH / 2, Game.HEIGHT / 2);
				}
			}
		}else{
			HOVERED[2] = (false);
		}
	}
	
	/**
	 * Draw page two
	 */
	public static void drawPageTwo(Graphics g){
		if(HOVERED[3] == false){
			//g.drawImage(TextureManager.TEXTURE_ICON_LEVEL4, Game.WIDTH / 2 - TextureManager.TEXTURE_ICON_LEVEL4.getWidth() * 2, Game.HEIGHT / 2);
			//FontManager.drawBold(g, "#4" + LevelManager.getLocked(LevelManager.LEVEL_1), Game.WIDTH / 2 - TextureManager.TEXTURE_ICON_LEVEL4.getWidth() * 2 - 2, Game.HEIGHT / 2);
			g.drawImage(TextureManager.TEXTURE_ICON_LEVEL4, RECT_LEVEL4.getX() - 2, RECT_LEVEL4.getY() - 2);
			FontManager.drawBold(g, "#4" + LevelManager.getLocked(LevelManager.LEVEL_1), Math.round(RECT_LEVEL4.getX() - 2), Math.round(RECT_LEVEL4.getY() - 2));
		}else{
			//g.drawImage(TextureManager.TEXTURE_ICON_LEVEL4_HOVER, Game.WIDTH / 2 - TextureManager.TEXTURE_ICON_LEVEL4.getWidth() * 2, Game.HEIGHT / 2);
			//FontManager.drawBold(g, "#4" + LevelManager.getLocked(LevelManager.LEVEL_1), Game.WIDTH / 2 - TextureManager.TEXTURE_ICON_LEVEL4.getWidth() * 2, Game.HEIGHT / 2);
			g.drawImage(TextureManager.TEXTURE_ICON_LEVEL4_HOVER, RECT_LEVEL4.getX(), RECT_LEVEL4.getY());
			FontManager.drawBold(g, "#4" + LevelManager.getLocked(LevelManager.LEVEL_1), Math.round(RECT_LEVEL4.getX()), Math.round(RECT_LEVEL4.getY()));
		}
	}
	
	/**
	 * Update page two
	 */
	public static void updatePageTwo(){
		
		if(mouseRect.intersects(RECT_LEVEL4)){
			HOVERED[3] = (true);
			playHover = (true);
			hoverButton = (3);
			if(InputManager.input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				LevelManager.playLevel(LevelManager.LEVEL_4);
			}
		}else{
			HOVERED[3] = (false);
		}
		
	}
	
}