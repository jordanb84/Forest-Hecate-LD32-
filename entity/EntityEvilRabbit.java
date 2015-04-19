package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateUi;

public class EntityEvilRabbit extends EntityDemon{

	//The list of rabbits
	public static List<EntityEvilRabbit> RABBITS = new ArrayList<EntityEvilRabbit>();
	
	private boolean isSummoned;
	
	public EntityEvilRabbit(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_EVILRABBIT);
		StateUi.KARMA = (StateUi.KARMA - 1);
	}
	
	/**
	 * Spawns a new rabbiy
	 */
	public static void spawnRabbit(EntityEvilRabbit rabbit){
		RABBITS.add(rabbit);
	}
	
	public void doAi(){
		
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		if(isSummoned == true){
			setY(getY() - getSpeed() * 2);
			setX(getX() - getSpeed() * 2);
		}
		
		if(getHealth() < 0){
			isSummoned = (true);
		}
		
		if(getX() < 0){
			kill();
		}
		
		//Move towards the player
		if(getX() < StateGame.player.getX())
			setX(getX() + getSpeed());
		if(getX() > StateGame.player.getX())
			setX(getX() - getSpeed());
		if(getY() < StateGame.player.getY())
			setY(getY() + getSpeed());
		if(getY() > StateGame.player.getY())
			setY(getY() - getSpeed());
		
		//Check for collision with the player
		if(getRectangle().intersects(StateGame.player.getRectangle())){
			StateGame.player.setHealth(StateGame.player.getHealth() - 1);
			//StateUi.KARMA = (StateUi.KARMA - 1);
			Announcer.addAnnouncement("-1", 30, getX(), getY());
		}
		
	}
	
	/**
	 * Kills the rabbit
	 */
	public void kill(){
		//RABBITS.remove(getId());
	}

}
