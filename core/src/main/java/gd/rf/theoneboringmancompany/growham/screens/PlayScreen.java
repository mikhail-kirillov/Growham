package gd.rf.theoneboringmancompany.growham.screens;

import com.badlogic.gdx.audio.Music;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.play.Background;
import gd.rf.theoneboringmancompany.growham.actors.play.Emotions;
import gd.rf.theoneboringmancompany.growham.actors.play.Gym;
import gd.rf.theoneboringmancompany.growham.actors.play.Information;
import gd.rf.theoneboringmancompany.growham.actors.play.Market;
import gd.rf.theoneboringmancompany.growham.actors.play.Sleep;
import gd.rf.theoneboringmancompany.growham.tools.MyAssetsManagers;
import gd.rf.theoneboringmancompany.growham.tools.MyScreen;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class PlayScreen extends MyScreen {
    public static final int SCREEN_NUMBER = 3;

    private Music music;
    private Background background;
    private Market market;
    private Emotions emotions;


    public PlayScreen(Main main) {
        super(main);
        main.objectRefs.sleep = new Sleep(main);
        main.objectRefs.gym = new Gym(main);
        main.objectRefs.marketScreen = new MarketScreen(main);
    }

    @Override
    public void show() {
        main.font.getData().setScale(Settings.FONT_SCALE_PLAY_INFORMATION);

        music = MyAssetsManagers.musicManager.get(Settings.Path.Audio.Music.PLAY, Music.class);
        music.setLooping(true);
        music.setVolume(Settings.MusicAndSound.MUSIC_VOLUME);
        music.play();

        background = new Background(main);
        Information information = new Information(main);
        market = new Market(main);
        emotions = new Emotions(main);

        main.stage.addActor(background);
        main.stage.addActor(information);
        main.stage.addActor(market);
        main.stage.addActor(main.objectRefs.hamster);
        main.stage.addActor(main.objectRefs.sleep);
        main.stage.addActor(main.objectRefs.gym);
        main.stage.addActor(emotions);

        if (!main.objectRefs.hamster.isHasName()){
            main.setScreen(new EnterNameScreen(main));
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.methods.inputBack(MenuScreen.SCREEN_NUMBER);
    }



    @Override
    public void hide() {
        super.hide();
        //Music starts anew every time
        music.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (background != null)
            background.dispose();
        if (market != null)
            market.dispose();
        if (emotions != null)
            emotions.dispose();
    }
}