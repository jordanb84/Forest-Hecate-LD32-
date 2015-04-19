package com.exilegl.forest.engine.entity;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.announcer.Announcer2;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.sound.SoundManager;
import com.exilegl.forest.engine.state.EntityJelly;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateGameover;
import com.exilegl.forest.engine.state.StateManager;

public class EntityPanes extends EntityEuryale{

	//Whether or not the entity has been "beat"
	private boolean isBeat = (false);
	
	//The amount of frames that have past since defeating the entity
	private int framesPastSinceBeat = (0);
	
	public EntityPanes(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_BOSS_PANES);
		setDestX(MathHelper.getRandom(0, Game.WIDTH));
		setDestY(MathHelper.getRandom(0, Game.HEIGHT / 2));
	}
	
	/**
	 * Update the AI, spawning demons, fairies,
	 * and occasionally jelly. Very challenging and fast moving boss.
	 */
	public void doAi(){
		//Update rectangle
				setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
				rect = new Rectangle(getDestX(), getDestY(), 10, 10);
				
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
					System.out.println("New destination");
					setDestX(MathHelper.getRandom(0, Game.WIDTH));
					setDestY(MathHelper.getRandom(0, Game.HEIGHT / 2));
					rect = new Rectangle(getDestX(), getDestY(), 10, 10);
				}
				
				//Have a small chance of spawning a fairy, demon, or jelly at either end, or the center of the boss
				if(MathHelper.getRandom(0, Game.FRAMERATE / 5) == Game.FRAMERATE / 5){
					int location = (MathHelper.getRandom(0, 3));
					SoundManager.playSound(SoundManager.ID_APHRODITE);
					
					switch(location){
					
					case 1: //Bottom left
						if(MathHelper.getRandom(2, 4) == 4){
						EntityFairy.spawnFairy(new EntityFairy(getX(), getY(), 2, 25));
						}
						break;
					case 2: //Bottom right
						//EntityFairy.spawnFairy(new EntityFairy(getX() + getTexture().getWidth(), getY(), 2, 25));
						if(MathHelper.getRandom(2, 4) == 4){
						EntityDemon.spawn(new EntityDemon(getX() + getTexture().getWidth(), getY(), 2, 25));
						}
						break;
					case 3: //Center
						//EntityFairy.spawnFairy(new EntityFairy(getX() + getTexture().getWidth() / 2, getY(), 2, 25));
						EntityJelly.spawnJelly(getX() + getTexture().getWidth() / 2, getY());
						break;
					}
				}
				
				
				if(getHealth() < 0){
					//Announcer.addAnnouncement("Panes is dead! Great job!", Game.FRAMERATE * 3, Game.WIDTH / 2, Game.HEIGHT / 2);
					Announcer2.enable(4);
					isBeat = (true);
					StateGame.player.setHealth(200);
				}
				
				if(isBeat == true){
					framesPastSinceBeat++;
				}
				if(isBeat == true && framesPastSinceBeat < Game.FRAMERATE * 8){
					//Spawn a bunch of kittens around it
					
					int updatedBunnies = (0);
					while(updatedBunnies < EntityOddBunny.ODDBUNNIES.size()){
						EntityOddBunny.ODDBUNNIES.get(updatedBunnies).setDestinationX(getX());
						EntityOddBunny.ODDBUNNIES.get(updatedBunnies).setDestinationY(getY());
						EntityOddBunny.ODDBUNNIES.get(updatedBunnies).setSpeed(4);
						updatedBunnies++;
					}
					
					StateGame.player.setHealth(200);
					
					setSpeed(5);
				}
				if(isBeat == true && framesPastSinceBeat > Game.FRAMERATE * 8){
				StateGameover.resetGame(StateManager.STATE_LEVELFOUR);
				}
				
				//if this is too performance breaking (test it), we can just have one random check,
				//spawning a demon if left, fairy if right, and jelly if center
				//Actually, let's do that
	}

}
