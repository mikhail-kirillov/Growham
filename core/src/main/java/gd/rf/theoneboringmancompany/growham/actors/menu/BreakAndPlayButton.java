package gd.rf.theoneboringmancompany.growham.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.screens.EnterNameScreen;
import gd.rf.theoneboringmancompany.growham.tools.MyAnimatedMenuButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class BreakAndPlayButton extends MyAnimatedMenuButton {
    public BreakAndPlayButton(final Main main) {
        super(main, Settings.Path.TextureAtlas.BREAK_AND_PLAY_BUTTON_ATLAS);
        setPosition(0,
                main.stage.getHeight()
                        - BUTTON_SIZE
                        - BUTTON_SIZE/3);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Gdx.files.local(Settings.Path.SERIALIZATION_FILE).exists()){
                    Gdx.files.local(Settings.Path.SERIALIZATION_FILE).delete();
                    sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                    main.setScreen(new EnterNameScreen(main));
                }
                else if (main.objectRefs.hamster.isHasName()){
                    sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                    main.setScreen(new EnterNameScreen(main));
                }
                else {
                    Gdx.input.vibrate(VIBRATION);
                }
            }
        });
    }
}