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

    public Star(){
        sprite = new Sprite(new Texture(Gdx.files.internal("pixil-frame-0.png")));
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        v = Math.random() * 3 + 1;
    }

    public int inc(){
        double prev = curr;
        curr += v;
        return (int)(curr) - (int)(prev);
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
