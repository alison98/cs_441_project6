package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class Score extends Actor {
    private int width;
    private int height;
    private Stage stage;
    private int score = 0;
    private Label label;
    private static int base = 10; //base score
    private static int inc = 1; //additional score per level
    private int level = 0;
    private static int level1 = 100; //base points per level increase
    private static int levelInc = 200; //additional points per level increase

    public Score(Stage s){
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        stage = s;
        initLabel();
    }

    private void initLabel(){
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style.font = myFont;
        String text = score + "\nLevel: " + level;
        label = new Label(text, label1Style);
        label.setSize(width - 25, 200);
        label.setPosition(0,height - 200);
        label.setAlignment(Align.right);
        stage.addActor(label);
    }

    public void add(){
        System.out.println(score);
        score += base + level * inc;
        if(score >= levelScore(level)){
            level++;
        }
        String text = score + "\nLevel: " + level;
        label.setText(text);
    }

    public int getLevel(){
        return level;
    }

    private int levelScore(int n){
        return (level1 + ((level1 + levelInc) * n));
    }
}
