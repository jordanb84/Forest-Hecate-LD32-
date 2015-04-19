package com.exilegl.forest.engine.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.state.StateManager;

public class Button {

	//The list of all our buttons
	public static List<Button> BUTTONS = new ArrayList<Button>();
	
	//The button's texture
	private Image TEXTURE;
	
	//The button's on click texture
	private Image TEXTURE_ACTIVE;
	
	private Image TEXTURE_HOVER;
	
	//The button's location
	private int LOCATION_X;
	private int LOCATION_Y;
	
	//The states (e.g hovered, active, normal)
	private final int STATE_NORMAL = (0);
	private final int STATE_ACTIVE = (16);
	private final int STATE_HOVERED = (32);
	
	//The text to draw on the button
	private String TEXT;
	
	//Whether the button is hovered, active, or normal
	private int state;
	
	//The state we should switch to on click
	private int switchState;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param stateId The ID of the state we should switch to on click
	 */
	public Button(int x, int y, int stateId, String text){
		switchState = (stateId);
		
		TEXTURE = (TextureManager.TEXTURE_BUTTON);
		TEXTURE_ACTIVE = (TextureManager.TEXTURE_BUTTON_ACTIVE);
		TEXTURE_HOVER = (TextureManager.TEXTURE_BUTTON_HOVER);
	}
	
	/**
	 * Draws the button
	 */
	public void draw(Graphics g){
		switch(state){
		case STATE_NORMAL:
			g.drawImage(TEXTURE, getX(), getY());
			break;
		case STATE_ACTIVE:
			g.drawImage(TEXTURE_ACTIVE, getX(), getY());
			break;
		case STATE_HOVERED:
			g.drawImage(TEXTURE_HOVER, getX(), getY());
			break;
		}
	}
	
	/**
	 * Updates the button, checking
	 * for hovers/clicks
	 */
	public void update(){
		switch(state){
		case STATE_NORMAL:
			break;
		case STATE_ACTIVE:
			StateManager.setState(switchState);
			break;
		case STATE_HOVERED:
			break;
		}
	}
	
	/**
	 * Sets the button's X
	 */
	public void setX(int x){
		LOCATION_X = (x);
	}
	
	/**
	 * Sets the button's Y
	 */
	public void setY(int y){
		LOCATION_Y = (y);
	}
	
	/**
	 * Returns the button's X
	 */
	public int getX(){
		return LOCATION_X;
	}
	
	/**
	 * Returns the button's Y
	 */
	public int getY(){
		return LOCATION_Y;
	}
	
}
