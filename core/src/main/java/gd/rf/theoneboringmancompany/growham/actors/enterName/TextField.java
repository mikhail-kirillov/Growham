package gd.rf.theoneboringmancompany.growham.actors.enterName;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import gd.rf.theoneboringmancompany.growham.Main;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

public class TextField extends com.badlogic.gdx.scenes.scene2d.ui.TextField {
    private Label text;

    public TextField(final Main main){
        super("", new Skin(Gdx.files.internal(Settings.Path.UI_SKIN_TEXT_FIELD)));

        setHeight(main.font.getData().capHeight * 2);
        setWidth(main.stage.getWidth()/2.5f);

        setPosition(main.stage.getWidth()/2 - getWidth()/2,
                    main.stage.getHeight()/2 - getHeight()/2);

        text = new Label(Settings.Text.ENTER_NAME, new Label.LabelStyle(main.font, Color.WHITE));
        text.setPosition(main.stage.getWidth()/2-text.getWidth()/2,
                            getY() + getHeight()*2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        text.draw(batch, parentAlpha);
    }
}