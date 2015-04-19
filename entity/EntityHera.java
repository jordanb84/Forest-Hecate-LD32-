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

public class EntityHera extends EntityAphrodite{

	//Whether or not the entity has been "beat"
	private boolean isBeat = (false);
	
	//The amount of frames that have past since defeating the entity
	private int framesPastSinceBeat = (0);
	
	public EntityHera(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_HERA);
		
	}
	
	public void doAi(){
		
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		//Move closer to the destination
		if(getBeat() == false){
		if(getX() < getDestX())
			setX(getX() + getSpeed() * 2);
		if(getX() > getDestX())
			setX(getX() - getSpeed() * 2);
		if(getY() < getDestY())
			setY(getY() + getSpeed() * 2);
		if(getY() > getDestY())
			setY(getY() - getSpeed() * 2);
		}
		if(rect.intersects(getRectangle())){
			setDestX(MathHelper.getRandom(0, Game.WIDTH));
			setDestY(MathHelper.getRandom(0, Game.HEIGHT / 2));
			rect = new Rectangle(getDestX(), getDestY(), 10, 10);
		}
		
		//Have a small chance of spawning a jelly at either end, or the center of the boss
		if(getBeat() == false){
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
		}
		
		//Have a small chance of spawning a kitty at either end, or the center of the boss
		if(getBeat() == false){
		if(MathHelper.getRandom(0, Game.FRAMERATE / 2) == Game.FRAMERATE / 2){
			int location = (MathHelper.getRandom(0, 2));
			
			switch(location){
			case 0: //Bottom left
				EntityKitten.spawnKitten(getX(), getY());
				break;
			case 1: //Bottom right
				EntityKitten.spawnKitten(getX() + getTexture().getWidth(), getY());
				break;
			case 2: //Center
				EntityKitten.spawnKitten(getX() + getTexture().getWidth() / 2, getY());
				break;
			}
		}
		}
		
		//Check for collision with bunny
		int collisionsChecked = (0);
		while(collisionsChecked < EntityBunny.BUNNIES.size() && EntityBunny.BUNNIES.get(collisionsChecked) != null){
			Rectangle bunnyRect = new Rectangle(EntityBunny.BUNNIES.get(collisionsChecked).getX(), EntityBunny.BUNNIES.get(collisionsChecked).getY(), EntityBunny.BUNNIES.get(collisionsChecked).getTexture().getWidth(), EntityBunny.BUNNIES.get(collisionsChecked).getTexture().getHeight());
			
			if(getRectangle().intersects(bunnyRect)){
				setHealth(getHealth() - 1);
				Announcer.addAnnouncement("-1", 60, EntityBunny.BUNNIES.get(collisionsChecked).getX(), EntityBunny.BUNNIES.get(collisionsChecked).getY());
			}
			collisionsChecked++;
		}
		
		//Win and go to main menu if health is below 0
		if(getHealth() < 0){
			System.out.println("Won!");
			Announcer.addAnnouncement("Hera is dead! Great jo!", Game.FRAMERATE * 3, Game.WIDTH / 2, Game.HEIGHT / 2);
			isBeat = (true);
			StateGame.player.setHealth(200);
		}
		
		if(isBeat == true){
			framesPastSinceBeat++;
			System.out.println(framesPastSinceBeat);
		}
		if(isBeat == true && framesPastSinceBeat < Game.FRAMERATE * 5){
			System.out.println("he's dead, spawning things");
			//Spawn a bunch of kittens around it
			EntityBunny bunny = new EntityBunny(getX(), getY(), MathHelper.getRandom(1, 2), MathHelper.getRandom(15, 25));
			EntityBunny.spawnBunny(bunny);
			StateGame.player.setHealth(200);
		}
		if(isBeat == true && framesPastSinceBeat > Game.FRAMERATE * 5){
		StateGameover.resetGame(StateManager.STATE_LEVELTHREE);
		}
		
	}

}
