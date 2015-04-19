package com.exilegl.forest.engine.map.texture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TextureManager { //Contains functions used to load or grab textures
	
	//Tree textures
	public static Image TEXTURE_TREE;
	public static Image TEXTURE_TREE2;
	public static Image TEXTURE_PLAYER;
	
	//Map texture
	public static Image TEXTURE_MAP;
	
	public static Image TEXTURE_MAP4;
	
	//Demon texture
	public static Image TEXTURE_DEMON;
	
	//Aconite texture
	public static Image TEXTURE_ACONITE;
	public static Image TEXTURE_ACONITE_RAW;
	
	public static Image TEXTURE_GAMEOVER;
	public static Image TEXTURE_FADE;
	
	public static Image TEXTURE_MENUBG;
	
	public static Image TEXTURE_MAP_ALIEN;
	
	public static Image TEXTURE_APHRODITE;
	
	public static Image TEXTURE_BUTTON;
	public static Image TEXTURE_BUTTON_HOVER;
	public static Image TEXTURE_BUTTON_ACTIVE;
	
	public static Image TEXTURE_KITTY1;
	public static Image TEXTURE_KITTY2;
	
	public static Image TEXTURE_PLAYER_LEFT;
	
	public static Image TEXTURE_LEVEL_1;
	
	public static Image TEXTURE_LEVEL_1_HOVERED;
	
	public static Image TEXTURE_LEVEL_2;
	public static Image TEXTURE_LEVEL_2_HOVERED;
	
	public static Image TEXTURE_MAP_LEVEL2;
	
	public static Image TEXTURE_FAIRY;
	
	public static Image TEXTURE_VOLCANO;
	
	public static Image TEXTURE_LAVA;
	
	public static Image TEXTURE_TREE1_LEVELTWO;
	public static Image TEXTURE_TREE2_LEVELTWO;
	public static Image TEXTURE_TREE1_LEVELTHREE;
	public static Image TEXTURE_TREE2_LEVELTHREE;
	
	public static Image TEXTURE_HERA;
	
	public static Image TEXTURE_MAP_BOSSTWO;
	
	public static Image TEXTURE_BUNNY1;
	
	public static Image TEXTURE_MAP_THREE;
	
	public static Image TEXTURE_HAUNTEDTREE;
	
	public static Image TEXTURE_ICON_3;
	public static Image TEXTURE_ICON_3_HOVERED;
	
	public static Image TEXTURE_BOSS_EURYALE;
	
	public static Image TEXTURE_MAP_BOSS3;
	
	public static Image TEXTURE_BOSS_PANES;
	
	public static Image TEXTURE_ODDBUNNY;
	
	public static Image TEXTURE_RABBITHOLE;
	
	public static Image TEXTURE_EVILRABBIT;
	
	public static Image TEXTURE_ICON_LEVEL4;
	public static Image TEXTURE_ICON_LEVEL4_HOVER;
	
	public static Image TEXTURE_MAP_LEVEL4_BOSS;
	
	public static Image TEXTURE_BOSS_SIRENS;
	
	public static Image TEXTURE_PIG;
	
	public static Image TEXTURE_MUD;
	
	/**
	 * Loads all images
	 * @throws SlickException 
	 */
	public static void init() throws SlickException{
		
		//Initiate all textures
		TEXTURE_TREE = (load("tree1"));
		TEXTURE_TREE2 = (load("tree2"));
		TEXTURE_MAP = (load("map"));
		TEXTURE_PLAYER = (load("player"));
		TEXTURE_DEMON = (load("demon"));
		TEXTURE_ACONITE = (load("aconite"));
		TEXTURE_ACONITE_RAW = (load("aconite_uncooked"));
		TEXTURE_GAMEOVER = (load("gameover"));
		TEXTURE_FADE = (load("fade"));
		TEXTURE_MENUBG = (load("menubg"));
		TEXTURE_MAP_ALIEN = (load("map_alien"));
		TEXTURE_APHRODITE = (load("aphrodite"));
		TEXTURE_BUTTON = (load("button_normal"));
		TEXTURE_BUTTON_HOVER = (load("button_hover"));
		TEXTURE_BUTTON_ACTIVE = (load("button_active"));
		TEXTURE_KITTY1 = (load("kitty1"));
		TEXTURE_KITTY2 = (load("kitty2"));
		TEXTURE_PLAYER_LEFT = (load("player_left"));
		TEXTURE_LEVEL_1 = (load("level_icon_1"));
		TEXTURE_LEVEL_1_HOVERED = (load("level_icon_1_hovered"));
		TEXTURE_MAP_LEVEL2 = (load("map_level2"));
		TEXTURE_FAIRY = (load("fairy"));
		TEXTURE_VOLCANO = (load("volcano"));
		TEXTURE_LAVA = (load("lava"));
		TEXTURE_TREE1_LEVELTWO = (load("tree1_level2"));
		TEXTURE_TREE2_LEVELTWO = (load("tree2_level2"));
		TEXTURE_HERA = (load("boss_hera"));
		TEXTURE_MAP_BOSSTWO = (load("map_bosstwo"));
		TEXTURE_LEVEL_2 = (load("icon_level2"));
		TEXTURE_LEVEL_2_HOVERED = (load("icon_level2_hover"));
		TEXTURE_BUNNY1 = (load("bunny1"));
		TEXTURE_MAP_THREE = (load("mapthree"));
		TEXTURE_TREE1_LEVELTHREE = (load("tree1_levelthree"));
		TEXTURE_TREE2_LEVELTHREE = (load("tree2_levelthree"));
		TEXTURE_HAUNTEDTREE = (load("hauntedtree"));
		TEXTURE_ICON_3 = (load("icon_level3"));
		TEXTURE_ICON_3_HOVERED = (load("icon_level3_hovered"));
		TEXTURE_BOSS_EURYALE = (load("boss_euryale"));
		TEXTURE_MAP_BOSS3 = (load("map_boss3"));
		TEXTURE_BOSS_PANES = (load("boss_panes"));
		TEXTURE_ODDBUNNY = (load("oddbunny"));
		TEXTURE_MAP4 = (load("map_level4"));
		TEXTURE_RABBITHOLE = (load("rabbithole"));
		TEXTURE_EVILRABBIT = (load("rabbitevil"));
		TEXTURE_ICON_LEVEL4 = (load("icon_level4"));
		TEXTURE_ICON_LEVEL4_HOVER = (load("icon_level4_hover"));
		TEXTURE_MAP_LEVEL4_BOSS = (load("map_level4_boss"));
		TEXTURE_BOSS_SIRENS = (load("boss_sirens"));
		TEXTURE_PIG = (load("pig"));
		TEXTURE_MUD = (load("mud"));
		
	}
	
	/**
	 * Loads a texture with a given
	 * location
	 * @throws SlickException 
	 */
	public static Image load(String name) throws SlickException{
		Image image = new Image("res/sprite/" + name + ".png");
		return image;
	}

}
