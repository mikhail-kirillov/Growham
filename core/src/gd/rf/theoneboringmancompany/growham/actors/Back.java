package gd.rf.theoneboringmancompany.growham.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.screens.LogoScreen;
import gd.rf.theoneboringmancompany.growham.screens.MenuScreen;
import gd.rf.theoneboringmancompany.growham.screens.PlayScreen;
import gd.rf.theoneboringmancompany.growham.tools.MyButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class Back extends MyButton {
    public Back(final Main main, final int toScreen) {
        super(main, Settings.Path.Pictures.Buttons.BACK);
        setPosition(0, main.stage.getHeight() - getHeight());

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                switch (toScreen) {
                    case LogoScreen.SCREEN_NUMBER:
                        main.methods.exit();
                        break;
                    case MenuScreen.SCREEN_NUMBER:
                        main.setScreen(main.objectRefs.menuScreen);
                        break;
                    case PlayScreen.SCREEN_NUMBER:
                        main.setScreen(main.objectRefs.playScreen);
                        break;
                    default:
                        Gdx.input.vibrate(VIBRATION);
                        break;
                }
            }
        });
    }
}