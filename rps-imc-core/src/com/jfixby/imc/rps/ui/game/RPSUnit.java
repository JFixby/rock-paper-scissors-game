
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.Activity;
import com.jfixby.r3.activity.api.ActivityManager;
import com.jfixby.r3.activity.api.ComponentsFactory;
import com.jfixby.r3.activity.api.RootLayer;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.r3.scene2d.api.Scene;
import com.jfixby.r3.scene2d.api.Scene2D;
import com.jfixby.r3.scene2d.api.Scene2DSpawningConfig;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public class RPSUnit implements Activity {

	private RootLayer root;
	private ComponentsFactory components_factory;

	final MenuScreen menuScreen = new MenuScreen(this);
	final GameScreen gameScreen = new GameScreen(this);
	private Layer scenelayer;

	@Override
	public void onCreate (final ActivityManager unitManager) {
		this.root = unitManager.getRootLayer();
		this.components_factory = this.root.getComponentsFactory();

		final Scene2DSpawningConfig config = new Scene2DSpawningConfig();
		final ID scene_id = Names.newID("com.jfixby.imc.rps.ui.game.ui.psd");
// final ID scene_id = Names.newID("com.jfixby.imc.rps.ui.game.input.test.psd");
		config.structureID = scene_id;

		final Scene scene = Scene2D.newScene(this.components_factory, config);
		scene.startAllAnimations();
		this.scenelayer = scene.getRoot();

		this.root.attachComponent(this.scenelayer);

// final ID assetID = FOKKER_SYSTEM_ASSETS.SOUND_TEST_MP3;
// final ID soundid = Names.newID("com.jfixby.fokker.assets.sound.test.mp3");
// final SoundEvent event = this.components_factory.getSoundFactory().newSoundEvent(soundid);

// final RelativePath relativeMenu = Utils.newRelativePath().child("rps").child("menu");
// this.root = this.root.findComponent(relativeMenu);

		this.menuScreen.deploy(this.scenelayer);
		this.gameScreen.deploy(this.scenelayer);

		this.goMenu();
	}

	private void goMenu () {
		this.gameScreen.hide();
		this.menuScreen.show();
	}

	public void goGame (final GAME_DIFFICULTY diff) {
		L.d("goGame", diff);
		this.gameScreen.show();
		this.menuScreen.hide();
	}

	@Override
	public void onStart () {
	}

	@Override
	public void onResume () {

	}

	@Override
	public void onPause () {
	}

	@Override
	public void onDestroy () {
	}

}
