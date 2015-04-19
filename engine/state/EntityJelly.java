package com.exilegl.forest.engine.state;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.entity.Entity;
import com.exilegl.forest.engine.math.MathHelper;

public class EntityJelly extends Entity{
	
	//The list of all jelly
	public static List<EntityJelly> JELLY = new ArrayList<EntityJelly>();
	
	//The jelly's width
	private int WIDTH;
	
	//The jelly's height
	private int HEIGHT;
	
	//Whether or not the jelly is dead
	private boolean DEAD;
	
	//The jelly's segments
	private int SEGS;
	
	//The jelly's color
	private Color COLOR;
	
	public EntityJelly(int spawnX, int spawnY, int speed, int health, int width, int height, int segs, Color color) {
		super(spawnX, spawnY, speed, health);
		setId(JELLY.size() + 1);
		setWidth(width);
		setHeight(height);
		setSpeed(speed);
		setSegs(segs);
		setColor(color);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g){
		if(StateManager.getState() == StateManager.STATE_BOSS){
		g.setColor(Color.gray);
		g.fillOval(getX() - 2, getY() - 2, getWidth(), getHeight(), getSegs());
		g.setColor(getColor());
		g.fillOval(getX(), getY(), getWidth(), getHeight(), getSegs());
		g.setColor(Color.white);
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSTWO){
			g.setColor(Color.black);
			g.fillOval(getX() + 5, getY() + 5, getWidth(), getHeight(), getSegs());
			if(MathHelper.getRandom(1, 5) == 5){
			g.setColor(getColor());}else{
				g.setColor(Color.orange);
			}
			g.fillOval(getX(), getY(), getWidth(), getHeight(), getSegs());
			g.setColor(Color.white);
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSTHREE){
			g.setColor(Color.black);
			g.fillOval(getX() + 5, getY() + 5, getWidth(), getHeight(), getSegs());
			if(MathHelper.getRandom(1, 5) == 5){
			g.setColor(getColor());}else{
				g.setColor(Color.yellow);
			}
			g.fillOval(getX(), getY(), getWidth(), getHeight(), getSegs());
			g.setColor(Color.white);
		}
	}
	
	/**
	 * Spawns a jelly with a random
	 * color and size
	 */
	public static void spawnJelly(int x, int y){
		
		int width = (MathHelper.getRandom(50, 80));
		int height = (MathHelper.getRandom(50, 80));
		int speed = (MathHelper.getRandom(1, 3));
		int segs = (MathHelper.getRandom(10, 35));
		Color color = (MathHelper.getRandomColor());
		
		EntityJelly jelly = new EntityJelly(x, y, speed, 0, width, height, segs, MathHelper.getRandomColor());
		JELLY.add(jelly);
	}
	
	/**
	 * Updates the jelly
	 */
	public void doAi(){
		
		//Update rectangle
		setRectangle(getX(), getY(), getWidth(), getHeight());
		
		setY(getY() + getSpeed());
		
		//If the jelly is out of bounds, destroy it
		if(getY() > Game.HEIGHT){
			kill();
		}
		
		//Check for collision with player
		if(getRectangle().intersects(StateGame.player.getRectangle())){
			StateGame.player.setHealth(StateGame.player.getHealth() - 1);
		}
		
	}
	
	/**
	 * Sets the jelly's segments
	 */
	public void setSegs(int segs){
		SEGS = (segs);
	}
	
	/**
	 * Returns the jelly's segments
	 */
	public int getSegs(){
		return SEGS;
	}
	
	/**
	 * Sets the jelly's color
	 */
	public void setColor(Color color){
		COLOR = (color);
	}
	
	/**
	 * Returns the jelly's color
	 */
	public Color getColor(){
		return COLOR;
	}
	
	/**
	 * Kills the jelly
	 */
	public void kill(){
		setDead(true);
		
		//I think the problem is related to other jellies being killed, and the id number not being lowered
	}
	
	/**
	 * Sets whether or not the jelly is dead
	 */
	public void setDead(boolean dead){
		DEAD = (dead);
	}
	
	/**
	 * Whether or not the jelly is dead
	 */
	public boolean getDead(){
		return DEAD;
	}
	
	/**
	 * Sets the jelly's width
	 */
	public void setWidth(int width){
		WIDTH = (width);
	}
	
	/**
	 * Returns the jelly's width
	 */
	public int getWidth(){
		return WIDTH;
	}
	
	/**
	 * Sets the jelly's height
	 */
	public void setHeight(int height){
		HEIGHT = (height);
	}
	
	/**
	 * Returns the jelly's height
	 */
	public int getHeight(){
		return HEIGHT;
	}

}
