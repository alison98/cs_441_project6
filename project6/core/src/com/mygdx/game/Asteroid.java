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
    private int img;
    private int rotation;

    public Asteroid(int i, int r){
        img = i;
        rotation = r;
        sprite = new Sprite(new Texture(Gdx.files.internal(getFileName())));
        sprite.setRotation(rotation);
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        v = 2;
    }

    public String getFileName(){
        return "asteroid-" + img + "-1-resized.png";
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
