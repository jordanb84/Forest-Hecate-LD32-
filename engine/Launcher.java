package com.exilegl.forest.engine;

import org.newdawn.slick.SlickException;

public class Launcher {

	public static void main(String args[]){
		
		//Remember to note or edit over the timelapse that the date (day) is off.
		//It's hard coded to April 17.
		
		
		
		//Initiate Slick2D game
		try {
			Game.init("Forest Hecate", Game.WIDTH, Game.HEIGHT);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		//TODO:
		//Finish levels 4, 5, and 6
		//If I have time, polishing
		//I think I'll just let the user play whatever levels they want for the sake of testing and time
		
		//When you click somewhere, a kitten is spawned on you, and it's destination set
		//to where you've clicked. If the kitty has reached it's destination, it goes up by it's speed
		//each frame
		
		
		//Going to think of ideas and be back.
		
		//So on click, we spawn a new bullet with it's destination set to the mouse's location.
		//This is how shooting will work
		
		//Now we need collision between demons
		//Testing performance first
		//TODO later when collisions are done:
		//If a demon is killed, we NEED to remove it from the array list so it won't be called anymore
		//Maybe limit demon count
		
		//I think this works.
	}
	
}
