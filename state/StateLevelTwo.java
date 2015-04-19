package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityFairy;
import com.exilegl.forest.engine.entity.EntityLava;
import com.exilegl.forest.engine.map.Map;
import com.exilegl.forest.engine.map.structure.Tree;
import com.exilegl.forest.engine.map.structure.Volcano;
import com.exilegl.forest.engine.map.texture.TextureManager;

public class StateLevelTwo {

	//Whether or not this is the first frame since starting the level
	public static boolean firstFrame = (true);
	
	//The current frame the state is on
	public static int frameNumber = (0);
	
	public static void draw(Graphics g){
		
		//Draw background/map
		g.drawImage(TextureManager.TEXTURE_MAP_LEVEL2, 0, 0);
		
		//Draw player
		StateGame.player.draw(g);
		
		//Draw all fairies
		int drawnFairies = (0);
		while(drawnFairies < EntityFairy.FAIRIES.size() && EntityFairy.FAIRIES.get(drawnFairies) != null){
			
			EntityFairy.FAIRIES.get(drawnFairies).draw(g);
			
			drawnFairies++;
		}
		
		//Draw all volcanos
		int drawnVolcanos = (0);
		while(drawnVolcanos < Volcano.VOLCANOS.size() && Volcano.VOLCANOS.get(drawnVolcanos) != null){
			Volcano.VOLCANOS.get(drawnVolcanos).draw(g);
			drawnVolcanos++;
		}
		
		//Draw all lava
		int drawnLava = (0);
		while(drawnLava < EntityLava.LAVA.size() && EntityLava.LAVA.get(drawnLava) != null){
			EntityLava.LAVA.get(drawnLava).draw(g);
			drawnLava++;
		}
		
		//Draw all trees
		int drawnTrees = (0);
		while(drawnTrees < Tree.TREES.size() && Tree.TREES.get(drawnTrees) != null){
			Tree.TREES.get(drawnTrees).draw(g);
			drawnTrees++;
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
		
		//Draw announcement
		Announcer.drawAnnouncements(g);
		
	}
	
	public static void update(){
	
		frameNumber++;
		
		if(frameNumber == 1){
			Announcer.addAnnouncement("Avoid lava!", Game.FRAMERATE * 5, Game.WIDTH / 2, Game.HEIGHT / 2);
		}
		if(frameNumber == Game.FRAMERATE * 5){
			String summonText = ("Defeat the fairies to raise karma and ascend to the Goddess!");
			Announcer.addAnnouncement(summonText, Game.FRAMERATE * 5, Game.WIDTH / 2 - summonText.length() * 5, Game.HEIGHT / 2);
		}
		
		//If this is the first frame, generate volcanos and trees
		if(firstFrame == true){
			
			//Generate volcanos and trees
			Map.generateMapTwo();
			
			firstFrame = (false);
		}
		
		
		
		//Update player
		StateGame.player.doAi();
		
		//Update all fairies
		int updatedFairies = (0);
		while(updatedFairies < EntityFairy.FAIRIES.size() && EntityFairy.FAIRIES.get(updatedFairies) != null){
			
			EntityFairy.FAIRIES.get(updatedFairies).doAi();
			
			updatedFairies++;
		}
		
		//Update all volcanos
		int updatedVolcanos = (0);
		while(updatedVolcanos < Volcano.VOLCANOS.size() && Volcano.VOLCANOS.get(updatedVolcanos) != null){
			Volcano.VOLCANOS.get(updatedVolcanos).doAi();
			updatedVolcanos++;
		}
		
		//Update all lava
		int updatedLava = (0);
		while(updatedLava < EntityLava.LAVA.size() && EntityLava.LAVA.get(updatedLava) != null){
			EntityLava.LAVA.get(updatedLava).doAi();
			updatedLava++;
		}
		
		//Update all trees
		int updatedTrees = (0);
		while(updatedTrees < Tree.TREES.size() && Tree.TREES.get(updatedTrees) != null){
			Tree.TREES.get(updatedTrees).update();
			updatedTrees++;
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
