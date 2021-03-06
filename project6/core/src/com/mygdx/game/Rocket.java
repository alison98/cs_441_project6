package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rocket extends Actor {
    private Sprite[] sprites;
    private Sprite sprite;
    private int frames;
    private int curr;
    private Rectangle hitbox1;
    private Rectangle hitbox2;

    public Rocket(){
        initSprites();
        sprite = sprites[0];
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        frames = (int)(Math.random() * 5 + 2);
        curr = frames;
        hitbox1 = new Rectangle(getX() + 32, getY() + 144, 176, 64);
        hitbox2 = new Rectangle(getX() + 96, getY() + 208, 80, 144);
    }

    public Rectangle getHitbox1(){
        return hitbox1;
    }

    public Rectangle getHitbox2(){
        return hitbox2;
    }

    private void initSprites(){
        sprites = new Sprite[3];
        sprites[0] = new Sprite(new Texture(Gdx.files.internal("rocket/rocket-1-resized.png")));
        sprites[1] = new Sprite(new Texture(Gdx.files.internal("rocket/rocket-2-resized.png")));
        sprites[2] = new Sprite(new Texture(Gdx.files.internal("rocket/rocket-3-resized.png")));
    }

    public void tick(){
        frames--;
        if(frames == 0){
            int num = (int)(Math.random() * 3);
            while(num == curr){
                num = (int)(Math.random() * 3);
            }
            sprite = sprites[num];
            frames = (int)(Math.random() * 5 + 2);
            curr = frames;
        }
        positionChanged();
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void positionChanged(){
        sprite.setPosition(getX(), getY());
        hitbox1.set(getX() + 32, getY() + 144, 176, 64);
        hitbox2.set(getX() + 96, getY() + 208, 80, 144);
    }

    @Override
    public boolean remove(){
        return super.remove();
    }
}
