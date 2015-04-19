package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.StateGame;

public class EntityHauntedTree extends EntityFairy{

	//The list of all haunted trees
	public static List<EntityHauntedTree> HAUNTEDTREES = new ArrayList<EntityHauntedTree>();
	
	public EntityHauntedTree(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_HAUNTEDTREE);
	}
	
	public void doAi(){
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		//Have a chance to move towards the player
			//Move towards the player
			if(getX() < StateGame.player.getX())
				setX(getX() + getSpeed());
			if(getX() > StateGame.player.getX())
				setX(getX() - getSpeed());
			if(getY() < StateGame.player.getY())
				setY(getY() + getSpeed());
			if(getY() > StateGame.player.getY())
				setY(getY() - getSpeed());
		
		if(getSummoned() == true){
			setY(getY() - getSpeed() * 2);
			setX(getX() - getSpeed() * 2);
		}
		
		if(getHealth() < 0){
			setSummoned(true);
		}
		
		if(getX() < 0){
			kill();
		}
		
		//Check for collision with player
		int collisionsChecked = (0);
		while(collisionsChecked < HAUNTEDTREES.size()){
			if(getRectangle().intersects(StateGame.player.getRectangle()) && MathHelper.getRandom(1, Game.FRAMERATE * 3) == 10){
				StateGame.player.setHealth(StateGame.player.getHealth() - 1);
				Announcer.addAnnouncement("-1", 60, StateGame.player.getX(), StateGame.player.getY());
			}
			collisionsChecked++;
		}
	}
	
	/**
	 * Spawns a tree
	 */
	public static void spawnHauntedTree(EntityHauntedTree hauntedTree){
		HAUNTEDTREES.add(hauntedTree);
	}
	
	/**
	 * Kills the tree
	 */
	public void kill(){
		HAUNTEDTREES.remove(getId());
	}

}
