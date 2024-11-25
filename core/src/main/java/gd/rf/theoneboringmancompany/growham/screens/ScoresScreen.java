package gd.rf.theoneboringmancompany.growham.screens;

import com.badlogic.gdx.audio.Music;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.Back;
import gd.rf.theoneboringmancompany.growham.actors.Background;
import gd.rf.theoneboringmancompany.growham.actors.scores.ScoresTable;
import gd.rf.theoneboringmancompany.growham.actors.scores.Time;
import gd.rf.theoneboringmancompany.growham.actors.scores.Who;
import gd.rf.theoneboringmancompany.growham.tools.MyAssetsManagers;
import gd.rf.theoneboringmancompany.growham.tools.MyScreen;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class ScoresScreen extends MyScreen {
    public static final int SCREEN_NUMBER = 2;

    private Music music;
    private Background background;
    private Who who;
    private Time time;
    private Back back;

    public ScoresScreen(Main main) {
        super(main);
    }

    @Override
    public void show() {
        main.font.getData().setScale(Settings.FONT_SCALE_ORDINARY);

        music = MyAssetsManagers.musicManager.get(Settings.Path.Audio.Music.SCORES, Music.class);
        music.setLooping(true);
        music.setVolume(Settings.MusicAndSound.MUSIC_VOLUME);
        music.play();

        background = new Background(main);
        ScoresTable table = new ScoresTable(main);
        who = new Who(main);
        time = new Time(main);
        back = new Back(main, MenuScreen.SCREEN_NUMBER);

        main.stage.addActor(background);
        main.stage.addActor(table);
        main.stage.addActor(who);
        main.stage.addActor(time);
        main.stage.addActor(back);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.methods.inputBack(MenuScreen.SCREEN_NUMBER);
    }

    @Override
    public void hide() {
        music.stop();
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (music != null)
            music.dispose();
        if (background != null)
            background.dispose();
        if (back != null)
            back.dispose();
        if (time != null)
            time.dispose();
        if (who != null)
            who.dispose();
    }
}