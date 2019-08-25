
package com.jfixby.imc.rps.ui.game;

import com.jfixby.imc.rps.ui.game.actions.ComputerActions;
import com.jfixby.imc.rps.ui.game.actions.PlayerActions;
import com.jfixby.imc.rps.ui.game.controls.PlayerControls;
import com.jfixby.imc.rps.ui.game.messages.GameMessages;
import com.jfixby.r3.activity.api.act.UIEventsManager;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class GameScreen {
	private Layer root;
	private final RPSUnit master;

	final GameMessages gameMessages = new GameMessages(this);
	final PlayerActions playerActions = new PlayerActions(this);
	final ComputerActions computerActions = new ComputerActions(this);
	final PlayerControls playerControls = new PlayerControls(this);

	public GameScreen (final RPSUnit rpsUnit) {
		this.master = rpsUnit;
	}

	public void deploy (final Layer root) {
		final RelativePath relativeGame = Utils.newRelativePath().child("rps").child("game");
		this.root = root.findComponent(relativeGame);
		this.gameMessages.deploy(this.root);
		this.playerActions.deploy(this.root);
		this.computerActions.deploy(this.root);
		this.playerControls.deploy(this.root);

		this.reset();
	}

	void reset () {

		this.gameMessages.hideAll();
		this.playerActions.hideAll();
		this.computerActions.hideAll();

		this.playerControls.hide();
	}

	public void hide () {
		this.root.hide();
	}

	public void show () {
		this.root.show();
	}

	public void onStartGame (final GAME_DIFFICULTY diff) {
		UIEventsManager.pushAction(UIActions.PlayFightIntro(diff));
		final long wait = 800L;
		UIEventsManager.pushWait(wait);
		UIEventsManager.pushAction(UIActions.ShowControls());
	}

	public void brifPlayer (final GAME_DIFFICULTY diff) {
		this.gameMessages.sayFight();
	}

	public void showUserControls () {
		this.gameMessages.hideAll();
		this.playerControls.show();
	}

	public void hideMessages () {
		this.gameMessages.hideAll();
	}

}
