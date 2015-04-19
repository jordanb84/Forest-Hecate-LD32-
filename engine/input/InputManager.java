package com.exilegl.forest.engine.input;

import org.newdawn.slick.Input;

import com.exilegl.forest.engine.state.StateGame;

public class InputManager { //Contains functions used to handle input
	
	public static Input input;
	
	//All of our game keys, used for later configuration
	public static int ID_MOVEUP = (Input.KEY_W);
	public static int ID_MOVEDOWN = (Input.KEY_S);
	public static int ID_MOVELEFT = (Input.KEY_A);
	public static int ID_MOVERIGHT = (Input.KEY_D);
	
	/**
	 * Polls for input
	 * @param input
	 */
	public static void poll(Input input2){
		input = (input2);
	}

}
