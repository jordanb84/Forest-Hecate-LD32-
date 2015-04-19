package com.exilegl.forest.engine.state;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.entity.Entity;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityDemon;
import com.exilegl.forest.engine.entity.EntityPlayer;
import com.exilegl.forest.engine.map.Map;
import com.exilegl.forest.engine.map.structure.Tree;
import com.exilegl.forest.engine.map.texture.TextureManager;

public class StateGame{
	
	//The player entity
	public static EntityPlayer player;
	
	public static int frameNumber = (0);

	public static void draw(Graphics g){
		
		//Draw map
		Map.draw(g);
		
		//Draw all trees
		int treesDrawn = (0);
		while(treesDrawn < Tree.TREES.size() && Tree.TREES.get(treesDrawn) != null){
			Tree.TREES.get(treesDrawn).draw(g);
			
			treesDrawn++;
		}
		
		//Draw all demons
		int drawnDemons = (0);
		try{
		while(drawnDemons < EntityDemon.DEMONS.size()){
			EntityDemon.DEMONS.get(drawnDemons).draw(g);
			drawnDemons++;
		}
		}catch(IndexOutOfBoundsException e){
			//System.out.println("Drew " + drawnDemons + " demons this frame");
			//Stopping at #2, since we start at 0
			//We've drawn all of these, so stop until next frame
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
		
		player.draw(g);
		
		//Draw all announcements
		Announcer.drawAnnouncements(g);
		
	}
	
	public static void update(){
		
		if(frameNumber == 0){
			String text0 = ("Place Aconite by clicking!");
			Announcer.addAnnouncement(text0, Game.FRAMERATE * 5, Game.WIDTH / 2 - text0.length(), Game.HEIGHT / 2);
			StateUi.KARMA = (380);
		}
		if(frameNumber == Game.FRAMERATE * 5){
			String summonText = ("Cooked Aconite lowers demon karma so the Hecate summons them!");
			Announcer.addAnnouncement(summonText, Game.FRAMERATE * 5, Game.WIDTH / 2 - summonText.length() * 5, Game.HEIGHT / 2);
		}
		if(frameNumber == Game.FRAMERATE * 10){
			String infoText = ("Raw Aconite drops from trees and is cooked when you pick it up!");
			Announcer.addAnnouncement(infoText, Game.FRAMERATE * 5, Game.WIDTH / 2 - infoText.length() * 5, Game.HEIGHT / 2);
		}
		
		frameNumber++;
		
		Announcer.update();
		
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
		
		//Update all trees
		int treesUpdated = (0);
		while(treesUpdated < Tree.TREES.size() && Tree.TREES.get(treesUpdated) != null){
			Tree.TREES.get(treesUpdated).update();
			
			treesUpdated++;
		}
		
		//Update all aconites
		int aconitesUpdated = (0);
		while(aconitesUpdated < EntityAconite.ACONITES.size() && EntityAconite.ACONITES.get(aconitesUpdated) != null){
			EntityAconite.ACONITES.get(aconitesUpdated).doAi();
			
			aconitesUpdated++;
		}
		
		player.doAi();
		
	}
	
	/**
	 * Renders all of a specific entity
	 */
	public static void drawAll(Graphics g, ArrayList list){
		
	}
	
	/**
	 * Updates all of a specific entity
	 */
	public static void updateAll(List<Entity> list){
		int updatedEnitites = (0);
		try{
			while(updatedEnitites < list.size() && list.get(updatedEnitites) != null){
				list.get(updatedEnitites).doAi();
				updatedEnitites++;
			}
		}catch(IndexOutOfBoundsException e){
			
		}
	}
	
	//TODO: I'll have a lot of entities, so make a drawAll and updateAll method that
	//takes args to be different for each entity so I only have to write one of those
	
}
