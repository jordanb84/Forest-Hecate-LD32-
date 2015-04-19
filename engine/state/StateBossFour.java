package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityMud;
import com.exilegl.forest.engine.entity.EntityPig;
import com.exilegl.forest.engine.entity.EntitySirens;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;

public class StateBossFour {
	
	public static boolean firstFrame = (true);
	
	public static EntitySirens sirens;
	public static boolean announced2 = (false);
	public static boolean announced3 = (false);
	public static int framesPast = (0);
	public static boolean doneFramesPast = (false);
	
	public static void draw(Graphics g){
		
		g.drawImage(TextureManager.TEXTURE_MAP_LEVEL4_BOSS, 0, 0);
		
		//Draw player
		StateGame.player.draw(g);
		
		//Draw boss
		if(firstFrame == false){
		sirens.draw(g);
		}
		
		//Draw all mud
		int drawnMud = (0);
		while(drawnMud < EntityMud.MUD.size()){
			
			EntityMud.MUD.get(drawnMud).draw(g);
			
			drawnMud++;
		}
		
		//Draw all pigs
		int drawPigs = (0);
		while(drawPigs < EntityPig.PIGS.size()){
			
			EntityPig.PIGS.get(drawPigs).draw(g);
			
			drawPigs++;
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
		
		Announcer.drawAnnouncements(g);
		
	}
	
	public static void update(){
		framesPast++;
		
		if(firstFrame == true){
			String message = ("Avoid pig's mud at all costs and survive the wave!");
			Announcer.addAnnouncement(message, Game.FRAMERATE * 5, Game.WIDTH / 2 - message.length(), Game.HEIGHT / 2);
			
			//Spawn boss
			sirens = new EntitySirens(MathHelper.getRandom(0, Game.WIDTH), MathHelper.getRandom(0, Game.HEIGHT / 3), 3, 150);
			
			firstFrame = (false);
		}
		if(announced2 == false && framesPast > Game.FRAMERATE * 5){
			StateUi.ACONITE_COUNT = (3);
			String message = ("Use your few Aconite if you need to!");
			Announcer.addAnnouncement(message, Game.FRAMERATE * 5, Game.WIDTH / 2 - message.length(), Game.HEIGHT / 2);
			announced2 = (true);
		}
		if(announced3 == false && framesPast > Game.FRAMERATE * 10){
			String message = ("The pigs WILL try to target you with mud!");
			Announcer.addAnnouncement(message, Game.FRAMERATE * 10, Game.WIDTH / 2 - message.length(), Game.HEIGHT / 2);
			announced3 = (true);
		}
		
		if(sirens.getHealth() < 0){
			if(doneFramesPast == false){
			framesPast = (0); //for new counter
			doneFramesPast = (true);
			}
			
			String message = ("Congrats, you're smarter than the pigs!");
			Announcer.addAnnouncement(message, Game.FRAMERATE * 5, Game.WIDTH / 2 - message.length(), Game.HEIGHT / 2);
			
			
			if(framesPast > Game.FRAMERATE * 6){
				StateGameover.resetGame(StateManager.STATE_MENU);
			}
			
			StateGame.player.setHealth(200);
		}
		
		//Update player
		StateGame.player.doAi();
		
		//Update boss
		sirens.doAi();
		
		//Update all pigs
		int updatedPigs = (0);
		while(updatedPigs < EntityPig.PIGS.size()){
			
			EntityPig.PIGS.get(updatedPigs).doAi();
			
			updatedPigs++;
		}
		
		//Update all mud
		int updatedMud = (0);
		while(updatedMud < EntityMud.MUD.size()){
			
			EntityMud.MUD.get(updatedMud).doAi();
			
			updatedMud++;
		}
		
		//Update all aconites
		int aconitesUpdated = (0);
		while(aconitesUpdated < EntityAconite.ACONITES.size() && EntityAconite.ACONITES.get(aconitesUpdated) != null){
			EntityAconite.ACONITES.get(aconitesUpdated).doAi();
			
			aconitesUpdated++;
		}
		
		Announcer.update();
		
	}

}
