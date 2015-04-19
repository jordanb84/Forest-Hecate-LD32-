package com.exilegl.forest.engine.level;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.exilegl.forest.engine.sound.SoundManager;
import com.exilegl.forest.engine.state.StateManager;

public class LevelManager {

	//IDs for each level, used for unlocking or getting whether a level is unlocked
	public static final int LEVEL_1 = (0);
	public static final int LEVEL_2 = (16);
	public static final int LEVEL_3 = (32);
	public static final int LEVEL_4 = (48);
	
	//Whether or not each level is unlocked (Loaded from file)
	public static boolean UNLOCKED_1 = (true);
	public static boolean UNLOCKED_2 = (true);
	public static boolean UNLOCKED_3 = (true);
	
	//Our file/content/buffered writer/file writer
	static String content;
	static File file = new File(System.getProperty("user.dir") + "/levels/leveldat.txt");
	static PrintWriter writer;
	
	/**
	 * Play a level (Sets the state, etc)
	 */
	public static void playLevel(int level){
		
		switch(level){
		case LEVEL_1:
			StateManager.setState(StateManager.STATE_GAME);
			SoundManager.playSound(SoundManager.ID_STARTLEVEL);
			break;
		case LEVEL_2:
			StateManager.setState(StateManager.STATE_LEVELTWO);
			SoundManager.playSound(SoundManager.ID_STARTLEVEL);
			break;
		case LEVEL_3:
			StateManager.setState(StateManager.STATE_LEVELTHREE);
			SoundManager.playSound(SoundManager.ID_STARTLEVEL);
			break;
		case LEVEL_4:
			StateManager.setState(StateManager.STATE_LEVELFOUR);
			SoundManager.playSound(SoundManager.ID_STARTLEVEL);
			break;
		}
		
	}
	
	/**
	 * Returns a String fitting with whether
	 * or not the level is locked
	 */
	public static String getLocked(int level){
		switch(level){
		case LEVEL_1:
			if(UNLOCKED_1 == false){
				return (" [LOCKED]");
			}
			break;
		case LEVEL_2:
			if(UNLOCKED_2 == false){
			return (" [LOCKED]");
			}
			break;		
			//Check if file exists
		default:
			return ("");
		}
		return ("");
	}
	
	/**
	 * Unlocks a level
	 * @throws IOException 
	 */
	public static void unlockLevel(int level) throws IOException{
		
		//Set level to unlocked
		switch(level){
		case LEVEL_1:
			UNLOCKED_1 = (true);
			writer.println("10345");
			break;
		case LEVEL_2:
			UNLOCKED_2 = (true);
			//writer.println("10589"); //Code for level 2 (is unlocked if file.contains("code"))
			break;
		}
		
		
	}
	
	/**
	 * Loads level unlock
	 * data from file, and if the file does
	 * not exist, writes it
	 * @throws IOException 
	 */
	public static void init() throws IOException{
		
		//writer = (new PrintWriter(System.getProperty("user.dir" + "/leveldat.txt"), "UTF-8"));
		
		
		//Check if file exists
		
	}
	
}
