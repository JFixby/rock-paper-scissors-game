
package com.jfixby.imc.rps.ui.game;

import com.jfixby.imc.rps.engine.SPELL;
import com.jfixby.r3.activity.api.Activity;
import com.jfixby.r3.activity.api.ActivityManager;
import com.jfixby.r3.activity.api.ComponentsFactory;
import com.jfixby.r3.activity.api.act.ShadowStateListener;
import com.jfixby.r3.activity.api.act.UIEventsManager;
import com.jfixby.r3.activity.api.animation.Animation;
import com.jfixby.r3.activity.api.camera.Shadow;
import com.jfixby.r3.activity.api.camera.ShadowSpecs;
import com.jfixby.r3.activity.api.input.InputManager;
import com.jfixby.r3.activity.api.input.KeyDownEvent;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.r3.activity.api.user.KeyboardInputEventListener;
import com.jfixby.r3.scene2d.api.Scene;
import com.jfixby.r3.scene2d.api.Scene2D;
import com.jfixby.r3.scene2d.api.Scene2DSpawningConfig;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.debug.StateSwitcher;
import com.jfixby.scarabei.api.input.Key;
import com.jfixby.scarabei.api.input.UserInput;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.sys.Sys;

public class RPSUnit implements Activity, InputManager, ShadowStateListener {

	private ComponentsFactory components_factory;

	final MenuScreen menuScreen = new MenuScreen(this);
	final GameScreen gameScreen = new GameScreen(this);
	private Layer scenelayer;
	private StateSwitcher<GAME_STATE> state;

	private Layer root;

	@Override
	public void onCreate (final ActivityManager unitManager) {
		this.root = unitManager.getRootLayer();
		this.components_factory = this.root.getComponentsFactory();

		final Layer content = this.components_factory.newLayer();
		{
			this.root.closeInputValve();
			final ShadowSpecs shadow_specs = this.components_factory.getCameraDepartment().newShadowSpecs();
			this.shadow = this.components_factory.getCameraDepartment().newShadow(shadow_specs);

			this.shadow.setValue(Shadow.ABSOLUTE_CLEAR);
		}

		this.state = Debug.newStateSwitcher(GAME_STATE.NEW);

		this.createContent(content);

		this.root.attachComponent(content);
		this.root.attachComponent(this.shadow);
		this.showMenu();

	}

	private void createContent (final Layer content) {
		final Scene2DSpawningConfig config = new Scene2DSpawningConfig();
		final ID scene_id = Names.newID("com.jfixby.imc.rps.ui.game.ui.psd");
// final ID scene_id = Names.newID("com.jfixby.imc.rps.ui.game.input.test.psd");
		config.structureID = scene_id;

		final Scene scene = Scene2D.newScene(this.components_factory, config);
		scene.startAllAnimations();
		this.scenelayer = scene.getRoot();

		content.attachComponent(this.scenelayer);

		this.menuScreen.deploy(this.scenelayer);
		this.gameScreen.deploy(this.scenelayer);

		content.attachComponent(this.keyboardListener);
	}

	private void goMenu () {
		UIEventsManager.pushFadeOut(this.shadowTime);
		UIEventsManager.pushAction(UIActions.goMenu());
		UIEventsManager.pushFadeIn(this.shadowTime);
	}

	public void showMenu () {
		this.gameScreen.hide();
		this.gameScreen.reset();
		this.menuScreen.show();
		this.menuScreen.reset();
		this.state.switchState(GAME_STATE.MENU);
	}

	public void showGame (final GAME_DIFFICULTY diff) {
		this.gameScreen.reset();
		this.menuScreen.reset();
		this.gameScreen.show();
		this.menuScreen.hide();
		this.state.switchState(GAME_STATE.GAME);
		this.gameScreen.onStartGame(diff);
	}

	public void goGame (final GAME_DIFFICULTY diff) {
		UIEventsManager.pushFadeOut(this.shadowTime);
		UIEventsManager.pushAction(UIActions.goGame(diff));
		UIEventsManager.pushFadeIn(this.shadowTime);
		L.d("goGame", diff);
	}

	long shadowTime = 500L;

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

	private final KeyboardInputEventListener keyboardListener = new KeyboardInputEventListener() {

		@Override
		public boolean onKeyDown (final KeyDownEvent event) {
			final Key key = event.getKey();
			if (key.is(UserInput.Keyboard().ESCAPE())) {
				if (RPSUnit.this.state.currentState() == GAME_STATE.MENU) {
					Sys.exit();
				}
				if (RPSUnit.this.state.currentState() == GAME_STATE.GAME) {
					RPSUnit.this.goMenu();
				}
			}
			return super.onKeyDown(event);
		}

	};

	public Animation brifPlayer (final PlayFightIntro playFightIntro, final GAME_DIFFICULTY diff) {
		return this.gameScreen.brifPlayer(diff);
	}

	public void showUserControls () {
		this.gameScreen.hideMessages();
		this.gameScreen.showUserControls();
	}

	public Animation playerSpellsAction (final SPELL spell) {
		this.gameScreen.hideUserControls();
		return this.gameScreen.playSpellAnimation(spell);
	}

}
