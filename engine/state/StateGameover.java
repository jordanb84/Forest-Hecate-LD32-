package com.exilegl.forest.engine.state;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.announcer.Announcer;
import com.exilegl.forest.engine.announcer.Announcer2;
import com.exilegl.forest.engine.entity.Entity;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityDemon;
import com.exilegl.forest.engine.entity.EntityEvilRabbit;
import com.exilegl.forest.engine.entity.EntityFairy;
import com.exilegl.forest.engine.entity.EntityHauntedTree;
import com.exilegl.forest.engine.entity.EntityLava;
import com.exilegl.forest.engine.entity.EntityMud;
import com.exilegl.forest.engine.entity.EntityOddBunny;
import com.exilegl.forest.engine.entity.EntityPlayer;
import com.exilegl.forest.engine.map.Map;
import com.exilegl.forest.engine.map.structure.RabbitHole;
import com.exilegl.forest.engine.map.structure.Tree;
import com.exilegl.forest.engine.map.texture.TextureManager;

public class StateGameover {
	
	//The amount of frames that have past since the game over
	static int framesPast = (0);
	
	//The amount of frames to wait before resetting the game
	static int framesToWait = (Game.FRAMERATE * 3);
	
	//Whether or not the game is over
	public static boolean isOver = (false);
	
	public static void draw(Graphics g){
	
		g.drawImage(TextureManager.TEXTURE_FADE, 0, 0);
		g.drawImage(TextureManager.TEXTURE_GAMEOVER, Game.WIDTH / 2 - TextureManager.TEXTURE_GAMEOVER.getWidth() / 2, Game.HEIGHT / 2);
		
	}
	
	public static void update(){
		
		framesPast++;
		
		//After a few seconds, restart the game
		if(framesPast == framesToWait){
			resetGame(StateManager.STATE_MENU);
		}
	}
	
	public static void endGame(){
		isOver = (true);
		StateManager.setState(StateManager.STATE_GAMEOVER);
	}
	
	/**
	 * Resets/restarts the game
	 */
	public static void resetGame(int state){
		
		isOver = (false);
		EntityDemon.DEMONS.clear();
		EntityPlayer.ENTITY.clear();
		Entity.ENTITY.clear();
		StateUi.KARMA = (500);
		StateUi.ACONITE_COUNT = (15);
		EntityAconite.ACONITES.clear();
		StateUi.framesPast = (0);
		
		StateGame.player.setHealth(200);
		Tree.TREES.clear();
		Map.generateMap();
		StateUi.isCompleted = (false);
		StateBoss.firstFrame = (true);
		StateLevelTwo.firstFrame = (true);
		StateGame.frameNumber = (0);
		StateBoss.frameNumber = (0);
		StateLevelTwo.frameNumber = (0);
		StateBossTwo.firstFrame = (true);
		if(StateBossTwo.hera != null){
		StateBossTwo.hera.setBeat(false);
		StateBossTwo.hera.setHealth(1250);
		}
		
		EntityMud.MUD.clear();
		
		StateLevelThree.firstFrame = (true);
		
		RabbitHole.RABBITHOLES.clear();
		
		EntityEvilRabbit.RABBITS.clear();
		
		StateLevelFour.firstFrame = (true);
		
		StateBossFour.firstFrame = (true);
		
		StateBossFour.framesPast = (0);
		StateBossFour.announced2 = (false);
		
		if(StateBossThree.panes != null){
		StateBossThree.panes.setBeat(false);
		StateBossThree.panes.setHealth(4750);
		}
		StateBossThree.frameNumber = (0);
		StateBossThree.firstFrame = (true);
		
		StateBossFour.doneFramesPast = (false);
		
		if(StateBoss.aphrodite != null){
		StateBoss.aphrodite.setFramesPast(0);
		StateBoss.aphrodite.setBeat(false);
		StateBoss.aphrodite.setHealth(750);
		}
		
		EntityOddBunny.ODDBUNNIES.clear();
		
		Announcer2.ENABLED_1 = (false);
		Announcer2.ENABLED_2 = (false);
		Announcer2.ENABLED_3 = (false);
		Announcer2.ENABLED_4 = (false);
		Announcer2.ENABLED_5 = (false);
		Announcer2.PAST_1 = (0);
		Announcer2.PAST_2 = (0);
		Announcer2.PAST_3 = (0);
		Announcer2.PAST_4 = (0);
		Announcer2.PAST_5 = (0);
		
		StateBossFour.doneFramesPast = (false);
		
		StateBossFour.announced3 = (false);
		
		if(StateBossThree.panes != null){
		StateBossThree.panes.setHealth(5750);
		}
		
		EntityHauntedTree.HAUNTEDTREES.clear();
		EntityLava.LAVA.clear();
		EntityAconite.ACONITES.clear();
		EntityFairy.FAIRIES.clear();
		
		EntityJelly.JELLY.clear();
		
		framesPast = (0);
		
		StateManager.setState(state);
		
	}

}
