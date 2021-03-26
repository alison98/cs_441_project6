package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Startup extends Game {
	SpriteBatch batch;
	Texture img;
	//static public Skin gameSkin;

	@Override
	public void create () {
		setScreen(new StartScreen(this));
		//gameSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}
