package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.state.StateGame;

public class EntityMud extends EntityLava{

	//List of all mud
	public static List<EntityMud> MUD = new ArrayList<EntityMud>();
	
	public EntityMud(boolean isRaw, int destX, int destY, int spawnX,
			int spawnY, int speed, int health) {
		super(isRaw, destX, destY, spawnX, spawnY, speed, health);
	
		setTexture(TextureManager.TEXTURE_MUD);
		
	}
	
	/**
	 * Spawns a new mud
	 */
	public static void spawnMud(int x, int y){
		MUD.add(new EntityMud(false, x, y, x, y, 0, 0));
	}
	
	public void doAi(){
		
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		//Check for collision with player
		if(getRectangle().intersects(StateGame.player.getRectangle())){
			StateGame.player.setHealth(StateGame.player.getHealth() - 1);
		}
	}

}
