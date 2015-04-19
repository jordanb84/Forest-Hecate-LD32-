package com.exilegl.forest.engine.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.map.structure.RabbitHole;
import com.exilegl.forest.engine.map.structure.Tree;
import com.exilegl.forest.engine.map.structure.Volcano;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;

public class Map {
	
	//The amount of trees we should generate
	static int generationAmount = (16);
	static int generationAmountTwo = (8);
	static int generationAmountThree = (18);
	
	//The amount of mountains we should generate
	static int generatedAmountMountains = (16);
	
	static int generatedAmountRabbitHoles = (16);
	
	/**
	 * Draws the map
	 */
	public static void draw(Graphics g){
		
		//Draw tiled map image
		g.drawImage(TextureManager.TEXTURE_MAP, 0, 0);
		
	}
	
	/**
	 * Generates a new map, placing trees
	 * and such
	 */
	public static void generateMap(){
		
		//Generate a new tree in a random location until all the needed trees are there
		int generatedTrees = (0);
		while(generatedTrees < generationAmount){
			
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT));
			int textureNum = (MathHelper.getRandom(1, 2));
			Image texture;
			
			if(textureNum == 1){
				texture = (TextureManager.TEXTURE_TREE);
			}else{
				texture = (TextureManager.TEXTURE_TREE2);
			}
			
			Tree tree = new Tree(x, y, texture);
			Tree.spawnTree(tree);
			
			generatedTrees++;
		}
		
	}
	
	/**
	 * Generates a new map for level two, placing trees
	 * and such
	 */
	public static void generateMapTwo(){
		
		//Generate a new tree in a random location until all the needed trees are there
		int generatedTrees = (0);
		while(generatedTrees < generationAmountTwo){
			
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT));
			int textureNum = (MathHelper.getRandom(1, 2));
			Image texture;
			
			if(textureNum == 1){
				texture = (TextureManager.TEXTURE_TREE1_LEVELTWO);
			}else{
				texture = (TextureManager.TEXTURE_TREE2_LEVELTWO);
			}
			
			Tree tree = new Tree(x, y, texture);
			Tree.spawnTree(tree);
			
			generatedTrees++;
		}
		
		//Generate a new lava in a random location until all the needed trees are there
		int generatedVolcanos = (0);
		while(generatedVolcanos < generatedAmountMountains){
			
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT));
			Image texture = (TextureManager.TEXTURE_VOLCANO);
			
			Volcano volcano = new Volcano(x, y, texture);
			Volcano.spawnVolcano(volcano);
			
			generatedVolcanos++;
		}
		
	}
	
	/**
	 * Generates a new map for level three, placing trees
	 * and such
	 */
	public static void generateMapThree(){
		
		//Generate a new tree in a random location until all the needed trees are there
		int generatedTrees = (0);
		while(generatedTrees < generationAmountThree){
			
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT));
			int textureNum = (MathHelper.getRandom(1, 2));
			Image texture;
			
			if(textureNum == 1){
				texture = (TextureManager.TEXTURE_TREE1_LEVELTHREE);
			}else{
				texture = (TextureManager.TEXTURE_TREE2_LEVELTHREE);
			}
			
			Tree tree = new Tree(x, y, texture);
			Tree.spawnTree(tree);
			
			generatedTrees++;
		}
		
		//Generate a new lava in a random location until all the needed trees are there
		int generatedVolcanos = (0);
		while(generatedVolcanos < generatedAmountMountains){
			
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT));
			Image texture = (TextureManager.TEXTURE_VOLCANO);
			
			Volcano volcano = new Volcano(x, y, texture);
			Volcano.spawnVolcano(volcano);
			
			generatedVolcanos++;
		}
		
	}
	
	/**
	 * Generates a map for level four, placing
	 * volcanos and rabbit holes
	 */
	public static void generateMapFour(){
		
		//Generate a new lava in a random location until all the needed trees are there
		int generatedRabbitHoles = (0);
		while(generatedRabbitHoles < generatedAmountRabbitHoles){
			
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT));
			Image texture = (TextureManager.TEXTURE_RABBITHOLE);
			
			RabbitHole rabbitHole = new RabbitHole(x, y, texture);
			RabbitHole.spawnRabbitHole(rabbitHole);
			
			generatedRabbitHoles++;
		}
		
		//Generate a new lava in a random location until all the needed trees are there
		int generatedVolcanos = (0);
		while(generatedVolcanos < generatedAmountMountains){
			
			int x = (MathHelper.getRandom(0, Game.WIDTH));
			int y = (MathHelper.getRandom(0, Game.HEIGHT));
			Image texture = (TextureManager.TEXTURE_VOLCANO);
			
			Volcano volcano = new Volcano(x, y, texture);
			Volcano.spawnVolcano(volcano);
			
			generatedVolcanos++;
		}
	}
}
