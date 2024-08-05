package gd.rf.theoneboringmancompany.growham.actors.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.io.Serializable;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.AGE_IN_SECONDS;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.INFORMATION_IN_SECONDS;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.MONEY_IN_AGE;

public class Hamster extends Actor implements Serializable {
    private transient Main main;

    private int roomLevel = 1;

    private int money = 500;
    private int health = 100;
    private int hungry = 100;
    private int age = 1;

    protected String name;
    private boolean hasName = false;

    public long endTime;
    public boolean endTimeFlag = false;

    public float time = 0f;
    public float ageTime = 0f;
    private float delta = 0f;

    final int bGym = 2;
    public final int bAll = 0;
    final int bSleep = 1;
    public int button = 0;

    //Костыль для класса Gym
    float HSize = 342;

    private transient TextureAtlas atlas;
    private transient Texture texture;
    private transient Animation<TextureAtlas.AtlasRegion> animation;

    public final float standardX;

    private float X;
    private float Y;
    private String pose;

    /*
    Arrays or map could be used here, but it's more convenient for me.
     */

    private transient TextureAtlas smallSit;
    private transient TextureAtlas smallLeft;
    private transient TextureAtlas smallRight;
    private transient Texture smallSleep;

    private transient TextureAtlas NormalSit;
    private transient TextureAtlas NormalLeft;
    private transient TextureAtlas NormalRight;
    private transient Texture NormalSleep;

    private transient TextureAtlas OldSit;
    private transient TextureAtlas OldLeft;
    private transient TextureAtlas OldRight;
    private transient Texture OldSleep;

    public Hamster(Main main) {
        this.main = main;

        standardX = main.stage.getWidth()/2.5f;
        float standardY = main.stage.getHeight() / 21;

        pose = "Sit";
        X = standardX;
        Y = standardY;
        setPosition(X, Y);

        loadTextures(main);
    }

    public void loadTextures(Main main){
        this.main = main;

        smallSleep = new Texture(Settings.Hamster.Small.SLEEP);
        NormalSleep = new Texture(Settings.Hamster.Normal.SLEEP);
        OldSleep = new Texture(Settings.Hamster.Old.SLEEP);

        smallSit = new TextureAtlas(Settings.Hamster.Small.SIT);
        NormalSit = new TextureAtlas(Settings.Hamster.Normal.SIT);
        OldSit = new TextureAtlas(Settings.Hamster.Old.SIT);

        smallRight = new TextureAtlas(Settings.Hamster.Small.RIGHT);
        NormalRight = new TextureAtlas(Settings.Hamster.Normal.RIGHT);
        OldRight = new TextureAtlas(Settings.Hamster.Old.RIGHT);

        smallLeft = new TextureAtlas(Settings.Hamster.Small.LEFT);
        NormalLeft = new TextureAtlas(Settings.Hamster.Normal.LEFT);
        OldLeft = new TextureAtlas(Settings.Hamster.Old.LEFT);

        hamsterUpdate();

        animation = new Animation<>(Settings.Animation.HAMSTER_ANIMATION_SPEED, atlas.getRegions());
    }

    private void hamsterUpdate() {
        if (age < 200) {
            switch (pose) {
                case "Sit":
                    atlas = smallSit;
                    break;
                case "Right":
                    atlas = smallRight;
                    break;
                case "Left":
                    atlas = smallLeft;
                    break;
                case "Sleep":
                    texture = smallSleep;
                    break;
            }
        } else if (age > 200 && age < 800) {
            switch (pose) {
                case "Sit":
                    atlas = NormalSit;
                    break;
                case "Right":
                    atlas = NormalRight;
                    break;
                case "Left":
                    atlas = NormalLeft;
                    break;
                case "Sleep":
                    texture = NormalSleep;
                    break;
            }
        } else if (age > 800 && age < 1095) {
            switch (pose) {
                case "Sit":
                    atlas = OldSit;
                    break;
                case "Right":
                    atlas = OldRight;
                    break;
                case "Left":
                    atlas = OldLeft;
                    break;
                case "Sleep":
                    texture = OldSleep;
                    break;
            }
        }
        if (getHealth() <= 0 || getHungry() <= 0 || age > 1095) {
            if (age < 200) {
                texture = smallSleep;
            }
            else if (age > 200 && age < 800) {
                texture = NormalSleep;
            }
            else if (age > 800 && age < 1095) {
                texture = OldSleep;
            }
            death();
        }

        animation = new Animation<>(Settings.Animation.HAMSTER_ANIMATION_SPEED, atlas.getRegions());

    }

    private void death(){
        main.database.insert(name, age);
        if (Gdx.files.local(Settings.Path.SERIALIZATION_FILE).exists())
            Gdx.files.local(Settings.Path.SERIALIZATION_FILE).delete();
        main.setScreen(main.objectRefs.scoresScreen);
        main.objectRefs.hamster = new Hamster(main);
    }

    public void liveInformation(){
        if (ageTime >= AGE_IN_SECONDS){
            ageTime = 0;
            age++;
            money += MONEY_IN_AGE;
        }
        if (time >= INFORMATION_IN_SECONDS) {
            time = 0;
            int i = (int) (Math.random() * 5);
            switch (i) {
                case 4:
                    if (getHealth() > 0) setHealth(getHealth() - 1);
                    break;
                case 2:
                    if (getHungry() > 0) setHungry(getHungry() - 1);
                    break;
            }
        }
    }


    public void dispose(){
        if (atlas != null)
            atlas.dispose();
        if (texture != null)
            texture.dispose();

        smallSit.dispose();
        smallLeft.dispose();
        smallRight.dispose();
        smallSleep.dispose();

        NormalLeft.dispose();
        NormalRight.dispose();
        NormalSit.dispose();
        NormalSleep.dispose();

        OldLeft.dispose();
        OldRight.dispose();
        OldSit.dispose();
        OldSleep.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        delta += Gdx.graphics.getDeltaTime();
        ageTime += Gdx.graphics.getDeltaTime();
        time += Gdx.graphics.getDeltaTime();

        liveInformation();
        hamsterUpdate();

        if (pose.equals("Sleep")) {
            batch.draw(texture, X, Y);
        }
        else {
            batch.draw(animation.getKeyFrame(delta, true), X, Y);
        }

        setPosition(X, Y);
    }


    public int getRoomLevel() {return roomLevel;}
    public int getMoney() {return money;}
    public int getHealth() {return health;}
    public int getHungry() {return hungry;}
    public int getAge(){return age;}
    public float getX() {return X;}
    public float getY() {return Y;}
    public boolean isHasName(){return hasName;}
    public void setHealth(int health) {this.health = health;}
    public void setMoney(int money) {this.money = money;}
    public void setName(String name){this.name = name;}
    public void setHasName(boolean hasName){this.hasName = hasName;}
    public void setRoomLevel(int roomLevel) {this.roomLevel = roomLevel;}
    public void setHungry(int hungry) {this.hungry = hungry;}
    public void setX(float x) {X = x;}
    public void setY(float y) {Y = y;}
    public void setAge(int age) {this.age = age;}
    public void setPose(String pose) {this.pose = pose;}
}