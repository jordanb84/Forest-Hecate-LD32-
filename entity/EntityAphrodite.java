package com.exilegl.forest.engine.entity;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.sound.SoundManager;
import com.exilegl.forest.engine.state.EntityJelly;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateGameover;
import com.exilegl.forest.engine.state.StateManager;

public class EntityAphrodite extends Entity{

	//The entity's X destination
	private int destX;
	
	//The entity's Y destination
	private int destY;
	
	static Rectangle rect;
	
	//Whether or not the entity has been "beat"
	private boolean isBeat = (false);
	
	//The amount of frames that have past since defeating the entity
	private int framesPastSinceBeat = (0);
	
	public EntityAphrodite(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_APHRODITE);
		destX = (MathHelper.getRandom(0, Game.WIDTH));
		destY = (MathHelper.getRandom(Game.HEIGHT / 3, Game.HEIGHT / 2));
		rect = new Rectangle(destX, destY, 10, 10);
	}
	
	public void doAi(){
		
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		//Move closer to the destination
		if(getX() < getDestX())
			setX(getX() + getSpeed());
		if(getX() > getDestX())
			setX(getX() - getSpeed());
		if(getY() < getDestY())
			setY(getY() + getSpeed());
		if(getY() > getDestY())
			setY(getY() - getSpeed());
		
		//If we are at the destination, choose a new one
		if(rect.intersects(getRectangle())){
			System.out.println("Generating new destination!");
			destX = (MathHelper.getRandom(0, Game.WIDTH));
			destY = (MathHelper.getRandom(0, Game.HEIGHT / 2));
			rect = new Rectangle(destX, destY, 10, 10);
		}
		
		//Have a small chance of spawning a jelly at either end, or the center of the boss
		if(MathHelper.getRandom(0, Game.FRAMERATE / 2) == Game.FRAMERATE / 2){
			int location = (MathHelper.getRandom(0, 2));
			
			SoundManager.playSound(SoundManager.ID_APHRODITE);
			
			switch(location){
			case 0: //Bottom left
				EntityJelly.spawnJelly(getX(), getY());
				break;
			case 1: //Bottom right
				EntityJelly.spawnJelly(getX() + getTexture().getWidth(), getY());
				break;
			case 2: //Center
				EntityJelly.spawnJelly(getX() + getTexture().getWidth() / 2, getY());
				break;
			}
		}
		
		if(getHealth() < 0){
			Announcer.addAnnouncement("Apthrodite is dead! Congrats!", Game.FRAMERATE * 3, Game.WIDTH / 2, Game.HEIGHT / 2);
			setBeat(true);
			StateGame.player.setHealth(200);
		}
		
		if(isBeat == true){
			framesPastSinceBeat++;
		}
		if(isBeat == true && framesPastSinceBeat < Game.FRAMERATE * 5){
			//Spawn a bunch of kittens around it
			EntityKitten.spawnKitten();
			StateGame.player.setHealth(200);
		}
		if(isBeat == true && framesPastSinceBeat > Game.FRAMERATE * 5){
		StateGameover.resetGame(StateManager.STATE_LEVELTWO);
		}
		
		
		
	}
	
	/**
	 * Sets whether or not the entity is beat
	 * @param x
	 */
	public void setBeat(boolean beat){
		isBeat = (beat);
	}
	
	/**
	 * Gets whether or not the entity is beat
	 * @param x
	 */
	public boolean getBeat(){
		return isBeat;
	}
	
	/**
	 * Sets the amount of frames since beating the entity
	 * @param x
	 */
	public void setFramesPast(int frames){
		framesPastSinceBeat = (frames);
	}
	
	/**
	 * Returns the amount of frames since beating the entity
	 * @param x
	 */
	public int getFramesPast(){
		return framesPastSinceBeat;
	}
	
	//Sets the X destination
	public void setDestX(int x){
		destX = (x);
	}
	
	//Sets the Y destination
	public void setDestY(int y){
		destY = (y);
	}
	
	//Returns the X destination
	public int getDestX(){
		return destX;
	}
	
	//Returns the Y destination
	public int getDestY(){
		return destY;
	}
	
}
