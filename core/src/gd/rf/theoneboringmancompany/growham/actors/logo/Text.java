package gd.rf.theoneboringmancompany.growham.actors.logo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.screens.MenuScreen;
import gd.rf.theoneboringmancompany.growham.tools.MyAssetsManagers;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.Text.Blink.SPEED;

public class Text extends Actor {
    private float time;
    private Label text;

    private boolean blinkFlag = true;

    public Text(final Main main) {
        text = new Label(Settings.Text.DOUBLE_TAP, new Label.LabelStyle(main.font, Color.WHITE));
        text.setPosition(main.stage.getWidth()/2, main.stage.getHeight()/12);
        setHeight(main.stage.getHeight());
        setWidth(main.stage.getWidth());

        main.objectRefs.menuScreen = new MenuScreen(main);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.OK, Sound.class)
                        .play(Settings.MusicAndSound.SOUND_VOLUME);
                main.setScreen(main.objectRefs.menuScreen);
            }
        });
    }

    private void blinkColor(){
        if (time >= SPEED){
            time = 0;
            if (blinkFlag){
                //White transparent color
                text.setColor(1,1,1,175/255f);
                blinkFlag = false;
            }
            else {
                //Clear white color
                text.setColor(1,1,1, 255/255f);
                blinkFlag = true;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        time += Gdx.graphics.getDeltaTime();
        text.draw(batch, parentAlpha);
        blinkColor();
    }
}
