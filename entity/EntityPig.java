package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;

public class EntityPig extends EntityKitten{
	//Pigs wander around a short distrance, tracking mud occasionally when they walk. Mud is poisionous to the player

	//The list of pigs
	public static List<EntityPig> PIGS = new ArrayList<EntityPig>();
	
	//The entity's destination rectangle
	private Rectangle rect;
	
	public EntityPig(int spawnX, int spawnY, int destX, int destY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		//Pigs are typically very slow, e.g one pixel
		
		setDestinationX(destX);
		setDestinationY(destY);
		setTexture(TextureManager.TEXTURE_PIG);
	}
	
	public void doAi(){
		
		//Update rectangles
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		rect = new Rectangle(getDestinationX(), getDestinationY(), 10, 10);
		
		//Move towards the destination
		if(getX() < getDestinationX())
			setX(getX() + getSpeed());
		if(getX() > getDestinationX())
			setX(getX() - getSpeed());
		if(getY() < getDestinationY())
			setY(getY() + getSpeed());
		if(getY() > getDestinationY())
			setY(getY() - getSpeed());
		
		//If we've reached our destination, make a new one
		if(rect.intersects(getRectangle())){
			setDestinationX(MathHelper.getRandom(0, Game.WIDTH));
			setDestinationY(MathHelper.getRandom(0, Game.HEIGHT));
		}
		
		//Have a small chance to spawn mud
		if(MathHelper.getRandom(1, Game.FRAMERATE / 2) == Game.FRAMERATE / 2){
			EntityMud.spawnMud(getX(), getY());
		}
		
		if(getHealth() < 0){
			setX(getX() - getSpeed() * 3);
			setY(getY() - getSpeed() * 3);
		}
		
	}
	
	/**
	 * Spawns a pig
	 */
	public static void spawnPig(int spawnX, int spawnY, int destX, int destY, int speed, int health){
		PIGS.add(new EntityPig(spawnX, spawnY, destX, destY, speed, health));
	}
	
	/**
	 * Kills the pig
	 */
	public void kill(){
		PIGS.remove(getId());
	}

	
	
}
