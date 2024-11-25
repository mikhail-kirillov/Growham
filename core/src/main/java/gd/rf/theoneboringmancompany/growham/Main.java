package gd.rf.theoneboringmancompany.growham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import gd.rf.theoneboringmancompany.growham.screens.LogoScreen;
import gd.rf.theoneboringmancompany.growham.tools.AndroidConnector;
import gd.rf.theoneboringmancompany.growham.tools.Database;
import gd.rf.theoneboringmancompany.growham.tools.GeneralMethods;
import gd.rf.theoneboringmancompany.growham.tools.MyAssetsManagers;
import gd.rf.theoneboringmancompany.growham.tools.ObjectRefs;
import gd.rf.theoneboringmancompany.growham.tools.Settings;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.CAMERA_HEIGHT;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.CAMERA_WIDTH;
import static gd.rf.theoneboringmancompany.growham.tools.Settings.Hamster.STANDARD_POSE;

public class Main extends Game {
	public BitmapFont font;
	public Stage stage;
	public Database database;
	public ObjectRefs objectRefs;
	public GeneralMethods methods;
	public long beginTime;

	private FitViewport viewport;

	public Main(AndroidConnector connector) {
		super();
		database = new Database(connector);
	}

	@Override
	public void create() {
		font = new BitmapFont(Gdx.files.internal(Settings.Path.FONT));
		objectRefs = new ObjectRefs();
		methods = new GeneralMethods(this);

		//Create stage
		OrthographicCamera camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.position.set(CAMERA_WIDTH /2, CAMERA_HEIGHT /2, 0);
		viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);

		MyAssetsManagers.soundManager.load(Settings.Path.Audio.Sounds.OK, Sound.class);
		MyAssetsManagers.soundManager.finishLoading();

		setScreen(new LogoScreen(this));
	}

	public void saveHamster(){
		objectRefs.hamster.setPose(STANDARD_POSE);
		objectRefs.hamster.button = objectRefs.hamster.bAll;
		methods.serialization();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height, true);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		saveHamster();
		super.dispose();
		font.dispose();
		stage.dispose();
		objectRefs.dispose();
	}
}