package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.EntityJelly;
import com.exilegl.forest.engine.state.StateBoss;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateManager;

public class EntityBunny extends EntityKitten{
	
	//The list of bunnies
	public static List<EntityBunny> BUNNIES = new ArrayList<EntityBunny>();
	
	//The entity's destination rectangle
	private Rectangle rect;
	
	//Whether or not the entity has reached it's destination
	private boolean reachedDestination;

	public EntityBunny(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_BUNNY1);
	}
	
	/**
	 * Spawns a new bunny
	 */
	public static void spawnBunny(EntityBunny bunny){
		BUNNIES.add(bunny);
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
			
			if(StateManager.getState() == StateManager.STATE_BOSSTWO){
				
					setDestinationX(StateGame.player.getX());
					
					setDestinationY(StateGame.player.getY());
					
					rect = new Rectangle(getDestinationX(), getDestinationY(), 10, 10);
					reachedDestination = (true);
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

}
