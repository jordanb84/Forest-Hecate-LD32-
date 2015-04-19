package com.exilegl.forest.engine.announcer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.map.text.FontManager;

public class Announcer2 { //Ran out of time and third boss wasn't announcing after a lot of debugging, so I made this just for it

	public static int PAST_1 = (0);
	public static int PAST_2 = (0);
	public static int PAST_3 = (0);
	public static int PAST_4 = (0);
	public static int PAST_5 = (0);
	
	public static boolean ENABLED_1 = (false);
	public static boolean ENABLED_2 = (false);
	public static boolean ENABLED_3 = (false);
	public static boolean ENABLED_4 = (false);
	public static boolean ENABLED_5 = (false);
	
	public static int DURATION_1 = (Game.FRAMERATE * 3);
	public static int DURATION_2 = (Game.FRAMERATE * 3);
	public static int DURATION_3 = (Game.FRAMERATE * 3);
	public static int DURATION_4 = (Game.FRAMERATE * 3);
	public static int DURATION_5 = (Game.FRAMERATE * 3);
	
	public static void enable(int announcement){
		switch(announcement){
		case 1:
			ENABLED_1 = (true);
			break;
		case 2:
			ENABLED_2 = (true);
			break;
		case 3:
			ENABLED_3 = (true);
			break;
		case 4:
			ENABLED_4 = (true);
			break;
		case 5:
			ENABLED_5 = (true);
			break;
		}
	}
	
	public static void draw(Graphics g){
		
		if(ENABLED_1 == true && PAST_1 < DURATION_1){
			String textOne = ("It's a bunny gone bad via karma!");
			g.setColor(Color.gray);
			FontManager.drawBold(g, textOne, Game.WIDTH / 2 - textOne.length() - 2, Game.HEIGHT / 2 - 2);
			g.setColor(Color.red);
			FontManager.drawBold(g, textOne, Game.WIDTH / 2 - textOne.length(), Game.HEIGHT / 2 );
			g.setColor(Color.white);
		}
		if(ENABLED_2 == true && PAST_2 < DURATION_2){
			String textTwo = ("Click to unleash an odd rabbit!");
			g.setColor(Color.gray);
			FontManager.drawBold(g, textTwo, Game.WIDTH / 2 - textTwo.length() - 2, Game.HEIGHT / 2 - 2);
			g.setColor(Color.red);
			FontManager.drawBold(g, textTwo, Game.WIDTH / 2 - textTwo.length(), Game.HEIGHT / 2 );
			g.setColor(Color.white);
		}
		if(ENABLED_3 == true && PAST_3 < DURATION_3){
			String textThree = ("Can you destroy this moraless beast?!");
			g.setColor(Color.gray);
			FontManager.drawBold(g, textThree, Game.WIDTH / 2 - textThree.length() - 2, Game.HEIGHT / 2 - 2);
			g.setColor(Color.red);
			FontManager.drawBold(g, textThree, Game.WIDTH / 2 - textThree.length(), Game.HEIGHT / 2 );
			g.setColor(Color.white);
		}
		if(ENABLED_4 == true && PAST_4 < DURATION_4){
			String textFour = ("Panes is dead! Great job!");
			g.setColor(Color.gray);
			FontManager.drawBold(g, textFour, Game.WIDTH / 2 - textFour.length() - 2, Game.HEIGHT / 2 - 2);
			g.setColor(Color.red);
			FontManager.drawBold(g, textFour, Game.WIDTH / 2 - textFour.length(), Game.HEIGHT / 2 );
			g.setColor(Color.white);
		}
		if(ENABLED_5 == true && PAST_5 < DURATION_5){
			String textFive = ("In this level, you must survive for a given period");
			g.setColor(Color.gray);
			FontManager.drawBold(g, textFive, Game.WIDTH / 2 - textFive.length() - 2, Game.HEIGHT / 2 - 2);
			g.setColor(Color.red);
			FontManager.drawBold(g, textFive, Game.WIDTH / 2 - textFive.length(), Game.HEIGHT / 2 );
			g.setColor(Color.white);
		}
		
	}
	
	public static void update(){
		if(ENABLED_1 == true){
			PAST_1++;
		}
		if(ENABLED_2 == true){
			PAST_2++;
		}
		if(ENABLED_3 == true){
			PAST_3++;
		}
		if(ENABLED_4 == true){
			PAST_4++;
		}
		if(ENABLED_5 == true){
			PAST_5++;
		}
	}

}
