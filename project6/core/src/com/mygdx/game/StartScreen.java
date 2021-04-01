package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;

public class StartScreen implements Screen{
    private Game game;
    private Stage stage;
    private StarManager stars;
    private int height;
    private int width;
    private Title title;
    private ImageButton start;
    private ImageButton instructions;

    public StartScreen(Game g, StarManager s){
        this.game = g;
        stage = new Stage(new ScreenViewport());
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        makeButton();
        initTitle();
        if(s == null){
            stars = new StarManager(height, width, stage);
        } else{
            stars = s;
            stars.setStage(stage);
        }
    }

    public void initTitle(){
        title = new Title();
        title.setPosition(width/2-(title.getWidth()/2), height-600);
        stage.addActor(title);
    }

    public void makeButton(){
        ImageButton.ImageButtonStyle startStyle = new ImageButton.ImageButtonStyle();
        startStyle.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("start-resized.png"))));
        startStyle.imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("start-resized.png"))));
        ImageButton.ImageButtonStyle instructionsStyle = new ImageButton.ImageButtonStyle();
        instructionsStyle.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("instructions-resized.png"))));
        instructionsStyle.imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("instructions-resized.png"))));
        start = new ImageButton(startStyle);
        instructions = new ImageButton(instructionsStyle);
        start.setPosition(width/2-start.getWidth()/2,height/4-start.getHeight()/2);
        start.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CountdownScreen(game, stars));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        instructions.setPosition(width/2-instructions.getWidth()/2,200);
        instructions.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new InstructionsScreen(game, stars));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(instructions);
        stage.addActor(start);
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.065f, .065f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stars.tick();
        title.toFront();
        start.toFront();
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
