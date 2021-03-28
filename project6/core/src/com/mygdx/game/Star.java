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
        v = Math.random() * 3 + 1;
        sprite = new Sprite(new Texture(Gdx.files.internal(getImg())));
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

    private String getImg(){
        if(v < 1.5)
            return "star-7-pixels.png";
        else if(v < 2)
            return "star-9-pixels.png";
        else if(v < 2.5)
            return "star-11-pixels.png";
        else
            return "star-13-pixels.png";
    }

    public int inc(){
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
