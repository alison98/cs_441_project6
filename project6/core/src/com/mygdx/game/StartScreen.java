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
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0;
    private List<Star> stars;
    private int height;
    private int width;

    public StartScreen(Game g){
        this.game = g;
        stage = new Stage(new ScreenViewport());
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        makeButton();
        initTitle();
        initStars();
    }

    public void initStars(){
        stars = new ArrayList<Star>();
        for(int i = 0; i < 40; i++){
            Star s = new Star();
            s.setPosition((float)(Math.random() * width), (float)(Math.random() * height));
            stars.add(s);
            stage.addActor(s);
        }
    }

    public void initTitle(){
        Title t = new Title();
        t.setPosition(width/2-(t.getWidth()/2), height-600);
        stage.addActor(t);
    }

    public void makeButton(){
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("start-resized.png"))));
        imageButtonStyle.imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("start-resized.png"))));
        ImageButton button = new ImageButton(imageButtonStyle);
        button.setPosition(width/2-button.getWidth()/2,height/4-button.getHeight()/2);
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
        Gdx.gl.glClearColor(.065f, .065f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tick();
        stage.act();
        stage.draw();
    }

    private void tick(){
        for(int i = 0; i < stars.size(); i++){
            Star s = stars.get(i);
            s.setPosition(s.getX(), s.getY() - s.tick());
            if(s.getY() <= -50){
                s.remove();
                stars.remove(i);
                i--;
            }
        }
        if((int)(Math.random() * 25) == 7){
            Star s = new Star();
            s.setPosition((float)(Math.random() * width), height);
            stars.add(s);
            stage.addActor(s);
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
