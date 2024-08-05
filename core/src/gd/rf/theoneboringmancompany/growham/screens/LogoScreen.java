package gd.rf.theoneboringmancompany.growham.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.logo.Logotype;
import gd.rf.theoneboringmancompany.growham.actors.logo.Text;
import gd.rf.theoneboringmancompany.growham.actors.menu.BreakAndPlayButton;
import gd.rf.theoneboringmancompany.growham.actors.menu.PlayButton;
import gd.rf.theoneboringmancompany.growham.actors.menu.ScoresButton;
import gd.rf.theoneboringmancompany.growham.tools.MyAssetsManagers;
import gd.rf.theoneboringmancompany.growham.tools.MyScreen;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class LogoScreen extends MyScreen {
    public static final int SCREEN_NUMBER = 0;

    private Logotype logo;

    private boolean addTextFlag = true;

    public LogoScreen(Main main) {
        super(main);
    }

    @Override
    public void show() {
        main.font.getData().setScale(Settings.FONT_SCALE_PLAY_INFORMATION);

        main.beginTime = System.currentTimeMillis();

        logo = new Logotype();
        main.stage.addActor(logo);

        main.methods.recoveryHamster();
        main.methods.liveHamster();

        MyAssetsManagers.textureAtlasManager.load(Settings.Path.TextureAtlas.PLAY_BUTTON_ATLAS,
                TextureAtlas.class);
        MyAssetsManagers.textureAtlasManager.load(Settings.Path.TextureAtlas.BREAK_AND_PLAY_BUTTON_ATLAS,
                TextureAtlas.class);
        MyAssetsManagers.textureAtlasManager.load(Settings.Path.TextureAtlas.SCORES_BUTTON_ATLAS,
                TextureAtlas.class);

        MyAssetsManagers.musicManager.load(Settings.Path.Audio.Music.MENU, Music.class);
        MyAssetsManagers.musicManager.load(Settings.Path.Audio.Music.PLAY, Music.class);
        MyAssetsManagers.musicManager.load(Settings.Path.Audio.Music.SCORES, Music.class);

        MyAssetsManagers.soundManager.load(Settings.Path.Audio.Sounds.BUTTON, Sound.class);
        MyAssetsManagers.soundManager.load(Settings.Path.Audio.Sounds.HEALTH, Sound.class);
        MyAssetsManagers.soundManager.load(Settings.Path.Audio.Sounds.LEVEL_UP, Sound.class);
        MyAssetsManagers.soundManager.load(Settings.Path.Audio.Sounds.PAY, Sound.class);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (MyAssetsManagers.textureAtlasManager.update()
                && MyAssetsManagers.soundManager.update()
                && MyAssetsManagers.musicManager.update()
                && addTextFlag){

            main.objectRefs.play = new PlayButton(main);
            main.objectRefs.scores = new ScoresButton(main);
            main.objectRefs.breakAndPlay = new BreakAndPlayButton(main);

            main.stage.addActor(new Text(main));

            addTextFlag = false;
        }

        main.methods.inputBack(LogoScreen.SCREEN_NUMBER);

    }

    @Override
    public void dispose() {
        super.dispose();
        logo.dispose();
    }
}