package com.exilegl.forest.engine;

import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

import com.exilegl.forest.engine.entity.Entity;
import com.exilegl.forest.engine.entity.EntityDemon;
import com.exilegl.forest.engine.entity.EntityPlayer;
import com.exilegl.forest.engine.input.InputManager;
import com.exilegl.forest.engine.level.LevelManager;
import com.exilegl.forest.engine.map.Map;
import com.exilegl.forest.engine.map.text.FontManager;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.sound.SoundManager;
import com.exilegl.forest.engine.state.StateGame;
import com.exilegl.forest.engine.state.StateManager;
import com.exilegl.forest.engine.state.StateMenu;

public class Game extends BasicGame{

	//Window width/height
	public static int WIDTH = (1366);
	public static int HEIGHT = (768);
	
	public static int FRAMERATE = (60);
	
	public static int MOUSE_X;
	public static int MOUSE_Y;
	
	public Game(String title) {
		super(title);
		
	}
	
	/**
	 * Not really meant to do it this way, but quicker
	 * @throws SlickException 
	 */
	public static void init(String title, int width, int height) throws SlickException{
		
		//AppGameContainer app = new AppGameContainer(new Game(title));
		//AppGameContainer app = new AppGameContainer(new ScalableGame(new Game("Forest Hecate"), WIDTH, HEIGHT, true));
		AppGameContainer app = new AppGameContainer(new Game(title));
		//AppGameContainer app = new AppGameContainer((new Game("Forest Hecate")));
		app.setDisplayMode(WIDTH,HEIGHT,false);
		//app.setDisplayMode(width, height, false);
		app.setShowFPS(true);
		app.setVSync(true);
		
		Display.setResizable(true);
		
		app.start();
		
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		//g.scale(Display.getWidth()/1920, Display.getHeight()/1024);
		//Draw the current state
		StateManager.drawCurrent(g);
		//g.drawImage(TextureManager.TEXTURE_DEMON, 100, 100);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		//EntityDemon demon = new EntityDemon(10, 10, 10, 10);
		//demon.spawn(demon);
		initGame();
		
		StateMenu.init();
		
		SoundManager.init();
		SoundManager.playSound(SoundManager.ID_MENU);
	}
	
	/**
	 * Initiates a new game
	 * @throws SlickException 
	 */
	public static void initGame() throws SlickException{
		//Initiate textures
		TextureManager.init();
		
		//Generate map
		Map.generateMap();
		
		//Spawn player
		StateGame.player = new EntityPlayer(Game.WIDTH / 2, Game.HEIGHT / 2, 2, 200);
		EntityPlayer.spawn(StateGame.player);
		
		//Initiate fonts
		FontManager.init();
		
		try {
			LevelManager.init();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			LevelManager.unlockLevel(LevelManager.LEVEL_2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		
		WIDTH = (Display.getWidth());
		HEIGHT = (Display.getHeight());
		
		MOUSE_X = (container.getInput().getMouseX());
		MOUSE_Y = (container.getInput().getMouseY());
		
		//Poll for input
		InputManager.poll(container.getInput());
		
		//Update the current state
		StateManager.updateCurrent();
		
	}

}
