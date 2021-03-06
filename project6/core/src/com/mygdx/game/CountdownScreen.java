package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CountdownScreen implements Screen {
    private Game game;
    private Stage stage;
    private StarManager stars;
    private Countdown c;

    public CountdownScreen(Game g, StarManager s) {
        game = g;
        stage = new Stage(new ScreenViewport());
        stars = s;
        stars.setStage(stage);
        c = new Countdown();
        stage.addActor(c);
        c.setPosition(Gdx.graphics.getWidth()/2 - c.getWidth()/2, Gdx.graphics.getHeight()-800);
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.065f, .065f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(c.isDone()){
            game.setScreen(new GameScreen(game, stars));
        }
        c.tick();
        stars.tick();
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
