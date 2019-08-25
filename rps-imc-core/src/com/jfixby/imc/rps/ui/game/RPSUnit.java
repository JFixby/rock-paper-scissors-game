
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.Activity;
import com.jfixby.r3.activity.api.ActivityManager;
import com.jfixby.r3.activity.api.ComponentsFactory;
import com.jfixby.r3.activity.api.RootLayer;
import com.jfixby.r3.activity.api.act.ShadowStateListener;
import com.jfixby.r3.activity.api.act.UIEventsManager;
import com.jfixby.r3.activity.api.camera.Shadow;
import com.jfixby.r3.activity.api.camera.ShadowSpecs;
import com.jfixby.r3.activity.api.input.InputManager;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.r3.scene2d.api.Scene;
import com.jfixby.r3.scene2d.api.Scene2D;
import com.jfixby.r3.scene2d.api.Scene2DSpawningConfig;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public class RPSUnit implements Activity, InputManager, ShadowStateListener {

	private RootLayer root;
	private ComponentsFactory components_factory;

	final MenuScreen menuScreen = new MenuScreen(this);
	final GameScreen gameScreen = new GameScreen(this);
	private Layer scenelayer;

	@Override
	public void onCreate (final ActivityManager unitManager) {
		this.root = unitManager.getRootLayer();
		this.components_factory = this.root.getComponentsFactory();
		{
			this.root.closeInputValve();
			final ShadowSpecs shadow_specs = this.components_factory.getCameraDepartment().newShadowSpecs();
			this.shadow = this.components_factory.getCameraDepartment().newShadow(shadow_specs);

			this.shadow.setValue(Shadow.ABSOLUTE_CLEAR);
		}

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
		this.showMenu();

		this.root.attachComponent(this.shadow);

	}

	public void goMenu () {
		Err.throwNotImplementedYet();
	}

	public void showMenu () {
		this.gameScreen.hide();
		this.menuScreen.show();
	}

	public void showGame () {
		this.gameScreen.show();
		this.menuScreen.hide();
	}

	public void goGame (final GAME_DIFFICULTY diff) {
		UIEventsManager.pushFadeOut(500);
		UIEventsManager.pushAction(UIActions.goGame(diff));
		UIEventsManager.pushFadeIn(500);
		L.d("goGame", diff);
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

	@Override
	public void enableInput () {
		this.root.openInputValve();
	}

	private Shadow shadow;

	@Override
	public void beginShadowing (final float value_begin, final float value_end) {
		L.d("beginShadowing", value_begin + " -> " + value_end);
		this.shadow.setValue(value_begin);
	}

	@Override
	public void endShadowing (final float value_begin, final float value_end) {
		L.d("endShadowing", value_begin + " -> " + value_end);
		this.shadow.setValue(value_end);
	}

	@Override
	public void updateShadow (final float value_current) {
		L.d("updateShadow", value_current);
		this.shadow.setValue(value_current);
	}

}
