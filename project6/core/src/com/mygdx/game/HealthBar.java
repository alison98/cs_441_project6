package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class HealthBar extends Actor {
    private Sprite sprite;
    private int height;
    private int width;
    private int currentHealth;
    private int originalHeight;
    private Texture original;
    private final int maxHealth = 24; //max health

    public HealthBar() {
        original = new Texture (Gdx.files.internal("health-bar/health-bar-resized.png"));
        sprite = new Sprite(new TextureRegion(original));
        setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        currentHealth = maxHealth;
        originalHeight = (int)sprite.getHeight();
        setPosition(width - 64,height/2 - getHeight()/2);
    }

    public boolean decreaseHealth(){
        currentHealth --;
        if(currentHealth <= 0){
            sprite.setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), 0);
            return false;
        }
        double percent = (1.0 * currentHealth) / maxHealth;
        int height = (int)(percent * originalHeight);
        TextureRegion region = new TextureRegion(original, 0, (originalHeight - height), original.getWidth(), height);
        sprite.setRegion(region);
        sprite.setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), height);
        return true;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        sprite.draw(batch);
    }

    @Override
    public void positionChanged() {
        sprite.setPosition(getX(), getY());
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
