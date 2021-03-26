package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StartScreen implements Screen{
    private Game game;
    private Stage stage;

    public StartScreen(Game g) {
        this.game = g;
        stage = new Stage(new ScreenViewport());
        makeButton();
        makeLabel();
    }

    public void makeLabel(){
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.WHITE;

        Label label1 = new Label("Title (BitmapFont)",label1Style);
        label1.setSize(Gdx.graphics.getWidth(),100);
        label1.setPosition(0,Gdx.graphics.getHeight()-100*2);
        label1.setAlignment(Align.center);
        stage.addActor(label1);
    }

    public void makeButton(){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
        textButtonStyle.font = font;
        TextButton button = new TextButton("Button1", textButtonStyle);

        button.setWidth(Gdx.graphics.getWidth()/2);
        button.setPosition(Gdx.graphics.getWidth()/2-button.getWidth()/2,Gdx.graphics.getHeight()/4-button.getHeight()/2);
        button.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button);
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.075f, .125f, .325f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
