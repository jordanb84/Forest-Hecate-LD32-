package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.EntityJelly;
import com.exilegl.forest.engine.state.StateBossThree;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateManager;

public class EntityOddBunny extends EntityBunny{

	//The entity's destination rectangle
	private Rectangle rect;
	
	//Whether or not the entity has reached it's destination
	private boolean reachedDestination;
	
	//The list of bunnies
	public static List<EntityOddBunny> ODDBUNNIES = new ArrayList<EntityOddBunny>();
	
	public EntityOddBunny(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_ODDBUNNY);
		
		setDestinationX(MathHelper.getRandom(StateGame.player.getX() - StateGame.player.getTexture().getWidth() * 1, StateGame.player.getX() + StateGame.player.getTexture().getWidth() * 10));
		setDestinationY(MathHelper.getRandom(StateGame.player.getY() - StateGame.player.getTexture().getHeight() * 10, StateGame.player.getY() + StateGame.player.getTexture().getHeight() * 15));
		rect = new Rectangle(getDestinationX(), getDestinationY(), 10, 10);
	}
	
	/**
	 * Spawns a bunny
	 */
	public static void spawnOddBunny(EntityOddBunny bunny){
		ODDBUNNIES.add(bunny);
	}
	
	public void doAi(){
		//Have a random chance to become "scared"
		if(MathHelper.getRandom(0, Game.FRAMERATE * 5) == 5){
			setScared(true);
		}
	
	//Update rectangle
	setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
	
	rect = new Rectangle(getDestinationX(), getDestinationY(), 10, 10);
	
	if(rect.intersects(getRectangle())){
				reachedDestination = (true);
	}
	
	if(reachedDestination == false){
		//Move closer to destination
		if(getX() < getDestinationX())
			setX(getX() + getSpeed());
		if(getX() > getDestinationX())
			setX(getX() - getSpeed());
		if(getY() < getDestinationY())
			setY(getY() + getSpeed());
		if(getY() > getDestinationY())
			setY(getY() - getSpeed());
	}else{
		//Otherwise, move straight up (towards the boss)
		setY(getY() - getSpeed());
	}
	
	
	//Check for collision with boss
	if(getRectangle().intersects(StateBossThree.panes.getRectangle())){
		StateBossThree.panes.setHealth(StateBossThree.panes.getHealth() - 1);
	}
	
	}

}
