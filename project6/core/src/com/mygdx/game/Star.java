package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Star extends Actor {
    private Sprite sprite;
    private double v;
    private double curr = 0;

    public Star(int img){
        setSprite(img);
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

    public double getV(){
        return v;
    }

    private void setSprite(int n){
        if(n == 1) {
            v = (Math.random() + 1 / 2);
            sprite = new Sprite(new Texture(Gdx.files.internal("stars/star-7-pixels.png")));
        } else if(n == 2) {
            v = (Math.random() + 1.5 / 2);
            sprite = new Sprite(new Texture(Gdx.files.internal("stars/star-9-pixels.png")));
        } else if(n == 3) {
            v = (Math.random() + 2 / 2);
            sprite = new Sprite(new Texture(Gdx.files.internal("stars/star-11-pixels.png")));
        } else {
            v = (Math.random() + 2.5 / 2);
            sprite = new Sprite(new Texture(Gdx.files.internal("stars/star-13-pixels.png")));
        }
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
