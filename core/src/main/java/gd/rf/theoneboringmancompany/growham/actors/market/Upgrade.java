package gd.rf.theoneboringmancompany.growham.actors.market;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.Back;
import gd.rf.theoneboringmancompany.growham.actors.Background;
import gd.rf.theoneboringmancompany.growham.screens.PlayScreen;
import gd.rf.theoneboringmancompany.growham.tools.MyButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class Upgrade extends MyButton {
    public Upgrade(final Main main){
        super(main, Settings.Path.Pictures.Buttons.UPGRADE,
                    Settings.Path.Pictures.Icons.UPGRADE,
                    Settings.Prices.UPGRADE);

        setPosition(main.stage.getWidth() - imgButton.getWidth() * 1.5f,
                main.stage.getHeight() / 2 - main.stage.getHeight() / 2.5f);

        setX_and_Y();

        //Поправляю расположение рисунка
        imgX = imgX - img.getWidth()/4f;

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (main.objectRefs.hamster.getMoney() >= Integer.parseInt(howMuch)){
                    sound.play(Settings.MusicAndSound.SOUND_VOLUME);

                    main.objectRefs.hamster.setRoomLevel(main.objectRefs.hamster.getRoomLevel()+1);
                    main.objectRefs.hamster.setMoney(main.objectRefs.hamster.getMoney()
                                                    - Integer.parseInt(howMuch));

                    if (main.objectRefs.hamster.getHealth() < 100)
                        main.objectRefs.hamster.setHealth(main.objectRefs.hamster.getHealth()+1);
                }
                else {
                    Gdx.input.vibrate(VIBRATION);
                }

                if (main.objectRefs.hamster.getRoomLevel() > 1){
                    main.stage.clear();
                    main.stage.addActor(new Background(main));
                    main.stage.addActor(new Back(main, PlayScreen.SCREEN_NUMBER));
                    main.stage.addActor(new Med(main));
                    main.stage.addActor(new Food(main));
                }

            }
        });
    }
}