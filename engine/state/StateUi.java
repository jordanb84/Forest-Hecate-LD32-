package com.exilegl.forest.engine.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityDemon;
import com.exilegl.forest.engine.entity.EntityFairy;
import com.exilegl.forest.engine.map.structure.Tree;
import com.exilegl.forest.engine.map.text.FontManager;
import com.exilegl.forest.engine.map.text.Text;
import com.exilegl.forest.engine.math.MathHelper;

public class StateUi { //Ui "state", typically drawn over the game state
	
	//The amount of aconites the player has
	public static int ACONITE_COUNT = (15); //You start with 15
	
	//The amount of karma that is left
	public static int KARMA = (500);
	
	public static int framesPast = (0);
	
	//Whether or not the first stage (before boss) is completed
	public static boolean isCompleted = (false);
	
	public static void draw(Graphics g){
		
		//Draw karma bar
		drawKarma(g);
		
	}
	
	public static void update(){
		
		if(KARMA < 0){
			if(StateManager.getState() == StateManager.STATE_GAME){
			EntityDemon.DEMONS.clear();
			
			isCompleted = (true);
			
			framesPast++;
			
			if(framesPast > Game.FRAMERATE * 4){
			StateManager.setState(StateManager.STATE_BOSS);
			}
			}
			
			if(StateManager.getState() == StateManager.STATE_LEVELTWO){
				EntityFairy.FAIRIES.clear();
				
				isCompleted = (true);
				
				framesPast++;
				
				if(framesPast > Game.FRAMERATE * 4){
				StateManager.setState(StateManager.STATE_BOSSTWO);
				}
			}
			
			if(StateManager.getState() == StateManager.STATE_LEVELTHREE){	
				isCompleted = (true);
				
				framesPast++;
				
				if(framesPast > Game.FRAMERATE){
					int aconitesSpawned = (0);
					while(aconitesSpawned < Tree.TREES.size() && Tree.TREES.get(aconitesSpawned) != null){
						
						EntityAconite aconite = new EntityAconite(true, Tree.TREES.get(aconitesSpawned).getX() + MathHelper.getRandom(0, 25), Tree.TREES.get(aconitesSpawned).getY() + Tree.TREES.get(aconitesSpawned).getTexture().getHeight() + MathHelper.getRandom(0, 25), Tree.TREES.get(aconitesSpawned).getX(), Tree.TREES.get(aconitesSpawned).getY(), 1, 0);
						EntityAconite.spawn(aconite);
						
						aconitesSpawned++;
					}
				}
				if(framesPast > Game.FRAMERATE * 4){
				//StateManager.setState(StateManager.STATE_BOSSTHREE);
				StateGameover.resetGame(StateManager.STATE_BOSSTHREE);
				}
			}
			//StateGameover.resetGame();
		}
			
		
	}
	
	/**
	 * Draws the "karma" bar
	 */
	public static void drawKarma(Graphics g){
		
		if(StateManager.getState() != StateManager.STATE_BOSS){
		int width = (KARMA);
		int height = (Game.HEIGHT / 55);
		int x = (Game.WIDTH / 2 - width / 2);
		int xText = (Game.WIDTH / 2 - 500 / 2);
		int y = (0 + Game.HEIGHT / 35);
		
		g.setColor(Color.cyan);;
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawRect(x, y, width, height);
		
		Text.drawBordered(g, "Karma " + "(" + KARMA + ")", xText + xText / 3, y);
		
		//Text.drawBordered(g, "Aconites: " + ACONITE_COUNT, Game.WIDTH - Game.WIDTH / 3, Game.HEIGHT / 35);
		FontManager.drawBold(g, "Aconites: " + ACONITE_COUNT, Game.WIDTH - Game.WIDTH / 3, Game.HEIGHT / 35);
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSS && StateBoss.aphrodite != null){
			int width = (StateBoss.aphrodite.getHealth());
			int height = (Game.HEIGHT / 55);
			int x = (Game.WIDTH / 2 - width / 2);
			int xText = (Game.WIDTH / 2 - 500 / 2);
			int y = (0 + Game.HEIGHT / 35);
			
			g.setColor(Color.darkGray);;
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
			
			Text.drawBordered(g, "Aphrodite Karma " + "(" + StateBoss.aphrodite.getHealth() + ")", xText + xText / 3, y);
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSTWO){
			if(StateBossTwo.firstFrame == false){
			int width = (StateBossTwo.hera.getHealth());
			int height = (Game.HEIGHT / 55);
			int x = (Game.WIDTH / 2 - width / 2);
			int xText = (Game.WIDTH / 2 - 500 / 2);
			int y = (0 + Game.HEIGHT / 35);
			
			g.setColor(Color.darkGray);;
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
			
			Text.drawBordered(g, "Hera Karma " + "(" + StateBossTwo.hera.getHealth() + ")", xText + xText / 3, y);
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSTHREE){
			if(StateBossThree.firstFrame == false){
			int width = (StateBossThree.panes.getHealth());
			int height = (Game.HEIGHT / 55);
			int x = (Game.WIDTH / 2 - width / 2);
			int xText = (Game.WIDTH / 2 - 500 / 2);
			int y = (0 + Game.HEIGHT / 35);
			
			g.setColor(Color.green);;
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
			
			Text.drawBordered(g, "Panes Karma " + "(" + StateBossThree.panes.getHealth() + ")", xText + xText / 3, y);
			}
		}
		
		if(StateManager.getState() == StateManager.STATE_BOSSFOUR){
			if(StateBossFour.firstFrame == false){
			int width = (StateBossFour.sirens.getHealth());
			int height = (Game.HEIGHT / 55);
			int x = (Game.WIDTH / 2 - width / 2);
			int xText = (Game.WIDTH / 2 - 500 / 2);
			int y = (0 + Game.HEIGHT / 35);
			
			g.setColor(Color.green);;
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
			
			Text.drawBordered(g, "Sirens Karma " + "(" + StateBossFour.sirens.getHealth() + ")", xText + xText / 3, y);
			}
			System.out.println("drew Sirens karma");
		}
		
	}

}
