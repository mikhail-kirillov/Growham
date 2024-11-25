package gd.rf.theoneboringmancompany.growham.actors.logo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class Logotype extends Actor {
    private Texture img;

    public Logotype() {
        img = new Texture(Settings.Path.Pictures.Images.LOGO);
    }

    public void dispose(){
        img.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, 0, 0);
    }
}