package gd.rf.theoneboringmancompany.growham.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import gd.rf.theoneboringmancompany.growham.Main;

public abstract class MyAnimatedMenuButton extends Button {
    protected final Main main;

    private Animation<TextureAtlas.AtlasRegion> animation;

    protected Sound sound;

    private float delta = 0f;

    protected final float BUTTON_SIZE;

    public MyAnimatedMenuButton(final Main main, String pathToAtlas) {
        this.main = main;

        TextureAtlas atlas = MyAssetsManagers.textureAtlasManager.get(pathToAtlas, TextureAtlas.class);
        animation = new Animation<>(Settings.Animation.MENU_BUTTON_ANIMATION_SPEED, atlas.getRegions());

        sound = MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.BUTTON, Sound.class);

        BUTTON_SIZE =
                ((atlas.createSprite(Settings.ATLAS_TEXTURE_NAME).getHeight())/3)
                        +((atlas.createSprite(Settings.ATLAS_TEXTURE_NAME).getWidth())/3);

        this.setHeight(BUTTON_SIZE);
        this.setWidth(BUTTON_SIZE);
    }

    public void dispose(){}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        delta += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(delta, true), getX(), getY());
    }
}