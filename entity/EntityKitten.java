package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.EntityJelly;
import com.exilegl.forest.engine.state.StateBoss;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateManager;

public class EntityKitten extends Entity{
	
	//List of all kitties
	public static List<EntityKitten> KITTENS = new ArrayList<EntityKitten>();
	
	//Whether or not the kitty is dead
	private boolean DEAD = (false);
	
	//Whether or not the kitten is running (Scared)
	private boolean SCARED;
	
	//The entity's destination rectangle
	private Rectangle rect;
	
	//The destination to move towards if raw, typically set to the bottom of the tree
	private int destX;
	private int destY;
	
	//Whether or not the entity has reached it's destination
	private boolean reachedDestination;

	public EntityKitten(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		if(MathHelper.getRandom(2, 4) == 3){
			setTexture(TextureManager.TEXTURE_KITTY1);
		}else{
			setTexture(TextureManager.TEXTURE_KITTY2);
		}
		
		setDestinationX(MathHelper.getRandom(StateGame.player.getX() - StateGame.player.getTexture().getWidth() * 1, StateGame.player.getX() + StateGame.player.getTexture().getWidth() * 10));
		setDestinationY(MathHelper.getRandom(StateGame.player.getY() - StateGame.player.getTexture().getHeight() * 10, StateGame.player.getY() + StateGame.player.getTexture().getHeight() * 15));
		rect = new Rectangle(destX, destY, 10, 10);
	}
	
	/**
	 * Draws the tree
	 */
	public void draw(Graphics g){
		g.drawImage(getTexture(), getX(), getY());
		drawHealth(g);
		if(getScared() == true){
			g.setColor(Color.gray);
			g.drawString("SCARED!", getX() - 2, getY() - 2);
			g.setColor(Color.white);
			g.drawString("SCARED!", getX(), getY());
		}
	}
	
	/**
	 * Does the kitty AI
	 */
	public void doAi(){
		
		//Have a random chance to become "scared"
		if(StateManager.getState() == StateManager.STATE_BOSS){
		if(MathHelper.getRandom(0, Game.FRAMERATE * 3) == 1){
			setScared(true);
		}
		}else{
			if(MathHelper.getRandom(0, Game.FRAMERATE * 5) == 5){
				setScared(true);
			}
		}
		
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		if(rect.intersects(getRectangle())){
			
			if(StateManager.getState() == StateManager.STATE_BOSS){
			try{
			setDestinationX(MathHelper.getRandom(StateGame.player.getX() - StateGame.player.getTexture().getWidth() * 1, StateGame.player.getX() - StateGame.player.getTexture().getWidth() * 10));
			}catch(IllegalArgumentException e){
				setDestinationX(StateGame.player.getX() - StateGame.player.getTexture().getWidth() * MathHelper.getRandom(5, 10));
			}
			try{setDestinationY(MathHelper.getRandom(StateGame.player.getY() - StateGame.player.getTexture().getHeight() * 10, StateGame.player.getY() - StateGame.player.getTexture().getHeight() * 15));
			}catch(IllegalArgumentException e){
				setDestinationY(StateGame.player.getY() - StateGame.player.getTexture().getWidth() * MathHelper.getRandom(5, 15));
			}
			rect = new Rectangle(destX, destY, 10, 10);
			reachedDestination = (true);
			}
			
			if(StateManager.getState() == StateManager.STATE_BOSSTWO){
				
					setDestinationX(StateGame.player.getX());
					
					setDestinationY(StateGame.player.getY());
					
					rect = new Rectangle(destX, destY, 10, 10);
					reachedDestination = (true);
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSS){
		//Check for collision with Aphrodite
		if(getRectangle().intersects(StateBoss.aphrodite.getRectangle())){
			if(getScared() == false){
			StateBoss.aphrodite.setHealth(StateBoss.aphrodite.getHealth() - 1);
			if(MathHelper.getRandom(1, 10) == 10){
			Announcer.addAnnouncement("-1", 60, getX(), getY());
			}
			}
		}
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSTWO){
			//Check for collision with the player
			if(getRectangle().intersects(StateGame.player.getRectangle())){
				if(getScared() == false){
				StateGame.player.setHealth(StateGame.player.getHealth() - 1);
				if(MathHelper.getRandom(1, 10) == 10){
				Announcer.addAnnouncement("-1", 60, getX(), getY());
				}
				}
			}
		}
		
		//Check for collision with jelly
		int checkedJelly = (0);
		while(checkedJelly < EntityJelly.JELLY.size() && EntityJelly.JELLY.get(checkedJelly) != null){
			if(EntityJelly.JELLY.get(checkedJelly).getDead() == false){
				if(getRectangle().intersects(EntityJelly.JELLY.get(checkedJelly).getRectangle())){
					setHealth(getHealth() - 2);
					if(MathHelper.getRandom(1, 10) == 10){
					Announcer.addAnnouncement("-2", 60, EntityJelly.JELLY.get(checkedJelly).getX(), EntityJelly.JELLY.get(checkedJelly).getY());
					}
					}
			}
			checkedJelly++;
		}
		
		//If we're out of health, kill the entity
		if(getHealth() < 0){
			setDead(true);
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSS){
		if(reachedDestination == true){
			if(getScared() == false){
			setY(getY() - getSpeed());
			}else{
				setY(getY() - getSpeed() * 3);
				setX(getX() - getSpeed() * 3);
			}
		}else{
			if(getScared() == false){
			if(getX() < getDestinationX())
				setX(getX() + getSpeed());
			if(getX() > getDestinationX())
				setX(getX() - getSpeed());
			if(getY() < getDestinationY())
				setY(getY() + getSpeed());
			if(getY() > getDestinationY())
				setY(getY() - getSpeed());
			}else{
					setY(getY() - getSpeed() * 3);
					setX(getX() - getSpeed() * 3);
				
			}
		}
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSTWO){
			if(reachedDestination == true){
				if(getScared() == false){
				setY(getY() + getSpeed());
				}else{
					setY(getY() - getSpeed() * 3);
					setX(getX() - getSpeed() * 3);
				}
			}else{
				if(getScared() == false){
				if(getX() < getDestinationX())
					setX(getX() + getSpeed());
				if(getX() > getDestinationX())
					setX(getX() - getSpeed());
				if(getY() < getDestinationY())
					setY(getY() + getSpeed());
				if(getY() > getDestinationY())
					setY(getY() - getSpeed());
				}else{
						setY(getY() - getSpeed() * 3);
						setX(getX() - getSpeed() * 3);
					
				}
			}
		}
		
	}
	
	/**
	 * Spawns a new kitten on the player with a random
	 * health and speed
	 */
	public static void spawnKitten(){
		
		int x = (StateGame.player.getX());
		int y = (StateGame.player.getY());
		int speed = (MathHelper.getRandom(1, 2));
		int health = (MathHelper.getRandom(10, 25));
		
		EntityKitten kitten = new EntityKitten(x, y, speed, health);
		KITTENS.add(kitten);
		
		System.out.println("Spawned kitten!");
	}
	
	/**
	 * Spawns a new kitten on the player with a random
	 * health and speed
	 */
	public static void spawnKitten(int x, int y){
		int speed = (MathHelper.getRandom(1, 2));
		int health = (MathHelper.getRandom(10, 25));
		
		EntityKitten kitten = new EntityKitten(x, y, speed, health);
		KITTENS.add(kitten);
		
		System.out.println("Spawned kitten!");
	}
	
	/**
	 * Sets whether or not the jelly is dead
	 */
	public void setDead(boolean dead){
		DEAD = (dead);
	}
	
	/**
	 * Whether or not the jelly is dead
	 */
	public boolean getDead(){
		return DEAD;
	}
	
	/**
	 * Sets whether or not the kitten
	 * is scared
	 */
	public void setScared(boolean scared){
		SCARED = (scared);
	}
	
	/**
	 * Gets whether or not the kitten is scared
	 */
	public boolean getScared(){
		return SCARED;
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
