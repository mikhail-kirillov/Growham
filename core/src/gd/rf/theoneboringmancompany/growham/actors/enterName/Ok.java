package gd.rf.theoneboringmancompany.growham.actors.enterName;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.play.Gym;
import gd.rf.theoneboringmancompany.growham.actors.play.Sleep;
import gd.rf.theoneboringmancompany.growham.screens.EnterNameScreen;
import gd.rf.theoneboringmancompany.growham.screens.PlayScreen;
import gd.rf.theoneboringmancompany.growham.tools.MyButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.AGE;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.HEALTH;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.HUNGRY;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.MONEY;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.ROOM_LEVEL;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.STANDARD_POSE;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class Ok extends MyButton {
    public Ok(final Main main, final PlayScreen play, final EnterNameScreen enterName) {
        super(main, Settings.Path.Pictures.Buttons.OK);
        setPosition(main.stage.getWidth()/2 + main.stage.getWidth()/4,
                    main.stage.getHeight()/2 - imgButton.getHeight()/2f);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!enterName.textField.getText().equals("")){
                    sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                    main.objectRefs.hamster.setName(enterName.textField.getText());
                    main.objectRefs.hamster.setHasName(true);
                    main.objectRefs.hamster.button = main.objectRefs.hamster.bAll;
                    main.objectRefs.gym = new Gym(main);
                    main.objectRefs.sleep = new Sleep(main);
                    main.objectRefs.hamster.setRoomLevel(ROOM_LEVEL);
                    main.objectRefs.hamster.setPose(STANDARD_POSE);
                    main.objectRefs.hamster.setX(main.objectRefs.hamster.standardX);
                    main.objectRefs.hamster.setHungry(HUNGRY);
                    main.objectRefs.hamster.setHealth(HEALTH);
                    main.objectRefs.hamster.setAge(AGE);
                    main.objectRefs.hamster.setMoney(MONEY);
                    main.setScreen(play);
                }
                else {
                    Gdx.input.vibrate(VIBRATION);
                }
            }
        });
    }
}