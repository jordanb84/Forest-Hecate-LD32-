package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.announcer.Announcer2;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityEuryale;
import com.exilegl.forest.engine.entity.EntityEvilRabbit;
import com.exilegl.forest.engine.map.Map;
import com.exilegl.forest.engine.map.structure.RabbitHole;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;

public class StateLevelFour {

	//Whether or not this is the first frame of this state
	public static boolean firstFrame = (true);
	
	public static void draw(Graphics g){
		
		//Idea: At the start of level four an on, you get this NPC helper who helps you in levels 4-6
		
		g.drawImage(TextureManager.TEXTURE_MAP4, 0, 0);
		
		//Draw all rabbits
		int drawnRabbits = (0);
		while(drawnRabbits < EntityEvilRabbit.RABBITS.size() && EntityEvilRabbit.RABBITS.get(drawnRabbits) != null){	
			EntityEvilRabbit.RABBITS.get(drawnRabbits).draw(g);
			drawnRabbits++;
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
		
		//Draw all rabbit holes
		int drawnHoles = (0);
		while(drawnHoles < RabbitHole.RABBITHOLES.size() && RabbitHole.RABBITHOLES.get(drawnHoles) != null){	
			RabbitHole.RABBITHOLES.get(drawnHoles).draw(g);
			drawnHoles++;
		}
		
		//Draw player
		StateGame.player.draw(g);
		
		//Draw annnouncer
		Announcer.drawAnnouncements(g);
		
		Announcer2.draw(g);
	}
	
	public static void update(){
		
		Announcer2.update();
		
		if(firstFrame == true){
			StateUi.KARMA = (250);
			//String text = ("In this level, you must survive for a given period");
			//Announcer.addAnnouncement(text, Game.FRAMERATE * 3, Game.WIDTH / 2 - text.length() * 5, Game.HEIGHT / 2);
			Announcer2.enable(5);
			Map.generateMapFour();
			firstFrame = (false);
		}
		
		if(StateUi.KARMA < 0){
			StateGameover.resetGame(StateManager.STATE_BOSSFOUR);
		}
		
		//Update player
		StateGame.player.doAi();
		
		//Update all rabbits
		int updatedRabbits = (0);
		while(updatedRabbits < EntityEvilRabbit.RABBITS.size() && EntityEvilRabbit.RABBITS.get(updatedRabbits) != null){	
			EntityEvilRabbit.RABBITS.get(updatedRabbits).doAi();
			updatedRabbits++;
		}
		
		//Update all aconites
		int aconitesUpdated = (0);
		while(aconitesUpdated < EntityAconite.ACONITES.size() && EntityAconite.ACONITES.get(aconitesUpdated) != null){
			EntityAconite.ACONITES.get(aconitesUpdated).doAi();
			
			aconitesUpdated++;
		}
		
		//Update all rabbit holes
		int updatedHoles = (0);
		while(updatedHoles < RabbitHole.RABBITHOLES.size() && RabbitHole.RABBITHOLES.get(updatedHoles) != null){	
			RabbitHole.RABBITHOLES.get(updatedHoles).update();
			updatedHoles++;
		}
		
		//Update announcer
		Announcer.update();
		
	}
	
}
