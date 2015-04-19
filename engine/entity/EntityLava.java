package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.state.StateGame;

public class EntityLava extends EntityAconite{
	
	//List of all lava
	public static List<EntityLava> LAVA = new ArrayList<EntityLava>();
	
	//The entity's rectangle
	private Rectangle rect;

	public EntityLava(boolean isRaw, int destX, int destY, int spawnX,
			int spawnY, int speed, int health) {
		super(isRaw, destX, destY, spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_LAVA);
		
	}
	
	public void doAi(){
		if(getX() < getDestinationX())
			setX(getX() + 1);
		if(getX() > getDestinationX())
			setX(getX() - 1);
		if(getY() < getDestinationY())
			setY(getY() + 1);
		if(getY() > getDestinationY())
			setY(getY() - 1);
		
		//Update rectangle
		rect = new Rectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		//Check for collision with player
		if(rect.intersects(StateGame.player.getRectangle())){
			StateGame.player.setHealth(StateGame.player.getHealth() - 1);
			Announcer.addAnnouncement("-1", 60, StateGame.player.getX(), StateGame.player.getY());
		}
		
	}
	
	public static void spawnLava(EntityLava lava){
		LAVA.add(lava);
		System.out.println("Spawned lava");
	}

}
