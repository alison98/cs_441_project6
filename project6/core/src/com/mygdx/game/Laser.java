package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Laser extends Actor {
    private Sprite sprite;
    private Rectangle hitbox;

    public Laser() {
        sprite = new Sprite(new Texture(Gdx.files.internal("laser.png")));
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        hitbox = new Rectangle(getX(), getY(), 16, 80);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        sprite.draw(batch);
    }

    @Override
    public void positionChanged() {
        sprite.setPosition(getX(), getY());
        hitbox.set(getX(), getY(), 16, 80);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
