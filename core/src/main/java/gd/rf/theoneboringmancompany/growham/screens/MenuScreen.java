package gd.rf.theoneboringmancompany.growham.screens;

import com.badlogic.gdx.audio.Music;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.menu.Background;
import gd.rf.theoneboringmancompany.growham.actors.menu.Exit;
import gd.rf.theoneboringmancompany.growham.tools.MyAssetsManagers;
import gd.rf.theoneboringmancompany.growham.tools.MyScreen;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class MenuScreen extends MyScreen {
    public static final int SCREEN_NUMBER = 1;

    private Music music;
    private Background background;
    private Exit exit;

    public MenuScreen(Main main) {
        super(main);
    }

    @Override
    public void show() {
        main.font.getData().setScale(Settings.FONT_SCALE_ORDINARY);

        music = MyAssetsManagers.musicManager.get(Settings.Path.Audio.Music.MENU, Music.class);
        music.setLooping(true);
        music.setVolume(Settings.MusicAndSound.MUSIC_VOLUME);
        music.play();

        background = new Background(main);
        exit = new Exit(main);

        main.stage.addActor(background);
        main.stage.addActor(main.objectRefs.play);
        main.stage.addActor(main.objectRefs.scores);
        main.stage.addActor(main.objectRefs.breakAndPlay);
        main.stage.addActor(exit);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.methods.inputBack(LogoScreen.SCREEN_NUMBER);
    }

    @Override
    public void hide() {
        music.stop();
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
        background.dispose();
        exit.dispose();
    }
}