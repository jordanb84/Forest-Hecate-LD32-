package com.exilegl.forest.engine.entity;

import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.EntityJelly;

public class EntityEuryale extends EntityAphrodite{
	
	//This will be the mini boss for level 3. I'll add some sort of mechanical creature for level 3's main boss.
	
	public EntityEuryale(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
	
		setTexture(TextureManager.TEXTURE_BOSS_EURYALE);
		
	}
	
	//Euryale will occasionally spawn a fairy on itself
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
			setDestX(MathHelper.getRandom(0, Game.WIDTH));
			setDestY(MathHelper.getRandom(0, Game.HEIGHT / 2));
			rect = new Rectangle(getDestX(), getDestY(), 10, 10);
		}
		
		//Have a small chance of spawning a jelly at either end, or the center of the boss
		if(MathHelper.getRandom(0, Game.FRAMERATE * 2) == Game.FRAMERATE * 2){
			int location = (MathHelper.getRandom(0, 2));
			
			switch(location){
			case 0: //Bottom left
				EntityFairy.spawnFairy(new EntityFairy(getX(), getY(), 2, 25));
				break;
			case 1: //Bottom right
				EntityFairy.spawnFairy(new EntityFairy(getX() + getTexture().getWidth(), getY(), 2, 25));
				break;
			case 2: //Center
				EntityFairy.spawnFairy(new EntityFairy(getX() + getTexture().getWidth() / 2, getY(), 2, 25));
				break;
			}
		}
		
	}
	
	

}
