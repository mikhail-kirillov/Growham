package gd.rf.theoneboringmancompany.growham.screens;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.actors.Back;
import gd.rf.theoneboringmancompany.growham.actors.Background;
import gd.rf.theoneboringmancompany.growham.actors.market.Food;
import gd.rf.theoneboringmancompany.growham.actors.market.Med;
import gd.rf.theoneboringmancompany.growham.actors.market.Upgrade;
import gd.rf.theoneboringmancompany.growham.tools.MyScreen;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class MarketScreen extends MyScreen {
    public static final int SCREEN_NUMBER = 5;

    private Background background;
    private Back back;
    private Med med;
    private Food food;

    public MarketScreen(Main main) {
        super(main);
    }

    @Override
    public void show() {
        main.font.getData().setScale(Settings.FONT_SCALE_ORDINARY);

        background = new Background(main);
        back = new Back(main, PlayScreen.SCREEN_NUMBER);
        med = new Med(main);
        food = new Food(main);

        main.stage.addActor(background);
        main.stage.addActor(back);
        main.stage.addActor(med);
        main.stage.addActor(food);
        addUpgrade();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.methods.inputBack(PlayScreen.SCREEN_NUMBER);
    }

    private void addUpgrade(){
        if (!(main.objectRefs.hamster.getRoomLevel() > 1))
            main.stage.addActor(new Upgrade(main));
    }

    @Override
    public void dispose() {
        super.dispose();
        if (background != null)
            background.dispose();
        if (back != null)
            back.dispose();
        if (med != null)
            med.dispose();
        if (food != null)
            food.dispose();
    }
}