package com.exilegl.forest.engine.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager {

	//All of our sound IDs
	public static final int ID_ACONITE = (0);
	public static final int ID_MENU = (16);
	public static final int ID_GAMEOVER = (32);
	public static final int ID_HOVER = (48);
	public static final int ID_STARTLEVEL = (64);
	public static final int ID_APHRODITE = (82);
	
	//All of our sounds
	public static Sound SOUND_ACONITE;
	public static Sound SOUND_MENU;
	public static Sound SOUND_GAMEOVER;
	public static Sound SOUND_HOVER;
	public static Sound SOUND_STARTLEVEL;
	public static Sound SOUND_APHRODITE;
	
	/**
	 * Plays a specific sound
	 */
	public static void playSound(int sound){
		
		switch(sound){
		case ID_ACONITE:
			SOUND_ACONITE.play(0.5f, 0.5f);
			break;
		case ID_MENU:
			SOUND_MENU.play();
			break;
		case ID_GAMEOVER:
			SOUND_GAMEOVER.play();
			break;
		case ID_HOVER:
			SOUND_HOVER.play(0.5f, 0.5f);
			break;
		case ID_STARTLEVEL:
			SOUND_STARTLEVEL.play();
			break;
		case ID_APHRODITE:
			SOUND_APHRODITE.play(0.5f, 0.5f);
			break;
		}
		
	}
	
	/**
	 * Initiates all sounds
	 * @throws SlickException 
	 */
	public static void init() throws SlickException{
		SOUND_ACONITE = new Sound("res/sound/sfx_aconite.wav");
		SOUND_MENU = new Sound("res/sound/sfx_menu.wav");
		SOUND_GAMEOVER = new Sound("res/sound/sfx_gameover.wav");
		SOUND_HOVER = new Sound("res/sound/sfx_hover.wav");
		SOUND_STARTLEVEL = new Sound("res/sound/sfx_startlevel.wav");
		SOUND_APHRODITE = new Sound("res/sound/sfx_aphrodite.wav");
	}
	
}
