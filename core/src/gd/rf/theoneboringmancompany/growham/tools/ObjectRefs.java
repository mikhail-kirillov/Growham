package gd.rf.theoneboringmancompany.growham.tools;

import gd.rf.theoneboringmancompany.growham.actors.menu.BreakAndPlayButton;
import gd.rf.theoneboringmancompany.growham.actors.menu.PlayButton;
import gd.rf.theoneboringmancompany.growham.actors.menu.ScoresButton;
import gd.rf.theoneboringmancompany.growham.actors.play.Gym;
import gd.rf.theoneboringmancompany.growham.actors.play.Hamster;
import gd.rf.theoneboringmancompany.growham.actors.play.Sleep;
import gd.rf.theoneboringmancompany.growham.screens.MarketScreen;
import gd.rf.theoneboringmancompany.growham.screens.MenuScreen;
import gd.rf.theoneboringmancompany.growham.screens.PlayScreen;
import gd.rf.theoneboringmancompany.growham.screens.ScoresScreen;

public class ObjectRefs {
    //Screens
    public MenuScreen menuScreen;
    public ScoresScreen scoresScreen;
    public PlayScreen playScreen;
    public MarketScreen marketScreen;

    //Animated menu buttons
    public PlayButton play;
    public ScoresButton scores;
    public BreakAndPlayButton breakAndPlay;

    //Actors
    public Hamster hamster;
    public Sleep sleep;
    public Gym gym;

    public void dispose(){
        if (menuScreen != null)
        menuScreen.dispose();
        if (scoresScreen != null)
        scoresScreen.dispose();
        if (playScreen != null)
        playScreen.dispose();
        if (marketScreen != null)
        marketScreen.dispose();

        if (play != null)
        play.dispose();
        if (scores != null)
        scores.dispose();
        if (breakAndPlay != null)
        breakAndPlay.dispose();

        if (hamster != null)
        hamster.dispose();
        if (sleep != null)
        sleep.dispose();
        if (gym != null)
        gym.dispose();
    }
}