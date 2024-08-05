package gd.rf.theoneboringmancompany.growham.actors.market;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.tools.MyAssetsManagers;
import gd.rf.theoneboringmancompany.growham.tools.MyButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class Food extends MyButton {
    public Food(final Main main) {
        super(main, Settings.Path.Pictures.Buttons.BUY,
                    Settings.Path.Pictures.Icons.FOOD,
                    Settings.Prices.FOOD);

        setPosition(main.stage.getWidth() - imgButton.getWidth()*1.5f,
                main.stage.getHeight()/2 - main.stage.getHeight()/12);

        setX_and_Y();

        sound = MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.HEALTH, Sound.class);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (main.objectRefs.hamster.getHungry() < 100 &&
                        main.objectRefs.hamster.getMoney() >= Integer.parseInt(howMuch)){

                    sound.play(Settings.MusicAndSound.SOUND_VOLUME);

                    main.objectRefs.hamster.setMoney(main.objectRefs.hamster.getMoney()
                                                    - Integer.parseInt(howMuch));

                    for (int i = 0; i < 5 && main.objectRefs.hamster.getHungry() < 100; i++){
                        main.objectRefs.hamster.setHungry(main.objectRefs.hamster.getHungry() + 1);
                    }
                }
                else {
                    Gdx.input.vibrate(VIBRATION);
                }
            }
        });
    }
}