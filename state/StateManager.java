package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.sound.SoundManager;

public class StateManager { //Used to manage what to draw/update in a frame
	
	//State IDs, used to switch the state
	public static final int STATE_GAME = (0); //In game
	public static final int STATE_GAMEMENU = (16); //Menu over game
	public static final int STATE_MENU = (32); //Menu/title screen
	public static final int STATE_GAMEOVER = (48);
	public static final int STATE_BOSS = (64);
	public static final int STATE_LEVELTWO = (72);
	public static final int STATE_BOSSTWO = (84);
	public static final int STATE_LEVELTHREE = (94);
	public static final int STATE_BOSSTHREE = (122);
	public static final int STATE_LEVELFOUR = (138);
	public static final int STATE_BOSSFOUR = (155);
	
	//The current state to draw/update
	public static int CURRENT_STATE = (STATE_MENU); //We'll make a menu later on
	
	/**
	 * Draws the current state
	 */
	public static void drawCurrent(Graphics g){
		
		switch(getState()){
		case STATE_GAME:
			StateGame.draw(g);
			StateUi.draw(g);
			break;
		case STATE_GAMEMENU:
			StateGame.update();
			StateGameMenu.draw(g);
			break;
		case STATE_MENU:
			StateMenu.draw(g);
			break;
		case STATE_GAMEOVER:
			StateGame.draw(g);
			StateGameover.draw(g);
			break;
		case STATE_BOSS:
			StateBoss.draw(g);
			StateUi.draw(g);
			break;
		case STATE_LEVELTWO:
			StateLevelTwo.draw(g);
			StateUi.draw(g);
			break;
		case STATE_BOSSTWO:
			StateBossTwo.draw(g);
			StateUi.draw(g);
			break;
		case STATE_LEVELTHREE:
			StateLevelThree.draw(g);
			StateUi.draw(g);
			break;
		case STATE_BOSSTHREE:
			StateBossThree.draw(g);
			StateUi.draw(g);
			Announcer.drawAnnouncements(g);
			break;
		case STATE_LEVELFOUR:
			StateLevelFour.draw(g);
			StateUi.draw(g);
			break;
		case STATE_BOSSFOUR:
			StateBossFour.draw(g);
			StateUi.draw(g);
			break;
		}
		
	}
	
	/**
	 * Updates the current state
	 */
	public static void updateCurrent(){
	
		switch(getState()){
		case STATE_GAME:
			StateGame.update();
			StateUi.update();
			break;
		case STATE_GAMEMENU:
			StateGameMenu.update();
			break;
		case STATE_MENU:
			StateMenu.update();
			break;
		case STATE_GAMEOVER:
			StateGameover.update();
			break;
		case STATE_BOSS:
			StateBoss.update();
			break;
		case STATE_LEVELTWO:
			StateLevelTwo.update();
			StateUi.update();
			break;
		case STATE_BOSSTWO:
			StateBossTwo.update();
			StateUi.update();
			break;
		case STATE_LEVELTHREE:
			StateLevelThree.update();
			StateUi.update();
			break;
		case STATE_BOSSTHREE:
			StateBossThree.update();
			StateUi.update();
			Announcer.update();
			break;
		case STATE_LEVELFOUR:
			StateLevelFour.update();
			StateUi.update();
			break;
		case STATE_BOSSFOUR:
			StateBossFour.update();
			StateUi.update();
			break;
		}
		
	}
	
	/**
	 * Gets the current state
	 */
	public static int getState(){
		return CURRENT_STATE;
	}
	
	/**
	 * Sets the state
	 */
	public static void setState(int state){
		if(state == STATE_MENU){
			SoundManager.playSound(SoundManager.ID_MENU);
		}
		if(state == STATE_GAMEOVER){
			SoundManager.playSound(SoundManager.ID_GAMEOVER);
		}
		CURRENT_STATE = (state);
	}
	
}
