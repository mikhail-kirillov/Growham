package gd.rf.theoneboringmancompany.growham.tools;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import gd.rf.theoneboringmancompany.growham.Main;

public abstract class MyButton extends Button {
    protected final Main main;

    //Market screen's button
    private Label price;
    protected Texture imgButton;

    //Other simple button
    protected Texture img;
    protected Sound sound;

    protected String howMuch;

    protected float imgX;
    private float imgY;

    //Simple button
    public MyButton(final Main main, String pathToImageButton) {
        this.main = main;

        imgButton = new Texture(pathToImageButton);
        sound = MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.BUTTON, Sound.class);

        setWidth(imgButton.getWidth());
        setHeight(imgButton.getHeight());
    }

    //Market button
    public MyButton(final Main main, String pathToImageButton, String pathToImage, String price){
        this.main = main;

        this.howMuch = price;

        main.font.getData().setScale(Settings.FONT_SCALE_ORDINARY);

        imgButton = new Texture(pathToImageButton);
        img = new Texture(pathToImage);
        sound = MyAssetsManagers.soundManager.get(Settings.Path.Audio.Sounds.BUTTON, Sound.class);
        this.price = new Label(price + " $", new Label.LabelStyle(main.font, Color.WHITE));

        setWidth(imgButton.getWidth());
        setHeight(imgButton.getHeight());
    }

    public void dispose(){
        if (img != null)
            img.dispose();
        imgButton.dispose();
        sound.dispose();
    }

    protected void setX_and_Y(){
        float priceX = main.stage.getWidth()/2 - this.price.getWidth()/2;
        float priceY = getY();
        imgX = img.getWidth();
        imgY = getY();

        this.price.setPosition(priceX, priceY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imgButton, getX(), getY());
        if (img != null){
            batch.draw(img, imgX, imgY);
            price.draw(batch, parentAlpha);
        }
    }
}