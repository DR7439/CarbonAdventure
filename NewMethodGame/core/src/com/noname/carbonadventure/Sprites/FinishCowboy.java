package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;

import static com.noname.carbonadventure.Play.player;

public class FinishCowboy extends InteractiveTileObject {
    private PlayScreen screen;

    public FinishCowboy(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {
        HUD.addScore(2000);
        HUD.setWorldTimer(120);
        HUD.setCarbonMeter(0);
        Gdx.app.log("Finish", "Finish line has been triggered.");
        // 2410/Play.PPM,1570/Play.PPM
        float destinationX = 390/Play.PPM;// Change this to the desired X coordinate
        float destinationY = 540/Play.PPM;// Change this to the desired Y coordinate

        // Teleport the player to the destination
        screen.teleportPlayer(player, destinationX, destinationY);
        screen.updateMiniMap("maps/Level_1.3.tmx");
        screen.onPlayerTeleportedCowboyDefeat();
        screen.setCowboyFinishTriggered(true);
    }
}
