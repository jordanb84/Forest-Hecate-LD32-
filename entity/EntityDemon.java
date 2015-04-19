package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateManager;

public class EntityDemon extends Entity{
	
	//The list of demons
	public static List<EntityDemon> DEMONS = new ArrayList<EntityDemon>();
	
	//The demon's health is their karma level. I need something in the game to indicate this..
	//How about when a demon runs, have it render over the demon "KARMA TOO LOW"?
	//I also need to indicate how the herb does this. Maybe at the beginning of the game, say in a guide
	//that demons need karma, or the Hecate will take them back, and that the Aconite herb makes them lose karma.
	//Have a quick guide at the beginning of the game

	//Whether or not the demon is out of karma and should run away
	private boolean isSummoned = (false);
	
	public EntityDemon(int spawnX, int spawnY, int speed, int health) {
		super(spawnX, spawnY, speed, health);
		
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		setTexture(TextureManager.TEXTURE_DEMON);
	}
	
	public void doAi(){
		
		//Update rectangle
		setRectangle(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
		
		if(StateManager.getState() == StateManager.STATE_BOSSTHREE){
			setSpeed(1);
		}else{
			setSpeed(2);
		}
		
		//Have a chance to move towards the player
			//Move towards the player
			if(getX() < StateGame.player.getX())
				setX(getX() + getSpeed());
			if(getX() > StateGame.player.getX())
				setX(getX() - getSpeed());
			if(getY() < StateGame.player.getY())
				setY(getY() + getSpeed());
			if(getY() > StateGame.player.getY())
				setY(getY() - getSpeed());
		
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
		
		//Check for collision with player
		int collisionsChecked = (0);
		while(collisionsChecked < DEMONS.size()){
			if(getRectangle().intersects(StateGame.player.getRectangle()) && MathHelper.getRandom(1, Game.FRAMERATE * 3) == 10){
				StateGame.player.setHealth(StateGame.player.getHealth() - 1);
				System.out.println("Hurt player");
				Announcer.addAnnouncement("-1", 60, StateGame.player.getX(), StateGame.player.getY());
			}
			collisionsChecked++;
		}
		
	}
	
	public void setSummoned(boolean summoned){
		isSummoned = (summoned);
	}
	
	public boolean getSummoned(){
		return isSummoned;
	}
	
	public void kill(){
		DEMONS.remove(getId());
		System.out.println("Killed 1 demon");
	}
	
	public void draw(Graphics g){
		g.drawImage(getTexture(), getX(), getY());
		drawHealth(g);
		
		if(isSummoned == true){
			g.drawString("SUMMONED", getX(), getY());
		}
	}
	
	public void drawHealth(Graphics g){
		g.setColor(Color.cyan);
		g.fillRect(getX(), getY() - getTexture().getHeight(), getHealth(), Game.HEIGHT / 75);
		g.setColor(Color.white);
		g.drawRect(getX(), getY() - getTexture().getHeight(), getHealth(), Game.HEIGHT / 75);
	}
	
	public static void spawn(EntityDemon demon){
		DEMONS.add(demon);
	}

}
