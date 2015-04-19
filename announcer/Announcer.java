package com.exilegl.forest.engine.announcer;

import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.map.text.FontManager;

public class Announcer { //Announcer, used to tell the player importat information at the beginning of a level
	
	//Arrays of all announcements and their data
	public static String[] ANNOUNCE_TEXT = new String[5120];
	public static String[] ANNOUNCE_TEXT2 = new String[5120]; //line 2
	public static int[] ANNOUNCE_LENGTH = new int[5120];
	public static int[] ANNOUNCE_DURATION = new int[5120];
	public static boolean[] ANNOUNCE_ENABLED = new boolean[5120];
	public static int[] ANNOUNCE_X = new int[5120];
	public static int[] ANNOUNCE_Y = new int[5120];
	public static boolean[] IS_DAMAGE = new boolean[5120];
	
	//hard coded for sake of time; these are for the third boss
	public static boolean show1 = (false);
	public static boolean show2 = (false);
	public static boolean show3 = (false);
	
	//The next available announcement ID
	static int nextId = (0);
	
	public static void addAnnouncement(String text, int length, int x, int y){
		try{
		ANNOUNCE_TEXT[nextId] = (text);
		ANNOUNCE_LENGTH[nextId] = (length);
		ANNOUNCE_ENABLED[nextId] = (true);
		ANNOUNCE_X[nextId] = (x);
		ANNOUNCE_Y[nextId] = (y);
		
		nextId = (nextId + 1);
		}catch(ArrayIndexOutOfBoundsException e){
			/**Arrays.fill(ANNOUNCE_TEXT, null);
			Arrays.fill(ANNOUNCE_LENGTH, null);
			Arrays.fill(ANNOUNCE_ENABLED, false);
			Arrays.fill(ANNOUNCE_X, 0);
			Arrays.fill(ANNOUNCE_Y, 0);**/
		}
	}
	
	/**
	 * Draws all currently enabled announcements
	 * at their location
	 */
	public static void drawAnnouncements(Graphics g){
		
		int checkedAnnouncements = (0);
		while(checkedAnnouncements < ANNOUNCE_TEXT.length && ANNOUNCE_TEXT[checkedAnnouncements] != null){
			if(ANNOUNCE_ENABLED[checkedAnnouncements] == true){
				if(IS_DAMAGE[checkedAnnouncements] == false){
				FontManager.drawBold(g, ANNOUNCE_TEXT[checkedAnnouncements], ANNOUNCE_X[checkedAnnouncements], ANNOUNCE_Y[checkedAnnouncements]);
				}else{
					g.setColor(Color.gray);
					g.drawString(ANNOUNCE_TEXT[checkedAnnouncements], ANNOUNCE_X[checkedAnnouncements] - 2 - ANNOUNCE_TEXT[checkedAnnouncements].length() * 2, ANNOUNCE_Y[checkedAnnouncements] - 2);
					g.setColor(Color.red);
					g.drawString(ANNOUNCE_TEXT[checkedAnnouncements], ANNOUNCE_X[checkedAnnouncements] - ANNOUNCE_TEXT[checkedAnnouncements].length() * 2, ANNOUNCE_Y[checkedAnnouncements]);
					g.setColor(Color.white);
				}
				}
			checkedAnnouncements++;
		}
		
	}
	
	/**
	 * Update announcement durations
	 * and disable them if over their length
	 */
	public static void update(){
		
		int checkedAnnouncements = (0);
		while(checkedAnnouncements < ANNOUNCE_TEXT.length && ANNOUNCE_TEXT[checkedAnnouncements] != null){
			ANNOUNCE_DURATION[checkedAnnouncements]++;
			
			if(ANNOUNCE_DURATION[checkedAnnouncements] > ANNOUNCE_LENGTH[checkedAnnouncements]){
				ANNOUNCE_ENABLED[checkedAnnouncements] = (false);
			}
			
			checkedAnnouncements++;
		}
		
	}

}
