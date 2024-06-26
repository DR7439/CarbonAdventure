package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Sprites.Player;

public class HUD implements Disposable {
    public static Stage stage;
    private Viewport viewport;
    private static Integer worldTimer;
    private float timeCount;
    private static Integer score = 0;
    private static Integer carbonMeter = 0;
    private static final int MAX_CARBON = 100;
    private WidgetGroup carbonMeterGroup;
    private Image carbonMeterBase;
    private Image carbonMeterFill;

  



    static Label carbonMeterLabel;

    Label countdownLabel;
    static Label scoreLabel;
    private Player player;
    Label timelabel;

    Label levellabel;
    Label worldlabel;
    Label playerlabel;

    private static Array<Image> gemIcons = new Array<>();
    private static final int ICON_SIZE = 16;
    private static final int ICON_PADDING = 4;

    private Image charInfoImage;

    private TextureAtlas UIatlas;

    private Texture playerHeadTexture;
    private Image playerHeadImage;

    private Texture carbonMeterTexture;

    private static float maxTime; // this is in minutes
    private static float currentTime = 0.0f; // this is in seconds
    private Image timeBarBase;
    private Image timeBarFill;
    private Texture timeBarTexture;

    private Skin skin;

    public HUD(SpriteBatch sb, Player player) {
        this.UIatlas = new TextureAtlas("ui.atlas");
        this.playerHeadTexture = new Texture("img/Playerhead.png");
        this.playerHeadImage = new Image(playerHeadTexture);

        // Set position of the image on the HUD (modify x, y as needed)
        this.playerHeadImage.setPosition(2, Play.V_HEIGHT - 55 - playerHeadImage.getHeight());
        this.playerHeadImage.setSize(80, 80);

        // Option 2: Scale the image
        this.playerHeadImage.setScale(0.5f, 0.75f);

        this.player = player;

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        this.carbonMeterTexture = new Texture(pixmap);
        pixmap.dispose();

        // Use this texture for the meter background and fill
        TextureRegionDrawable carbonMeterDrawable = new TextureRegionDrawable(new TextureRegion(carbonMeterTexture));

        carbonMeterBase = new Image(carbonMeterDrawable);
        carbonMeterBase.setColor(Color.DARK_GRAY);
        carbonMeterBase.setSize(1, 1);
        carbonMeterBase.setPosition(38.8f, Play.V_HEIGHT - 14.5f);

        // Carbon Meter Fill
        carbonMeterFill = new Image(carbonMeterDrawable);
        carbonMeterFill.setColor(Color.WHITE);
        carbonMeterFill.setSize(0, 1);
        carbonMeterFill.setPosition(38.8f, Play.V_HEIGHT - 14.5f);

        Pixmap pixmapTime = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmapTime.setColor(Color.BLUE);
        pixmapTime.fill();
        timeBarTexture = new Texture(pixmapTime);
        pixmapTime.dispose();

        TextureRegionDrawable timeBarDrawable = new TextureRegionDrawable(new TextureRegion(timeBarTexture));
        timeBarBase = new Image(timeBarDrawable);
        timeBarBase.setColor(Color.DARK_GRAY);
        timeBarBase.setSize(0, 20);
        timeBarBase.setPosition(38.8f, Play.V_HEIGHT - 22.5f);

        timeBarFill = new Image(timeBarDrawable);
        timeBarFill.setColor(Color.BLUE);
        timeBarFill.setSize(0, 20);
        timeBarFill.setPosition(38.8f, Play.V_HEIGHT - 26.9f);

        worldTimer = 6000; // This is in seconds
        maxTime = worldTimer/60F;
        //timeCount = 0;
        //score = null;

        viewport = new FitViewport(Play.V_WIDTH, Play.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        TextureAtlas.AtlasRegion charInfoRegion = UIatlas.findRegion("char_info");
        if (charInfoRegion != null) {
            this.charInfoImage = new Image(charInfoRegion);
            charInfoImage.setPosition(5, Play.V_HEIGHT - charInfoImage.getHeight() - 30);
            charInfoImage.setSize(100, 50);

            // Add to the stage
            stage.addActor(charInfoImage);
            stage.addActor(playerHeadImage);
            stage.addActor(carbonMeterBase);
            stage.addActor(carbonMeterFill);
            stage.addActor(timeBarBase);
            stage.addActor(timeBarFill);
        }

        //Table table = new Table();
        //table.top();
        //table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabel = new Label(String.format("%06d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabel = new Label(String.format("%01d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //carbonMeterLabel = new Label(String.format("%07d", carbonMeter), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //timelabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //levellabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldlabel = new Label("Carbon Meter", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldlabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //playerlabel = new Label("GEM COUNTER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //table.add(playerlabel).expandX().padTop(10);
        //table.add(worldlabel).expandX().padTop(10);
        //table.add(timelabel).expandX().padTop(10);
        //table.row();
        //table.add(scoreLabel).expandX();
        //table.add(carbonMeterLabel).expandX();
        //table.add(levellabel).expandX();
        //table.add(countdownLabel).expandX();

        //stage.addActor(table);


    }

    public static int getCarbonMeter() {
        return carbonMeter;
    }
    public static void setCarbonMeter(int carbon) {
        carbonMeter = carbon;
    }
    public static int getWorldTimer() {return worldTimer;}

    public static void setWorldTimer(int time){
        worldTimer = time;
        currentTime = 0;
        maxTime = worldTimer/60F;
    }

    public static void addScore(int additionalScore){
        score += additionalScore;
    }

    public static void setScore(int time, int carbon){
        int newscore = score + (time * 10 - carbon * 10);
        score = Math.max(newscore, 0);

    }

    public static int getScore() {return score;}

    public static void resetScore() {
        score = 0; // Reset score to zero at the start of each new game
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            if (worldTimer > 0) {
                worldTimer--;
                countdownLabel.setText(String.format("%03d", worldTimer));
            } else {
                Play.player.dead();
            }
            timeCount = 0;

            updateCarbonMeter();
            updateCarbonMeter();
            updateTimeBar(dt);
        }
    }

    private void updateTimeBar(float dt) {
        float clampedDt = Math.min(dt, 0.02f);
        currentTime += clampedDt;
        if (currentTime > maxTime) {
            currentTime = maxTime;
        }
        float width = 51.75f * (currentTime / maxTime);
        timeBarFill.setSize(width, 6.25f);
    }

    private void updateCarbonMeter() {
        float width = (float) carbonMeter / MAX_CARBON * 60.5f;
        carbonMeterFill.setSize(width, 6.25f);
    }

    public static void increaseCarbonMeter(int value) {
        int newCarbon = carbonMeter + value;
        carbonMeter = Math.max(newCarbon, 0);
        if (carbonMeter > MAX_CARBON) {
            carbonMeter = MAX_CARBON;
            Play.player.dead();
        }
    }


    public static void addGemIcon(String texturePath) {

        if (texturePath == null || texturePath.isEmpty()) {
            texturePath = "img/croppedGem.png";
        }

        TextureRegion gemRegion = new TextureRegion(new Texture(texturePath));
        Image gemImage = new Image(gemRegion);
        // Calculate position based on the number of already collected gems
        int offset = gemIcons.size * (ICON_SIZE + ICON_PADDING);
        gemImage.setPosition(+offset, Play.V_HEIGHT - 93);
        gemImage.setSize(100, 100);

        stage.addActor(gemImage);
        gemIcons.add(gemImage);

    }

    public static void resetGemIcons() {
        for (Image gemIcon : gemIcons) {
            gemIcon.remove(); // This will remove the icon from the stage
        }
        gemIcons.clear(); // Now clear the list to remove all references
    }


    public static void levelReset(int levelTime) {
        int time = getWorldTimer();
        int carbon = getCarbonMeter();
        System.out.println(time);
        System.out.println(carbon);
        setScore(time, carbon);
        setCarbonMeter(0);
        setWorldTimer(levelTime);
        resetGemIcons();
    }

    @Override
    public void dispose() {
        stage.dispose();
        carbonMeterTexture.dispose();
        if (timeBarTexture != null) {
            timeBarTexture.dispose();
        }
    }
}


