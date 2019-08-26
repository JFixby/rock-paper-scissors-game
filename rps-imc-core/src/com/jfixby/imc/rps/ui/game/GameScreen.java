
package com.jfixby.imc.rps.ui.game;

import com.jfixby.imc.rps.engine.GAME_DIFFICULTY;
import com.jfixby.imc.rps.engine.PlayActionResult;
import com.jfixby.imc.rps.engine.RPSEngine;
import com.jfixby.imc.rps.engine.SPELL;
import com.jfixby.imc.rps.ui.game.controls.PlayerControls;
import com.jfixby.imc.rps.ui.game.messages.GameMessages;
import com.jfixby.imc.rps.ui.game.spells.ComputerActions;
import com.jfixby.imc.rps.ui.game.spells.PlayerActions;
import com.jfixby.r3.activity.api.act.UIEventsManager;
import com.jfixby.r3.activity.api.animation.Animation;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.err.Err;
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

	public Animation brifPlayer (final GAME_DIFFICULTY diff) {
		RPSEngine.engine.restartGame(diff);
		return this.gameMessages.sayFight();

	}

	public void showUserControls () {
		this.gameMessages.hideAll();
		this.playerControls.show();
	}

	public void hideMessages () {
		this.gameMessages.hideAll();
	}

	public void hideUserControls () {
		this.playerControls.hide();
	}

	public Animation playSpellAnimation (final SPELL spell) {
		return this.playerActions.playSpell(spell);
	}

	public Animation showComputerResponse (final PlayActionResult response) {
		this.playerActions.hideAll();
		return this.computerActions.playSpell(response.computerReponse);
	}

	public void showRoundResult (final PlayActionResult result) {
		this.computerActions.hideAll();
		this.playerActions.hideAll();
		this.gameMessages.hideAll();

		if (result.isPlayerVictory()) {
			this.gameMessages.sayVictory();
			return;
		}
		if (result.isComputerVictory()) {
			this.gameMessages.sayFail();
			return;
		}
		if (result.isDraw()) {
			this.gameMessages.sayDraw();
			return;
		}
		Err.reportError("Incorrect state!");
		this.gameMessages.sayFight();
	}

}
