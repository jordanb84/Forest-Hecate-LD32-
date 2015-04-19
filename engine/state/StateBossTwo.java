package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.entity.EntityBunny;
import com.exilegl.forest.engine.entity.EntityHera;
import com.exilegl.forest.engine.entity.EntityKitten;
import com.exilegl.forest.engine.map.texture.TextureManager;

public class StateBossTwo {
	
	public static boolean firstFrame = (true);
	
	public static EntityHera hera;
	
	public static void draw(Graphics g){
		g.drawImage(TextureManager.TEXTURE_MAP_BOSSTWO, 0, 0);
		
		//Draw the player
		StateGame.player.draw(g);
		
		//Draw the boss
		if(firstFrame == false){
		hera.draw(g);
		}
		
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
		
		//Draw all bunnies
		int drawnBunnies = (0);
		while(drawnBunnies < EntityBunny.BUNNIES.size() && EntityBunny.BUNNIES.get(drawnBunnies) != null){
			if(EntityBunny.BUNNIES.get(drawnBunnies).getDead() == false){
				EntityBunny.BUNNIES.get(drawnBunnies).draw(g);
			}
			drawnBunnies++;
		}
		
		//Draw announcements
		Announcer.drawAnnouncements(g);
		
	}
	
	public static void update(){
		
		//Here, we will have a boss and fairies.
		
		//Draw the player
		StateGame.player.doAi();
		
		if(firstFrame == true){
			
			StateGame.player.setHealth(200);
			StateUi.KARMA = (1250);
			
			//Spawn boss
			hera = new EntityHera(Game.WIDTH / 2, Game.HEIGHT / 2 - Game.HEIGHT / 2, 2, 2000);
			
			String text = ("Hera is ready for a fight!");
			Announcer.addAnnouncement(text, 60, Game.WIDTH / 2 - text.length() * 5, Game.HEIGHT / 2);
			
			firstFrame = (false);
		}
		
		
		
		//Update the boss
		hera.doAi();
		
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
		
		//Draw all bunnies
		int updatedBunnies = (0);
		while(updatedBunnies < EntityBunny.BUNNIES.size() && EntityBunny.BUNNIES.get(updatedBunnies) != null){
			if(EntityBunny.BUNNIES.get(updatedBunnies).getDead() == false){
				EntityBunny.BUNNIES.get(updatedBunnies).doAi();
			}
			updatedBunnies++;
		}
		
		//Update announcements
		Announcer.update();
		
	}

}
