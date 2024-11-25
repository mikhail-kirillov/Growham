package gd.rf.theoneboringmancompany.growham.actors.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.tools.MyButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class Sleep extends MyButton {
    private float time = 0f;
    private boolean clicked = true;

    public Sleep(final Main main) {
        super(main, Settings.Path.Pictures.Buttons.SLEEP);
        setPosition(main.stage.getWidth() - imgButton.getWidth(),
                    imgButton.getHeight() + main.stage.getHeight()/18);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (main.objectRefs.hamster.button == main.objectRefs.hamster.bAll && clicked) {
                    sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                    main.objectRefs.hamster.button = main.objectRefs.hamster.bSleep;
                    clicked = false;
                    time = 0f;
                    main.objectRefs.hamster.setPose("Sleep");
                    if (main.objectRefs.hamster.getHealth() < 100)
                        main.objectRefs.hamster.setHealth(main.objectRefs.hamster.getHealth() + 1);
                    if (main.objectRefs.hamster.getHungry() < 100)
                        main.objectRefs.hamster.setHungry(main.objectRefs.hamster.getHungry() + 1);
                }
                else {
                    Gdx.input.vibrate(VIBRATION);
                }
            }
        });
    }

    private void setOutSleep(){
        if (main.objectRefs.hamster.button == main.objectRefs.hamster.bSleep && time > 20){
            main.objectRefs.hamster.setPose("Sit");
            main.objectRefs.hamster.button = main.objectRefs.hamster.bAll;
        }
    }

    private void Sleep(){
        if (time > 100){
            time = 0;
            clicked = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        time+= Gdx.graphics.getDeltaTime();
        setOutSleep();
        Sleep();
    }

}