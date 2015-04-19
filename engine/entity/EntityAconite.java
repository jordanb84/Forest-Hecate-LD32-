package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.sound.SoundManager;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateManager;
import com.exilegl.forest.engine.state.StateUi;

public class EntityAconite extends Entity{
	
	//The entity's list
	public static List<EntityAconite> ACONITES = new ArrayList<EntityAconite>();
	
	//Whether or not the aconite is raw (e.g should fall from tree)
	private boolean isRaw = (false); //By default, they are cooked, but ones spawned from trees are raw
	
	//The destination to move towards if raw, typically set to the bottom of the tree
	private int destX;
	private int destY;
	
	public EntityAconite(boolean isRaw, int destX, int destY, int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_ACONITE);
		setDestinationX(destX);
		setDestinationY(destY);
		setRaw(isRaw);
		setId(getNextId());
		
		if(isRaw == true){
			setTexture(TextureManager.TEXTURE_ACONITE_RAW);
		}
	}
	
	public void draw(Graphics g){
		g.drawImage(getTexture(), getX(), getY());
	}
	
	public void doAi(){
		
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		//If raw, move towards the destination
		if(getRaw() == true){
			if(getX() < getDestinationX())
				setX(getX() + 1);
			if(getX() > getDestinationX())
				setX(getX() - 1);
			if(getY() < getDestinationY())
				setY(getY() + 1);
			if(getY() > getDestinationY())
				setY(getY() - 1);
			
		}
		
		//If raw, check for collision between player
		if(getRaw() == true){
			//Check for collision with player
			if(getRectangle().intersects(StateGame.player.getRectangle())){
				setX(Game.WIDTH * 2);
				setY(Game.HEIGHT * 2);
				StateUi.ACONITE_COUNT = (StateUi.ACONITE_COUNT + 1);
			}
		}else{
			//If not raw, check for collision between demons
			
			if(StateManager.getState() == StateManager.STATE_GAME){
			int collisionsChecked = (0);
			while(collisionsChecked < EntityDemon.DEMONS.size()){
				try{
					if(EntityDemon.DEMONS.get(collisionsChecked) != null && getRectangle().intersects(EntityDemon.DEMONS.get(collisionsChecked).getRectangle())){
						EntityDemon.DEMONS.get(collisionsChecked).setHealth(-1);
						Announcer.addAnnouncement("-1", 60, getX(), getY());
						StateUi.KARMA = (StateUi.KARMA - 1);
						kill();
					}
				}catch(IndexOutOfBoundsException e){
					
				}
				collisionsChecked++;
			}
		}
		}
		
		if(StateManager.getState() == StateManager.STATE_LEVELTWO){
			int collisionsChecked = (0);
			while(collisionsChecked < EntityFairy.FAIRIES.size()){
				try{
					if(EntityFairy.FAIRIES.get(collisionsChecked) != null && getRectangle().intersects(EntityFairy.FAIRIES.get(collisionsChecked).getRectangle())){
						EntityFairy.FAIRIES.get(collisionsChecked).setHealth(-1);
						Announcer.addAnnouncement("-1", 60, getX(), getY());
						StateUi.KARMA = (StateUi.KARMA - 1);
						kill();
					}
				}catch(IndexOutOfBoundsException e){
					
				}
				collisionsChecked++;
			}
			
		}
		
		if(StateManager.getState() == StateManager.STATE_LEVELTHREE){
			int collisionsChecked = (0);
			while(collisionsChecked < EntityHauntedTree.HAUNTEDTREES.size()){
				try{
					if(EntityHauntedTree.HAUNTEDTREES.get(collisionsChecked) != null && getRectangle().intersects(EntityHauntedTree.HAUNTEDTREES.get(collisionsChecked).getRectangle())){
						EntityHauntedTree.HAUNTEDTREES.get(collisionsChecked).setHealth(-1);
						Announcer.addAnnouncement("-1", 60, getX(), getY());
						StateUi.KARMA = (StateUi.KARMA - 1);
						kill();
					}
				}catch(IndexOutOfBoundsException e){
					
				}
				collisionsChecked++;
			}
			
			int collisionsChecked2 = (0);
			while(collisionsChecked2 < EntityFairy.FAIRIES.size()){
				try{
					if(EntityFairy.FAIRIES.get(collisionsChecked2) != null && getRectangle().intersects(EntityFairy.FAIRIES.get(collisionsChecked2).getRectangle())){
						EntityFairy.FAIRIES.get(collisionsChecked2).setHealth(-1);
						kill();
					}
				}catch(IndexOutOfBoundsException e){
					
				}
				collisionsChecked2++;
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_LEVELFOUR){
			int collisionsChecked = (0);
			while(collisionsChecked < EntityEvilRabbit.RABBITS.size()){
				
				if(getRectangle().intersects(EntityEvilRabbit.RABBITS.get(collisionsChecked).getRectangle())){
					if(getRaw() == false){
					EntityEvilRabbit.RABBITS.get(collisionsChecked).setHealth(- 1);
					SoundManager.playSound(SoundManager.ID_ACONITE);
					}
				}
				
				collisionsChecked++;
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSFOUR){
			int collisionsChecked = (0);
			while(collisionsChecked < EntityPig.PIGS.size()){
				
				if(getRectangle().intersects(EntityPig.PIGS.get(collisionsChecked).getRectangle())){
					if(getRaw() == false){
						EntityPig.PIGS.get(collisionsChecked).setHealth(- 1);
						SoundManager.playSound(SoundManager.ID_ACONITE);
					}
				}
				
				collisionsChecked++;
			}
		}
	}
	
	/**
	 * Spawns a new aconite at a location
	 */
	public static void spawn(EntityAconite aconite){
		ACONITES.add(aconite);
	}
	
	/**
	 * Kills the entity, removing it from
	 * the list so it won't be checked anymore
	 */
	public void kill(){
		SoundManager.playSound(SoundManager.ID_ACONITE);
		ACONITES.remove(getId());
		System.out.println("kild");
	}
	
	/**
	 * Sets whether or not the entity is raw
	 */
	public void setRaw(boolean raw){
		isRaw = (raw);
	}
	
	/**
	 * Returns whether or not the entity is raw
	 */
	public boolean getRaw(){
		return isRaw;
	}
	
	/**
	 * Sets the destination X
	 */
	public void setDestinationX(int x){
		destX = (x);
	}
	
	/**
	 * Sets the destination Y
	 */
	public void setDestinationY(int y){
		destY = (y);
	}
	
	/**
	 * Gets the destination X
	 */
	public int getDestinationX(){
		return destX;
	}
	
	/**
	 * Gets the destination Y
	 */
	public int getDestinationY(){
		return destY;
	}

}
