package gd.rf.theoneboringmancompany.growham.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.tools.MyBackground;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class Background extends MyBackground {
    private Texture tube;

    public Background(Main main) {
        super(main, Settings.Path.Pictures.Backgrounds.ROOM);
        if (main.objectRefs.hamster.getRoomLevel() >= 2) {
            tube = new Texture(Settings.Path.Pictures.Images.TUBE);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (main.objectRefs.hamster.getRoomLevel() >= 2) {
            batch.draw(tube, main.stage.getWidth() / 2 + main.stage.getWidth() / 5,
                    main.stage.getHeight() / 2 - main.stage.getWidth() / 5.3f);
        }
    }

}