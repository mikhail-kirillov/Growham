package gd.rf.theoneboringmancompany.growham.screens;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.Background;
import gd.rf.theoneboringmancompany.growham.actors.enterName.Ok;
import gd.rf.theoneboringmancompany.growham.actors.enterName.TextField;
import gd.rf.theoneboringmancompany.growham.tools.MyScreen;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class EnterNameScreen extends MyScreen {
    public static final int SCREEN_NUMBER = 4;

    private Background background;
    private Ok ok;
    public TextField textField;

    public EnterNameScreen(Main main) {
        super(main);
    }

    @Override
    public void show() {
        main.font.getData().setScale(Settings.FONT_SCALE_PLAY_INFORMATION);

        background = new Background(main);
        ok = new Ok(main, main.objectRefs.playScreen, this);
        textField = new TextField(main);

        main.stage.addActor(background);
        main.stage.addActor(ok);
        main.stage.addActor(textField);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.methods.inputBack(MenuScreen.SCREEN_NUMBER);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (background != null)
            background.dispose();
        if (ok != null)
            ok.dispose();
    }
}