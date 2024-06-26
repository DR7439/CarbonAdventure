package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.noname.carbonadventure.Screens.PlayScreen;

import java.util.Vector;

public abstract class NPC extends Sprite {
    public Vector2 trainVelocity;

    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;

    public NPC(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineNPC();

        velocity = new Vector2(.1f,.1f);
        b2body.setActive(false);
    }

    protected abstract void defineNPC();
    public abstract void update(float dt);

    public abstract void BodyHit();

    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }


}
