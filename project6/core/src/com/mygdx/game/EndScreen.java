package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EndScreen implements Screen{
    private Game game;
    private Stage stage;
    private StarManager stars;
    private int width;
    private int height;
    private ImageButton tryAgain;
    private Img highScores;
    private int score;

    public EndScreen(Game g, StarManager s, int newScore) {
        game = g;
        stars = s;
        score = newScore;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        stage = new Stage(new ScreenViewport());
        stars.setStage(stage);
        makeButton();
        initTitle();
        initText();
    }

    private void initTitle(){
        highScores = new Img("text/high-scores-resized.png");
        highScores.setPosition(width/2-(highScores.getWidth()/2), height-(highScores.getHeight() + 50));
        stage.addActor(highScores);
    }

    private void initText(){
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        label1Style.font = myFont;
        String text = getScores();
        Label label1 = new Label(text, label1Style);
        label1.setSize(width, height - highScores.getHeight() - tryAgain.getHeight() - 150);
        label1.setPosition(0,tryAgain.getHeight() + 100);
        label1.setAlignment(Align.center);
        stage.addActor(label1);
    }

    private List<Integer> toIntList(List<String> strings){
        List<Integer> ret = new ArrayList<Integer>();
        for(String s: strings){
            ret.add(Integer.parseInt(s));
        }
        return ret;
    }

    private String getScores(){
        FileHandle handle = Gdx.files.local("scores.txt");
        if (!handle.exists()) {
            try{
                handle.file().createNewFile();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        String text = handle.readString();
        if(text.equals("")){
            handle.writeString(score + "", false);
            return score + "";
        }
        List<String> scoresStr = Arrays.asList(text.split(","));
        List<Integer> scores = toIntList(scoresStr);
        if(scores.size() < 10){
            scores.add(score);
        } else{
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for(int i = 0; i < 10; i++){
                if(scores.get(i) < min){
                    min = i;
                    minIndex = i;
                }
            }
            if(score > min){
                scores.remove(minIndex);
                scores.add(score);
            }
        }
        Collections.sort(scores, Collections.<Integer>reverseOrder());
        String writeString = scores.toString();
        writeString = writeString.substring(1, writeString.length() - 1);
        writeString = writeString.replace(" ", "");
        handle.writeString(writeString, false);
        return writeString.replace(",", "\n");
    }

    private void makeButton(){
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("text/try-again-resized.png"))));
        style.imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("text/try-again-resized.png"))));
        tryAgain = new ImageButton(style);
        tryAgain.setPosition(width/2-tryAgain.getWidth()/2,100);
        tryAgain.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CountdownScreen(game, stars));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(tryAgain);
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
        tryAgain.toFront();
        highScores.toFront();
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
