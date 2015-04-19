package com.exilegl.forest.engine.entity;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.sound.SoundManager;
import com.exilegl.forest.engine.state.StateGame;

public class EntitySirens extends EntityEuryale{

	public EntitySirens(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setTexture(TextureManager.TEXTURE_BOSS_SIRENS);
	}
	
	public void doAi(){
		
		//The health is randomly depleted over time. Once the health reaches 0, you win the fight/level.
		if(MathHelper.getRandom(1, Game.FRAMERATE / 2) == Game.FRAMERATE / 2){
			setHealth(getHealth() - 1);
		}
		
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
			setDestX(MathHelper.getRandom(0, Game.WIDTH));
			setDestY(MathHelper.getRandom(0, Game.HEIGHT / 2));
			rect = new Rectangle(getDestX(), getDestY(), 10, 10);
		}
		
		//Have a small chance of spawning a pig at either end, or the center of the boss
		if(MathHelper.getRandom(0, Game.FRAMERATE * 2) == Game.FRAMERATE * 2){
			int location = (MathHelper.getRandom(0, 2));
			SoundManager.playSound(SoundManager.ID_APHRODITE);
			switch(location){
			
			case 0: //Bottom left
				EntityPig.spawnPig(getX(), getY(), StateGame.player.getX(), StateGame.player.getY(), 2, 25);
				break;
			case 1: //Bottom right
				EntityPig.spawnPig(getX() + getTexture().getWidth(), getY(), StateGame.player.getX(), StateGame.player.getY(), 2, 25);
				break;
			case 2: //Center
				EntityPig.spawnPig(getX() + getTexture().getWidth() / 2, getY(), StateGame.player.getX(), StateGame.player.getY(), 2, 25);
				break;
			}
		}
		
	}

}
