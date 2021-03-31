package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Countdown extends Actor {
    private Sprite sprite;
    private int frames;
    private boolean isDone;

    public Countdown(){
        sprite = new Sprite(new Texture(Gdx.files.internal("countdown-3-resized.png")));
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        frames = 0;
        isDone = false;
    }

    public void tick(){
        frames++;
        if(frames == 60){
            sprite = new Sprite(new Texture(Gdx.files.internal("countdown-2-resized.png")));
        } else if(frames == 120){
            sprite = new Sprite(new Texture(Gdx.files.internal("countdown-1-resized.png")));
        } else if(frames == 180){
            isDone = true;
        }
        positionChanged();
    }

    public boolean isDone(){
        return isDone;
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
