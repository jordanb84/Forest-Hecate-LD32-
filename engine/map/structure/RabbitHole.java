package com.exilegl.forest.engine.map.structure;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;

import com.exilegl.forest.engine.Game;
import com.exilegl.forest.engine.entity.EntityAconite;
import com.exilegl.forest.engine.entity.EntityEvilRabbit;
import com.exilegl.forest.engine.map.texture.TextureManager;
import com.exilegl.forest.engine.math.MathHelper;
import com.exilegl.forest.engine.state.StateUi;

public class RabbitHole extends Tree{
	
	//The list of rabbit holes
	public static List<RabbitHole> RABBITHOLES = new ArrayList<RabbitHole>();

	public RabbitHole(int spawnX, int spawnY, Image texture) {
		super(spawnX, spawnY, texture);
		
		setTexture(TextureManager.TEXTURE_RABBITHOLE);
	}
	
	/**
	 * Spawns a new rabbit hold
	 */
	public static void spawnRabbitHole(RabbitHole rabbitHole){
		RABBITHOLES.add(rabbitHole);
	}
	
	public void update(){
		//We will randomly spawn both aconites and evil rabbits that go after the player
		
		//Spawn aconites
		if(MathHelper.getRandom(0, Game.FRAMERATE * 8) == Game.FRAMERATE * 8){
			EntityAconite aconite = new EntityAconite(true, getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), getX(), getY(), 1, 0);
			EntityAconite.spawn(aconite);
			if(StateUi.isCompleted == true){
			}
		}
		
		//Spawn evil rabbits
		if(MathHelper.getRandom(0, Game.FRAMERATE * 8) == Game.FRAMERATE * 8){
			EntityEvilRabbit rabbit = new EntityEvilRabbit(getX() + MathHelper.getRandom(0, 25), getY() + getTexture().getHeight() + MathHelper.getRandom(0, 25), 1, MathHelper.getRandom(15, 25));
			EntityEvilRabbit.spawnRabbit(rabbit);
		}
		
	}

}
