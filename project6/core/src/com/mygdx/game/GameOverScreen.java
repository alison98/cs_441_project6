package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameOverScreen implements Screen {
    private Game game;
    private Stage stage;
    private StarManager stars;
    private Img img;
    private int ticks = 120;
    private int score;

    public GameOverScreen(Game g, StarManager s, int newScore) {
        game = g;
        stage = new Stage(new ScreenViewport());
        stars = s;
        score = newScore;
        stars.setStage(stage);
        img = new Img("text/game-over-resized.png");
        stage.addActor(img);
        img.setPosition(Gdx.graphics.getWidth()/2 - img.getWidth()/2, Gdx.graphics.getHeight()-800);
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.065f, .065f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(ticks <= 0){
            game.setScreen(new EndScreen(game, stars, score));
        }
        stars.tick();
        ticks--;
        stage.act();
        stage.draw();
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
