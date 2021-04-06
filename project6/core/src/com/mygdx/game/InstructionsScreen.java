package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class InstructionsScreen implements Screen {
    private Game game;
    private Stage stage;
    private StarManager stars;
    private int width;
    private int height;
    private ImageButton back;

    public InstructionsScreen(Game g, StarManager s) {
        game = g;
        stars = s;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        stage = new Stage(new ScreenViewport());
        stars.setStage(stage);
        initText();
        makeButton();
    }

    private void initText(){
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        label1Style.font = myFont;
        String text = "Defend the Earth!\n" +
                "\nAny asteroids that pass\nyour ship will damage\nthe planet,\nso destroy them quickly!\n" +
                "\nThe asteroid shower will\ngain speed over time\n" +
                "\nDrag your ship to move it.\nYour lasers will fire\nautomatically as you move\n" +
                "\nAsteroids that hit your ship\nwill damage your blaster,\nso be careful!";
        Label label1 = new Label(text, label1Style);
        label1.setSize(width, height);
        label1.setPosition(0,150);
        label1.setAlignment(Align.center);
        stage.addActor(label1);
    }

    private void makeButton(){
        ImageButton.ImageButtonStyle startStyle = new ImageButton.ImageButtonStyle();
        startStyle.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("text/back-resized.png"))));
        startStyle.imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("text/back-resized.png"))));
        back = new ImageButton(startStyle);
        back.setPosition(width/2-back.getWidth()/2,100);
        back.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new StartScreen(game, stars));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(back);
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
