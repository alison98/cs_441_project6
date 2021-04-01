package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Asteroid extends Actor {
    private Sprite sprite;
    private Sprite sprite0;
    private Sprite sprite1;
    private Sprite sprite2;
    private double v;
    private double curr = 0;
    private int img;
    private int rotation;
    private Rectangle hitbox;
    private int explodeCounter;

    public Asteroid(int i, int r){
        img = i;
        rotation = r;
        String[] filenames = getFileNames();
        sprite0 = new Sprite(new Texture(Gdx.files.internal(filenames[0])));
        sprite1 = new Sprite(new Texture(Gdx.files.internal(filenames[1])));
        sprite2 = new Sprite(new Texture(Gdx.files.internal(filenames[2])));
        sprite = sprite0;
        sprite.setRotation(rotation);
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        v = 2;
        hitbox = new Rectangle(getX() + 48, getY() + 48, 144, 144);
        explodeCounter = 40;
    }

    public void reset(){
        sprite = sprite0;
        positionChanged();
        explodeCounter = 40;
    }

    public String[] getFileNames(){
        String[] ret = new String[3];
        ret[0] = "asteroid-" + img + "-1-resized.png";
        ret[1] = "asteroid-" + img + "-2-resized.png";
        ret[2] = "asteroid-" + img + "-3-resized.png";
        return ret;
    }

    public int tick(){
        double prev = curr;
        curr += v;
        int ret = (int)(curr) - (int)(prev);
        curr -= ret;
        return ret;
    }

    public boolean explodeTick(){
        if(explodeCounter == 40){
            sprite = sprite1;
            positionChanged();
        } else if(explodeCounter == 20){
            sprite = sprite2;
            positionChanged();
        }
        explodeCounter--;
        return explodeCounter >= 0;
    }

    public boolean collides(Laser l){
        return hitbox.overlaps(l.getHitbox());
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void positionChanged(){
        sprite.setPosition(getX(), getY());
        hitbox.set(getX() + 48, getY() + 48, 144, 144);
    }

    @Override
    public boolean remove(){
        return super.remove();
    }
}
