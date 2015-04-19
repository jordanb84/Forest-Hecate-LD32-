package com.exilegl.forest.engine.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.map.structure.Tree;
import com.exilegl.forest.engine.map.texture.TextureManager;

public class Entity { //Base class for all entities
	
	//The array list of this entity
	public static List<Entity> ENTITY = new ArrayList<Entity>();
	
	//The entity's texture
	private Image TEXTURE;
	
	//The entity's health
	private int HEALTH;
	
	//The entity's rectangle, used for collision
	private Rectangle rectangle;
	
	//The entity's location
	private int LOCATION_X;
	private int LOCATION_Y;
	
	//The entity's speed
	private int SPEED;
	
	//The entity's ID, used for removing/killing it
	private int ID;
	
	//The next available ID
	private int nextId;
	
	public Entity(int spawnX, int spawnY, int speed, int health){
		
		setX(spawnX);
		setY(spawnY);
		setSpeed(speed);
		setHealth(health);
		setTexture(TextureManager.TEXTURE_TREE);
		setId(getNextId());
		nextId = (nextId + 1);
	}
	
	/**
	 * Spawns a new tree at a given location
	 */
	public static void spawn(Entity entity){
		ENTITY.add(entity);
	}
	
	/**
	 * Draws the tree
	 */
	public void draw(Graphics g){
		g.drawImage(getTexture(), getX(), getY());
		drawHealth(g);
	}
	
	/**
	 * Sets the entity's ID
	 */
	public void setId(int id){
		ID = (id);
	}
	
	/**
	 * Returns the entity's ID
	 */
	public int getId(){
		return ID;
	}
	
	/**
	 * Gets the next available Id
	 */
	public int getNextId(){
		return nextId;
	}	
	
	/**
	 * Performs the entity's AI
	 */
	public void doAi(){
		setX(getX() - getSpeed());
		System.out.println("AI");
	}
	
	/**
	 * Sets the entity's speed
	 */
	public void setSpeed(int speed){
		SPEED = (speed);
	}
	
	/**
	 * Returns the entity's speed
	 */
	public int getSpeed(){
		return SPEED;
	}
	
	/**
	 * Draws the entity's health
	 */
	public void drawHealth(Graphics g){
		g.setColor(Color.red);
		g.fillRect(getX(), getY() - getTexture().getHeight(), getHealth(), Game.HEIGHT / 75);
		g.setColor(Color.white);
		g.drawRect(getX(), getY() - getTexture().getHeight(), getHealth(), Game.HEIGHT / 75);
	}
	
	/**
	 * Sets the entity's health
	 */
	public void setHealth(int health){
		HEALTH = (health);
	}
	
	/**
	 * Returns the entity's health
	 */
	public int getHealth(){
		return HEALTH;
	}
	
	/**
	 * Sets the tree's rectangle
	 * @return
	 */
	public void setRectangle(int x, int y, int width, int height){
		rectangle = new Rectangle(x, y, width, height);
	}
	
	/**
	 * Gets the tree's rectangle
	 * @return
	 */
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	//Gets the tree's texture
	public Image getTexture(){
		return TEXTURE;
	}
	
	//Sets the tree's texture
	public void setTexture(Image texture){
		TEXTURE = (texture);
	}
	
	/**
	 * Sets the tree's X location
	 */
	public void setX(int x){
		LOCATION_X = (x);
	}
	
	/**
	 * Sets the tree's Y location
	 */
	public void setY(int y){
		LOCATION_Y = (y);
	}
	
	/**
	 * Gets the tree's X location
	 */
	public int getX(){
		return LOCATION_X;
	}
	
	/**
	 * Gets the tree's Y location
	 */
	public int getY(){
		return LOCATION_Y;
	}

}
