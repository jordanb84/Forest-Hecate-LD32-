package com.exilegl.forest.engine.map.structure;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.entity.EntityLava;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;

public class Volcano extends Tree{
	
	//List of all volcanos
	public static List<Volcano> VOLCANOS = new ArrayList<Volcano>();

	public Volcano(int spawnX, int spawnY, Image texture) {
		super(spawnX, spawnY, texture);
		
		setTexture(TextureManager.TEXTURE_VOLCANO);
		
	}
	
	public void doAi(){
		
		//Have a small chance of spawning lava
		if(MathHelper.getRandom(0, Game.FRAMERATE * 10) == Game.FRAMERATE * 3){
			
			int x = (MathHelper.getRandom(getX(), getX() + getTexture().getWidth()));
			int y = (MathHelper.getRandom(getY(), getY() + getTexture().getHeight()));
			
			int destX = (MathHelper.getRandom(getX() + getTexture().getWidth(), getX() + getTexture().getWidth() * 2));
			int destY = (MathHelper.getRandom(getY() + getTexture().getHeight(), getY() + getTexture().getHeight() * 2));
					
					
			
			EntityLava lava = new EntityLava(false, destX, destY, x, y, MathHelper.getRandom(1, 2), 0);
			EntityLava.spawnLava(lava);
		}
	}
	
	/**
	 * Spawns a new volcano
	 */
	public static void spawnVolcano(Volcano volcano){
		VOLCANOS.add(volcano);
	}

}
