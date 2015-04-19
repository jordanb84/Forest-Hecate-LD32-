package com.exilegl.forest.engine.entity;

import org.newdawn.slick.Input;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.direction.Direction;
import com.exilegl.forest.engine.input.InputManager;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateGameover;
import com.exilegl.forest.engine.state.StateManager;
import com.exilegl.forest.engine.state.StateUi;

public class EntityPlayer extends Entity{

	
	public EntityPlayer(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
	
		setTexture(TextureManager.TEXTURE_PLAYER);
		
	}
	
	public void doAi(){
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		if(getX() < 0)
			setX(getX() + getSpeed() * 2);
		if(getX() > Game.WIDTH - getTexture().getWidth())
			setX(getX() - getSpeed() * 2);
		if(getY() < 0)
			setY(getY() + getSpeed() * 2);
		if(getY() > Game.HEIGHT - getTexture().getHeight())
			setY(getY() - getSpeed() * 2);
		
		//Read for input
		//Check for move up key
		if(InputManager.input.isKeyDown(InputManager.ID_MOVEUP)){
			StateGame.player.setY(StateGame.player.getY() - StateGame.player.getSpeed());
			setTexture(TextureManager.TEXTURE_PLAYER);
		}
		
		//Check for move down key
		if(InputManager.input.isKeyDown(InputManager.ID_MOVEDOWN)){
			StateGame.player.setY(StateGame.player.getY() + StateGame.player.getSpeed());
			setTexture(TextureManager.TEXTURE_PLAYER);
		}
		
		//Check for move right key
		if(InputManager.input.isKeyDown(InputManager.ID_MOVERIGHT)){
			StateGame.player.setX(StateGame.player.getX() + StateGame.player.getSpeed());
			setTexture(TextureManager.TEXTURE_PLAYER);
		}
		
		//Check for move left key
		if(InputManager.input.isKeyDown(InputManager.ID_MOVELEFT)){
			StateGame.player.setX(StateGame.player.getX() - StateGame.player.getSpeed());
			setTexture(TextureManager.TEXTURE_PLAYER_LEFT);
		}
		
		//Check for click
		if(InputManager.input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(!(StateUi.ACONITE_COUNT < 0)){
			EntityAconite aconite = new EntityAconite(false, 0, 0, Game.MOUSE_X, Game.MOUSE_Y, 0, 0);
			EntityAconite.spawn(aconite);
			StateUi.ACONITE_COUNT = (StateUi.ACONITE_COUNT - 1);
			}
			
			if(StateManager.getState() == StateManager.STATE_BOSS){
				EntityKitten.spawnKitten();
			}
			if(StateManager.getState() == StateManager.STATE_BOSSTWO){
				
				int x = (StateGame.player.getX());
				int y = (StateGame.player.getY());
				
				EntityBunny bunny = new EntityBunny(x, y, MathHelper.getRandom(1, 2), MathHelper.getRandom(15, 25));
				EntityBunny.spawnBunny(bunny);
			}
			
			if(StateManager.getState() == StateManager.STATE_BOSSTHREE){
				int x = (StateGame.player.getX());
				int y = (StateGame.player.getY());
				
				EntityOddBunny bunny = new EntityOddBunny(x, y, MathHelper.getRandom(1, 2), MathHelper.getRandom(15, 25));
				EntityOddBunny.spawnOddBunny(bunny);
			}
		}
		
		//If the player's health is <0, do a game over
		if(getHealth() < 0){
			StateGameover.endGame();
		}
	}

}
