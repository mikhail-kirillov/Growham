package gd.rf.theoneboringmancompany.growham.actors.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.screens.ScoresScreen;
import gd.rf.theoneboringmancompany.growham.tools.MyAnimatedMenuButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class ScoresButton extends MyAnimatedMenuButton {
    public ScoresButton(final Main main) {
        super(main, Settings.Path.TextureAtlas.SCORES_BUTTON_ATLAS);
        setPosition(main.stage.getWidth()
                        - BUTTON_SIZE*1.75f,

                main.stage.getHeight()
                        - BUTTON_SIZE
                        - BUTTON_SIZE/8
                        - BUTTON_SIZE/5);

        main.objectRefs.scoresScreen = new ScoresScreen(main);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                main.setScreen(main.objectRefs.scoresScreen);
            }
        });
    }
}