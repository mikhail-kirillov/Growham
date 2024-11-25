package gd.rf.theoneboringmancompany.growham.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import gd.rf.theoneboringmancompany.growham.Main;

public abstract class MyScreen implements Screen {
    protected final Main main;

    public MyScreen(final Main main) {this.main = main;}

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        main.stage.act(delta);

        main.stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {
        main.methods.serialization();
    }

    @Override
    public void resume() {
        main.beginTime = System.currentTimeMillis();
        main.methods.liveHamster();
    }

    @Override
    public void hide() {
        main.stage.clear();
    }

    @Override
    public void dispose() {
        main.saveHamster();
    }
}