package gd.rf.theoneboringmancompany.growham.actors.scores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import gd.rf.theoneboringmancompany.growham.Main;

public class ScoresTable extends Actor {
    private final Main main;

    private float oneY;
    private float Space;

    private Label firstName;
    private Label secondName;
    private Label thirdName;

    private Label firstRecord;
    private Label secondRecord;
    private Label thirdRecord;

    private float oneR;
    private float oneN;

    private Object[] o;

    public ScoresTable(Main main) {
        this.main = main;

        oneY = main.stage.getHeight()/2 + main.stage.getHeight()/28;
        Space = main.stage.getHeight()/6;

        oneR = oneY;
        oneN = oneY;

        getObjectArray();

        firstName = getName(1);
        secondName = getName(2);
        thirdName = getName(3);

        firstRecord = getRecord(1);
        secondRecord = getRecord(2);
        thirdRecord = getRecord(3);
    }

    private Label getName(int num){
        Label label;

        float X = main.stage.getWidth()/7;

        String result;
        switch (num){
            case 1:
                result = "1. " + o[0];
                break;
            case 2:
                result = "2. " + o[2];
                break;
            case 3:
                result = "3. " + o[4];
                break;
            default:
                result = "";
                break;
        }

        label = new Label(result, new Label.LabelStyle(main.font, Color.WHITE));

        if (num > 1) oneN -= Space + (label.getHeight()/3 * num);

        label.setPosition(X, oneN);

        return label;
    }

    private Label getRecord(int num){
        Label label;

        float X = main.stage.getHeight()/2 + main.stage.getHeight()/2;

        String result;
        switch (num){
            case 1:
                result = o[1] + "";
                break;
            case 2:
                result = o[3] + "";
                break;
            case 3:
                result = o[5] + "";
                break;
            default:
                result = "";
                break;
        }

        label = new Label(result, new Label.LabelStyle(main.font, Color.WHITE));

        if (num > 1) oneR -= Space + (label.getHeight()/3 * num);

        label.setPosition(X, oneR);

        return label;
    }

    private void getObjectArray(){
        o = main.database.selectToScore();
        for (int i = 0; i < o.length; i++){
            if (o[i] == null) o[i] = "";
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        firstName.draw(batch, parentAlpha);
        firstRecord.draw(batch, parentAlpha);

        secondName.draw(batch, parentAlpha);
        secondRecord.draw(batch, parentAlpha);

        thirdName.draw(batch, parentAlpha);
        thirdRecord.draw(batch, parentAlpha);
    }
}