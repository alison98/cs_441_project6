package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Asteroid extends Actor {
    private Sprite sprite;
    private double v;
    private double curr = 0;

    public Asteroid(){
        sprite = new Sprite(new Texture(Gdx.files.internal(chooseRandomImg())));
        sprite.setRotation(90 * ((int)(Math.random() * 4)));
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        v = 2;
    }

    public String chooseRandomImg(){
        int num = (int)(Math.random() * 3 + 1);
        return "asteroid-" + num + "-1-resized.png";
    }

    public int tick(){
        double prev = curr;
        curr += v;
        int ret = (int)(curr) - (int)(prev);
        curr -= ret;
        return ret;
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void positionChanged(){
        sprite.setPosition(getX(), getY());
    }

    @Override
    public boolean remove(){
        return super.remove();
    }
}
