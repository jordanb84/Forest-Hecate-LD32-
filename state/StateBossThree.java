package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.announcer.Announcer2;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityDemon;
import com.exilegl.forest.engine.entity.EntityFairy;
import com.exilegl.forest.engine.entity.EntityOddBunny;
import com.exilegl.forest.engine.entity.EntityPanes;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;

public class StateBossThree {
	
	//Our panes boss
	public static EntityPanes panes;
	
	public static boolean firstFrame = (true);
	public static int frameNumber = (0);
	
	public static void draw(Graphics g){
		
		g.drawImage(TextureManager.TEXTURE_MAP_BOSS3, 0, 0);
		
		StateGame.player.draw(g);
		
		//Draw all jelly
		int drawnJelly = (0);
		while(drawnJelly < EntityJelly.JELLY.size() && EntityJelly.JELLY.get(drawnJelly) != null){
			if(EntityJelly.JELLY.get(drawnJelly).getDead() == false){
			EntityJelly.JELLY.get(drawnJelly).draw(g);
			}
			
			drawnJelly++;
		}
		
		
		
		//Draw all demons
		int drawnDemons = (0);
		try{
		while(drawnDemons < EntityDemon.DEMONS.size()){
			EntityDemon.DEMONS.get(drawnDemons).draw(g);
			drawnDemons++;
		}
		}catch(IndexOutOfBoundsException e){
		}
		
		//Draw all fairies
		int drawnFairies = (0);
		while(drawnFairies < EntityFairy.FAIRIES.size() && EntityFairy.FAIRIES.get(drawnFairies) != null){
			
			EntityFairy.FAIRIES.get(drawnFairies).draw(g);
			
			drawnFairies++;
		}
		
		//Draw all bunnies
		int drawnBunnies = (0);
			while(drawnBunnies < EntityOddBunny.ODDBUNNIES.size()){
				EntityOddBunny.ODDBUNNIES.get(drawnBunnies).draw(g);
				drawnBunnies++;
			}
		
		
		//Draw all aconites
		int drawnAconites = (0);
		try{
		while(drawnAconites < EntityAconite.ACONITES.size()){
			EntityAconite.ACONITES.get(drawnAconites).draw(g);
			drawnAconites++;
		}
		}catch(IndexOutOfBoundsException e){
		}
		
		//Draw panes
		if(firstFrame == false){
		panes.draw(g);
		}
		
		//Draw announcer
		Announcer.drawAnnouncements(g);
		
		Announcer2.draw(g);
		
	}

	public static void update(){
	
		StateGame.player.doAi();
		
		Announcer2.update();
		
		if(firstFrame == true){
			
			//Initiate pane
			panes = new EntityPanes(MathHelper.getRandom(0, Game.WIDTH), MathHelper.getRandom(0, Game.HEIGHT / 2), 3, 4750);
			firstFrame = (false);
			EntityFairy.FAIRIES.clear();
			StateGame.player.setHealth(200);
			panes.setDestX(MathHelper.getRandom(0, Game.WIDTH));
			panes.setDestY(MathHelper.getRandom(0, Game.HEIGHT / 2));
		}
		
		frameNumber++;
		
		//System.out.println(frameNumber);
		if(frameNumber == 1){
			//String infoText = ("It's a bunny gone bad via karma!");
			//Announcer.addAnnouncement(infoText, Game.FRAMERATE * 5, Game.WIDTH / 2 - infoText.length(), Game.HEIGHT / 2);
			System.out.println("Added announcement 1");
			Announcer2.enable(1);
			//Maybe this announcement is disabled?
		}
		if(frameNumber == Game.FRAMERATE * 5){
			//String infoText = ("Click to unleash an odd rabbit!");
			//Announcer.addAnnouncement(infoText, Game.FRAMERATE * 5, Game.WIDTH / 2 - infoText.length(), Game.HEIGHT / 2);
			Announcer2.enable(2);
		}
		if(frameNumber == Game.FRAMERATE * 10){
			//String infoText = ("Can you destroy this moraless beast?!");
			//Announcer.addAnnouncement(infoText, Game.FRAMERATE * 5, Game.WIDTH / 2 - infoText.length(), Game.HEIGHT / 2);
			Announcer2.enable(3);
		}
		
		//System.out.println(frameNumber);

		/**if(frameNumber == 1){
			Announcer.addAnnouncement("Avoid lava!", 60, Game.WIDTH / 2 - 10 * 5, Game.HEIGHT / 2);
			System.out.println("Added announcement!");
		}
		if(frameNumber == Game.FRAMERATE * 5){
			String summonText = ("Defeat the fairies to raise karma and ascend to the Goddess!");
			Announcer.addAnnouncement(summonText, Game.FRAMERATE * 5, Game.WIDTH / 2 - summonText.length() * 5, Game.HEIGHT / 2);
		}**/
		
		//Update panes
		panes.doAi();
		
		//Update all jelly
		int updatedJelly = (0);
		while(updatedJelly < EntityJelly.JELLY.size() && EntityJelly.JELLY.get(updatedJelly) != null){
			if(EntityJelly.JELLY.get(updatedJelly).getDead() == false){
			EntityJelly.JELLY.get(updatedJelly).doAi();
			}
			
			updatedJelly++;
		}
		
		//Update all bunnies
		int updatedBunnies = (0);
			while(updatedBunnies < EntityOddBunny.ODDBUNNIES.size()){
				EntityOddBunny.ODDBUNNIES.get(updatedBunnies).doAi();
				updatedBunnies++;
			}
		
		//Update all demons
		int drawnDemons = (0);
		try{
		while(drawnDemons < EntityDemon.DEMONS.size() && EntityDemon.DEMONS.get(drawnDemons) != null){
			EntityDemon.DEMONS.get(drawnDemons).doAi();
			drawnDemons++;
		}
		}catch(IndexOutOfBoundsException e){
			//We've drawn all of these, so stop until next frame
		}
		
		//Update all fairies
		int updatedFairies = (0);
		while(updatedFairies < EntityFairy.FAIRIES.size() && EntityFairy.FAIRIES.get(updatedFairies) != null){
			
			EntityFairy.FAIRIES.get(updatedFairies).doAi();
			
			updatedFairies++;
		}
		
		//Update all aconites
		int aconitesUpdated = (0);
		while(aconitesUpdated < EntityAconite.ACONITES.size() && EntityAconite.ACONITES.get(aconitesUpdated) != null){
			EntityAconite.ACONITES.get(aconitesUpdated).doAi();
			
			aconitesUpdated++;
		}
		
		//Update announcer
		Announcer.update();
		
	}
	
}
