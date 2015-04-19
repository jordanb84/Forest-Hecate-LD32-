package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityEuryale;
import com.exilegl.forest.engine.entity.EntityFairy;
import com.exilegl.forest.engine.entity.EntityHauntedTree;
import com.exilegl.forest.engine.entity.EntityLava;
import com.exilegl.forest.engine.map.Map;
import com.exilegl.forest.engine.map.structure.Tree;
import com.exilegl.forest.engine.map.structure.Volcano;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;

public class StateLevelThree {
	
	//Whether or not it is the first frame of  this state
	public static boolean firstFrame = (true);
	
	public static EntityEuryale euryale;
	
	public static void draw(Graphics g){
		
		g.drawImage(TextureManager.TEXTURE_MAP_THREE, 0, 0);
		
		//Draw player
		StateGame.player.draw(g);
		
		//Draw Euryale
		if(firstFrame == false){
		euryale.draw(g);
		}
		
		//Draw all fairies
		int drawnFairies = (0);
		while(drawnFairies < EntityFairy.FAIRIES.size() && EntityFairy.FAIRIES.get(drawnFairies) != null){
			
			EntityFairy.FAIRIES.get(drawnFairies).draw(g);
			
			drawnFairies++;
		}
		
		//Draw all trees
		int drawnTrees = (0);
		while(drawnTrees < Tree.TREES.size() && Tree.TREES.get(drawnTrees) != null){
			Tree.TREES.get(drawnTrees).draw(g);
			drawnTrees++;
		}
		
		//Draw all haunted trees
		int drawnHauntedTrees = (0);
		while(drawnHauntedTrees < EntityHauntedTree.HAUNTEDTREES.size() && EntityHauntedTree.HAUNTEDTREES.get(drawnHauntedTrees) != null){
			EntityHauntedTree.HAUNTEDTREES.get(drawnHauntedTrees).draw(g);
			drawnHauntedTrees++;
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
		
		//Draw all aconites
		int drawnAconites = (0);
		try{
		while(drawnAconites < EntityAconite.ACONITES.size()){
			EntityAconite.ACONITES.get(drawnAconites).draw(g);
			drawnAconites++;
		}
		}catch(IndexOutOfBoundsException e){
		}
		
		
		
		//Draw announcer
		Announcer.drawAnnouncements(g);
		
	}
	
	public static void update(){
		
		if(firstFrame == true){
			Announcer.addAnnouncement("Watch out for lava near aconites!", 60, Game.WIDTH / 2, Game.HEIGHT / 2);
			
			//Spawn euryale mini boss
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT / 3));
			euryale = new EntityEuryale(x, y, 2, 350);
			
			//Spawn trees/mountains
			Map.generateMapThree();
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
		
		//Update euryale
		euryale.doAi();
		
		//Update all aconites
		int aconitesUpdated = (0);
		while(aconitesUpdated < EntityAconite.ACONITES.size() && EntityAconite.ACONITES.get(aconitesUpdated) != null){
			EntityAconite.ACONITES.get(aconitesUpdated).doAi();
			
			aconitesUpdated++;
		}
		
		//Update all trees
		int updatedTrees = (0);
		while(updatedTrees < Tree.TREES.size() && Tree.TREES.get(updatedTrees) != null){
			Tree.TREES.get(updatedTrees).update();
			updatedTrees++;
		}
		
		//Update all haunted trees
		int updatedHauntedTrees = (0);
		while(updatedHauntedTrees < EntityHauntedTree.HAUNTEDTREES.size() && EntityHauntedTree.HAUNTEDTREES.get(updatedHauntedTrees) != null){
			EntityHauntedTree.HAUNTEDTREES.get(updatedHauntedTrees).doAi();
			updatedHauntedTrees++;
		}
		
		//Update all lava
		int updatedLava = (0);
		while(updatedLava < EntityLava.LAVA.size() && EntityLava.LAVA.get(updatedLava) != null){
			EntityLava.LAVA.get(updatedLava).doAi();
			updatedLava++;
		}
		
		//Update all volcanos
		int updatedVolcanos = (0);
		while(updatedVolcanos < Volcano.VOLCANOS.size() && Volcano.VOLCANOS.get(updatedVolcanos) != null){
			Volcano.VOLCANOS.get(updatedVolcanos).doAi();
			updatedVolcanos++;
		}
		
		//Update announcer
		Announcer.update();
		
	}

}
