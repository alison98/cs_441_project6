package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private Game game;
    private Stage stage;
    private int height;
    private int width;
    private Rocket rocket;
    private LaserManager lasers;
    private AsteroidManager asteroids;
    private StarManager stars;
    private int stun = 0;
    private final int t = 25; //stun time
    private Img heart;
    private HealthBar healthBar;
    private Score score;
    private boolean gameOver = false;

    public GameScreen(Game g, StarManager s) {
        game = g;
        stage = new Stage(new ScreenViewport());
        stars = s;
        stars.setStage(stage);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        initRocket();
        initHealth();
        score = new Score(stage);
        lasers = new LaserManager((int)(25 + rocket.getHeight()), stage);
        asteroids = new AsteroidManager(stage, healthBar);
    }

    private void initHealth(){
        heart = new Img("health-bar/heart-resized.png");
        heart.setPosition(width-40 - heart.getWidth()/2, height/2+ 400);
        stage.addActor(heart);
        healthBar = new HealthBar();
        stage.addActor(healthBar);
    }

    private void initRocket(){
        rocket = new Rocket();
        rocket.setPosition(width/2-(rocket.getWidth()/2), 25);
        stage.addActor(rocket);
        rocket.addListener(new DragListener(){
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                if(stun == 0)
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
        if(gameOver){
            game.setScreen(new GameOverScreen(game, stars, score.getScore()));
        }
        tick();
        checkCollisions();
        stage.act();
        stage.draw();
    }

    private void checkCollisions(){
        ArrayList<Asteroid> asteroidList = (ArrayList<Asteroid>) asteroids.getOnScreen();
        ArrayList<Laser> laserList = (ArrayList<Laser>) lasers.getOnScreen();
        for(int i = 0; i < asteroidList.size(); i++){
            Asteroid a = asteroidList.get(i);
            for(int j = 0; j < laserList.size(); j++){
                Laser l = laserList.get(j);
                if(a.collides(l)){
                    asteroids.explode(i);
                    lasers.remove(j);
                    score.add();
                }
            }
        }
        for(int i = 0; i < asteroidList.size(); i++){
            Asteroid a = asteroidList.get(i);
            if (a.collides(rocket)){
                asteroids.explode(i);
                stun = t;
                lasers.hit();
            }
        }
    }

    private void tick(){
        stars.tick();
        lasers.tick((int)(rocket.getX() + (rocket.getWidth() / 2) - 8), stun > 0);
        if(!asteroids.tick(score.getLevel())){
            gameOver = true;
        }
        rocket.tick();
        rocket.toFront();
        heart.toFront();
        healthBar.toFront();
        if(stun > 0){
            stun--;
        }
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
