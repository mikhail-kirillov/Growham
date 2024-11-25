package gd.rf.theoneboringmancompany.growham.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.play.Hamster;
import gd.rf.theoneboringmancompany.growham.screens.LogoScreen;
import gd.rf.theoneboringmancompany.growham.screens.MenuScreen;
import gd.rf.theoneboringmancompany.growham.screens.PlayScreen;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.AGE_IN_SECONDS;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.INFORMATION_IN_SECONDS;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.STANDARD_POSE;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.VIBRATION;

public class GeneralMethods {
    private final Main main;

    public GeneralMethods(Main main) {
        this.main = main;
    }

    public void inputBack(int toScreen){
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            switch (toScreen) {
                case LogoScreen.SCREEN_NUMBER:
                    MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.OK, Sound.class)
                            .play(Settings.MusicAndSound.SOUND_VOLUME);
                    exit();
                    break;
                case MenuScreen.SCREEN_NUMBER:
                    MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.OK, Sound.class)
                            .play(Settings.MusicAndSound.SOUND_VOLUME);
                    main.setScreen(main.objectRefs.menuScreen);
                    break;
                case PlayScreen.SCREEN_NUMBER:
                    MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.OK, Sound.class)
                            .play(Settings.MusicAndSound.SOUND_VOLUME);
                    main.setScreen(main.objectRefs.playScreen);
                    break;
                default:
                    Gdx.input.vibrate(VIBRATION);
                    break;
            }
        }
    }

    public void recoveryHamster(){
        if (Gdx.files.local(Settings.Path.SERIALIZATION_FILE).exists()){
            main.objectRefs.hamster = Serialization.read();
            main.objectRefs.hamster.setPose(STANDARD_POSE);
            main.objectRefs.hamster.loadTextures(main);
        }
        else {
            main.objectRefs.hamster = new Hamster(main);
        }
    }

    public void liveHamster(){
        if (main.objectRefs.hamster.endTimeFlag){
            long n = (main.beginTime - main.objectRefs.hamster.endTime) / 1000;
            long time = n / 10;
            long age = n / 3600;
            for (long i = 0; i < age; i++) {
                main.objectRefs.hamster.ageTime = (float) AGE_IN_SECONDS;
                main.objectRefs.hamster.liveInformation();
            }
            for (long i = 0; i < time; i++){
                main.objectRefs.hamster.time = (float) INFORMATION_IN_SECONDS;
                main.objectRefs.hamster.liveInformation();
            }
        }
    }

    public void serialization(){
        main.objectRefs.hamster.endTime = System.currentTimeMillis();
        main.objectRefs.hamster.endTimeFlag = true;
        main.objectRefs.hamster.setX(main.objectRefs.hamster.standardX);
        Serialization.write(main.objectRefs.hamster);
    }

    public void exit(){
        main.dispose();
        Gdx.app.exit();
        System.exit(0);
    }

}