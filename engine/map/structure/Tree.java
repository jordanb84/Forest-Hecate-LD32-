package com.exilegl.forest.engine.map.structure;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityDemon;
import com.exilegl.forest.engine.entity.EntityFairy;
import com.exilegl.forest.engine.entity.EntityHauntedTree;
import com.exilegl.forest.engine.entity.EntityLava;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.StateManager;
import com.exilegl.forest.engine.state.StateUi;

public class Tree {
	
	//The list of all trees, used to render and update them
	public static List<Tree> TREES = new ArrayList<Tree>();
	
	//The tree's texture
	private Image TEXTURE;
	
	//The tree's x location
	private int TREE_X;
	
	//The tree's y location
	private int TREE_Y;
	
	//The tree's rectangle, used for collision
	private Rectangle rectangle;
	
	public Tree(int spawnX, int spawnY, Image texture){
		
		setTexture(texture);
		setX(spawnX);
		setY(spawnY);
		setRectangle(spawnX, spawnY, texture.getWidth(), texture.getHeight());
		
	}
	
	/**
	 * Spawns a new tree at a given location
	 */
	public static void spawnTree(Tree tree){
		TREES.add(tree);
	}
	
	/**
	 * Draws the tree
	 */
	public void draw(Graphics g){
		g.drawImage(getTexture(), getX(), getY());
	}
	
	/**
	 * Updates the tree
	 */
	public void update(){
		//Have a small chance to spawn a new demon
		
		if(StateManager.getState() == StateManager.STATE_GAME){
		if(MathHelper.getRandom(0, Game.FRAMERATE * 18) == Game.FRAMERATE * 18){
			EntityDemon demon = new EntityDemon(getX(), getY(), 1, MathHelper.getRandom(15, 25));
			EntityDemon.spawn(demon);
		}
		}
		
		if(StateManager.getState() == StateManager.STATE_LEVELTWO){
			if(MathHelper.getRandom(0, Game.FRAMERATE * 8) == Game.FRAMERATE * 3){
				EntityFairy fairy = new EntityFairy(getX(), getY(), 1, MathHelper.getRandom(15, 25));
				EntityFairy.spawnFairy(fairy);
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_LEVELTHREE){
			//Have a small chance of spawning lava
			if(MathHelper.getRandom(0, Game.FRAMERATE * 10) == Game.FRAMERATE * 3){
				
				int x = (MathHelper.getRandom(getX(), getX() + getTexture().getWidth()));
				int y = (MathHelper.getRandom(getY(), getY() + getTexture().getHeight()));
				
				int destX = (MathHelper.getRandom(getX() + getTexture().getWidth(), getX() + getTexture().getWidth() * 2));
				int destY = (MathHelper.getRandom(getY() + getTexture().getHeight(), getY() + getTexture().getHeight() * 2));
						
						
				
				EntityLava lava = new EntityLava(false, destX, destY, x, y, MathHelper.getRandom(1, 2), 0);
				EntityLava.spawnLava(lava);
			}
			
			if(MathHelper.getRandom(0, Game.FRAMERATE * 8) == Game.FRAMERATE * 3){
				EntityHauntedTree hauntedTree = new EntityHauntedTree(getX(), getY(), 1, MathHelper.getRandom(15, 25));
				EntityHauntedTree.spawnHauntedTree(hauntedTree);
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_GAME){
		if(MathHelper.getRandom(0, Game.FRAMERATE * 3) == Game.FRAMERATE * 3){
			EntityAconite aconite = new EntityAconite(true, getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), getX(), getY(), 1, 0);
			EntityAconite.spawn(aconite);
			if(StateUi.isCompleted == true){
				int spawnedAconites = (0);
				while(spawnedAconites < 50){
					EntityAconite aconite2 = new EntityAconite(true, getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), getX(), getY(), 1, 0);
					EntityAconite.spawn(aconite2);
					spawnedAconites++;
				}
			}
		}
		}
		
		if(StateManager.getState() == StateManager.STATE_LEVELTWO){
			if(MathHelper.getRandom(0, Game.FRAMERATE * 2) == Game.FRAMERATE * 2){
				EntityAconite aconite = new EntityAconite(true, getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), getX(), getY(), 1, 0);
				EntityAconite.spawn(aconite);
				if(StateUi.isCompleted == true){
					int spawnedAconites = (0);
					while(spawnedAconites < 50){
						EntityAconite aconite2 = new EntityAconite(true, getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), getX(), getY(), 1, 0);
						EntityAconite.spawn(aconite2);
						spawnedAconites++;
					}
				}
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_LEVELTHREE){
			if(MathHelper.getRandom(0, Game.FRAMERATE * 2) == Game.FRAMERATE * 2){
				EntityAconite aconite = new EntityAconite(true, getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), getX(), getY(), 1, 0);
				EntityAconite.spawn(aconite);
				if(StateUi.isCompleted == true){
					int spawnedAconites = (0);
					while(spawnedAconites < 50){
						EntityAconite aconite2 = new EntityAconite(true, getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), getX(), getY(), 1, 0);
						EntityAconite.spawn(aconite2);
						spawnedAconites++;
					}
				}
			}
		}
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
		TREE_X = (x);
	}
	
	/**
	 * Sets the tree's Y location
	 */
	public void setY(int y){
		TREE_Y = (y);
	}
	
	/**
	 * Gets the tree's X location
	 */
	public int getX(){
		return TREE_X;
	}
	
	/**
	 * Gets the tree's Y location
	 */
	public int getY(){
		return TREE_Y;
	}
	
	//I probably only need 1 tree related class, so not going to make structure superclasses

}
