package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.entity.EntityAphrodite;
import com.exilegl.forest.engine.entity.EntityKitten;
import com.exilegl.forest.engine.map.texture.TextureManager;

public class StateBoss {
	
	//Whether or not this is the first frame we've been on this stage
	static boolean firstFrame = (true);
	
	//The boss entity
	public static EntityAphrodite aphrodite;
	
	public static int frameNumber = (0);
	
	public static void draw(Graphics g){
		
		g.drawImage(TextureManager.TEXTURE_MAP_ALIEN, 0, 0);
		
		//Draw the player
		StateGame.player.draw(g);
		
		//Draw all jelly
		int drawnJelly = (0);
		while(drawnJelly < EntityJelly.JELLY.size() && EntityJelly.JELLY.get(drawnJelly) != null){
			if(EntityJelly.JELLY.get(drawnJelly).getDead() == false){
			EntityJelly.JELLY.get(drawnJelly).draw(g);
			}
			
			drawnJelly++;
		}
		
		//Draw all kittens
		int drawnKittens = (0);
		while(drawnKittens < EntityKitten.KITTENS.size() && EntityKitten.KITTENS.get(drawnKittens) != null){
			if(EntityKitten.KITTENS.get(drawnKittens).getDead() == false){
				EntityKitten.KITTENS.get(drawnKittens).draw(g);
			}
			drawnKittens++;
		}
		
		//Draw the boss
		if(firstFrame == false){
		aphrodite.draw(g);
		}
		
		
		
		Announcer.drawAnnouncements(g);
		
	}
	
	public static void update(){
		
		//If this is the first frame since getting to this stage, spawn the boss, generate Aconite poison pits, etc
		if(firstFrame == true){
		init();
		firstFrame = (false);
		}
		
		frameNumber++;
		
		if(frameNumber == Game.FRAMERATE * 1){
			String infoText = ("Avoid the Aphrodite's jellies!");
			Announcer.addAnnouncement(infoText, Game.FRAMERATE * 5, Game.WIDTH / 2 - infoText.length(), Game.HEIGHT / 2);
		}
		if(frameNumber == Game.FRAMERATE * 5){
			String infoText = ("Click to unleash a kitten!");
			Announcer.addAnnouncement(infoText, Game.FRAMERATE * 5, Game.WIDTH / 2 - infoText.length(), Game.HEIGHT / 2);
		}
		if(frameNumber == Game.FRAMERATE * 10){
			String infoText = ("What's more unconventional than a shy cat?!");
			Announcer.addAnnouncement(infoText, Game.FRAMERATE * 5, Game.WIDTH / 2 - infoText.length(), Game.HEIGHT / 2);
		}
		
		//Update the player
		StateGame.player.doAi();
		
		//Update the boss
		aphrodite.doAi();
		
		//Update all jelly
		int updatedJelly = (0);
		while(updatedJelly < EntityJelly.JELLY.size() && EntityJelly.JELLY.get(updatedJelly) != null){
			if(EntityJelly.JELLY.get(updatedJelly).getDead() == false){
			EntityJelly.JELLY.get(updatedJelly).doAi();
			}
			
			updatedJelly++;
		}
		
		//Update all kittens
		int updatedKittens = (0);
		while(updatedKittens < EntityKitten.KITTENS.size() && EntityKitten.KITTENS.get(updatedKittens) != null){
			if(EntityKitten.KITTENS.get(updatedKittens).getDead() == false){
				EntityKitten.KITTENS.get(updatedKittens).doAi();
			}
			updatedKittens++;
		}
	
		Announcer.update();
		
	}
	
	/**
	 * Spawns  the boss, actonite poison pits,
	 * etc
	 */
	public static void init(){
		
		//Spawn boss
		aphrodite = new EntityAphrodite(Game.WIDTH / 2, Game.HEIGHT / 2, 3, 750);
		
		StateGame.player.setHealth(200);
		
	}

}
