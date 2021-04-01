package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameScreen implements Screen {
    private Game game;
    private Stage stage;
    private int height;
    private int width;
    private Rocket rocket;
    private LaserManager lasers;
    private AsteroidManager asteroids;
    private StarManager stars;

    public GameScreen(Game g, StarManager s) {
        game = g;
        stage = new Stage(new ScreenViewport());
        stars = s;
        stars.setStage(stage);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        System.out.println(height + " " + width);
        initRocket();
        lasers = new LaserManager(height, width, (int)(25 + rocket.getHeight()), stage);
        asteroids = new AsteroidManager(height, width, stage);
    }

    private void initRocket(){
        rocket = new Rocket();
        rocket.setPosition(width/2-(rocket.getWidth()/2), 25);
        stage.addActor(rocket);
        rocket.addListener(new DragListener(){
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
               rocket.moveBy(x - rocket.getWidth()/2, 0);
            }
        });
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.065f, .065f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tick();
        stage.act();
        stage.draw();
    }

    private void tick(){
        stars.tick();
        lasers.tick((int)(rocket.getX() + (rocket.getWidth() / 2) - 8));
        asteroids.tick();
        rocket.tick();
        //stars.toBack();
        rocket.toFront();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    public void dispose() {
        stage.dispose();
    }
}
