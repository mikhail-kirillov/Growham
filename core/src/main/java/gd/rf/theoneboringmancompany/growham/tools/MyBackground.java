package gd.rf.theoneboringmancompany.growham.tools;

import gd.rf.theoneboringmancompany.growham.Main;

public abstract class MyBackground extends MyPicture {
    public MyBackground(Main main, String pathToPicture) {
        super(main, pathToPicture);
        setPosition(0, 0);
    }
}