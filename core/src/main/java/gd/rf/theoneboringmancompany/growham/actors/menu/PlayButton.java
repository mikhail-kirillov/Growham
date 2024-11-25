package gd.rf.theoneboringmancompany.growham.actors.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.screens.PlayScreen;
import gd.rf.theoneboringmancompany.growham.tools.MyAnimatedMenuButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class PlayButton extends MyAnimatedMenuButton {
    public PlayButton(final Main main) {
        super(main, Settings.Path.TextureAtlas.PLAY_BUTTON_ATLAS);
        setPosition(main.stage.getWidth()/2
                        - BUTTON_SIZE/2
                        - BUTTON_SIZE/6,

                main.stage.getHeight()/2
                        - BUTTON_SIZE/4
                        - BUTTON_SIZE/1);

        main.objectRefs.playScreen = new PlayScreen(main);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                main.setScreen(main.objectRefs.playScreen);
            }
        });
    }
}