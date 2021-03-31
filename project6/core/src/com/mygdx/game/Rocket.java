package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rocket extends Actor {
    private Sprite[] sprites;
    private Sprite sprite;
    private int frames;
    private int curr;

    public Rocket(){
        initSprites();
        sprite = sprites[0];
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        frames = (int)(Math.random() * 5 + 2);
        curr = frames;
    }

    private void initSprites(){
        sprites = new Sprite[3];
        sprites[0] = new Sprite(new Texture(Gdx.files.internal("rocket-1-resized.png")));
        sprites[1] = new Sprite(new Texture(Gdx.files.internal("rocket-2-resized.png")));
        sprites[2] = new Sprite(new Texture(Gdx.files.internal("rocket-3-resized.png")));
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
    }

    @Override
    public boolean remove(){
        return super.remove();
    }
}
