package gd.rf.theoneboringmancompany.growham.actors.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.tools.MyButton;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class Gym extends MyButton {
    private float time = 0f;
    private boolean clicked = true;
    private boolean back = false;
    private boolean to = false;
    private boolean sit = false;

    public Gym(final Main main) {
        super(main, Settings.Path.Pictures.Buttons.GYM);
        setPosition(main.stage.getWidth() - imgButton.getWidth(), main.stage.getHeight() / 50);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (main.objectRefs.hamster.button == main.objectRefs.hamster.bAll && clicked){
                    sound.play(Settings.MusicAndSound.SOUND_VOLUME);
                    main.objectRefs.hamster.button = main.objectRefs.hamster.bGym;
                    clicked = false;
                    sit = false;
                    back = false;
                    to = true;
                    time = 0;
                    setPosition();

                }
                else {
                    Gdx.input.vibrate(VIBRATION);
                }
            }
        });
    }

    private void to(float delta){
        if (main.objectRefs.hamster.button == main.objectRefs.hamster.bGym && to){
            if (main.objectRefs.hamster.getX() + main.objectRefs.hamster.HSize > 0){
                main.objectRefs.hamster.setX(main.objectRefs.hamster.getX() - delta);
                time = 0f;
            }
            if (main.objectRefs.hamster.getX() + main.objectRefs.hamster.HSize == 0) {
                if (time > 15) {
                    for (int i = 0; i < 10 && main.objectRefs.hamster.getHealth() < 100; i++)
                        main.objectRefs.hamster.setHealth(main.objectRefs.hamster.getHealth() + 1);
                    for (int i = 5; i > 0 && main.objectRefs.hamster.getHungry() > 0; i--)
                        main.objectRefs.hamster.setHungry(main.objectRefs.hamster.getHungry() - 1);
                    to = false;
                    back = true;
                    time = 0f;
                    setPosition();
                }
            }
        }
    }

    private void back(float delta){
        if (main.objectRefs.hamster.button == main.objectRefs.hamster.bGym && back) {
            if (main.objectRefs.hamster.getX() < main.objectRefs.hamster.standardX) {
                main.objectRefs.hamster.setX(main.objectRefs.hamster.getX() + delta);
                time = 0f;
            }
            if (main.objectRefs.hamster.getX() == main.objectRefs.hamster.standardX) {
                to = false;
                back = false;
                sit = true;
                setPosition();
                sit = false;
            }
        }
    }

    private void ReClick(){
        if (time > 28 && main.objectRefs.hamster.button == main.objectRefs.hamster.bGym
                && main.objectRefs.hamster.getX() == main.objectRefs.hamster.standardX) {
            clicked = true;
            main.objectRefs.hamster.button = main.objectRefs.hamster.bAll;
        }
    }

    private void setPosition(){
        if (main.objectRefs.hamster.button == main.objectRefs.hamster.bGym) {
            if (to) {
                main.objectRefs.hamster.setPose("Left");
            }
            if (back) {
                main.objectRefs.hamster.setPose("Right");
            }
            if (sit) {
                main.objectRefs.hamster.setPose("Sit");
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        time += Gdx.graphics.getDeltaTime();
        if (to){
            setPosition();
            to(parentAlpha);
        }
        if (back){
            setPosition();
            back(parentAlpha);
        }
        ReClick();
    }
}