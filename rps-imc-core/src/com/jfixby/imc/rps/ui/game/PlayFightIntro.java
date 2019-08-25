
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.act.UIAction;

public class PlayFightIntro implements UIAction<RPSUnit> {

	private final GAME_DIFFICULTY diff;

	public PlayFightIntro (final GAME_DIFFICULTY diff) {
		this.diff = diff;
	}

	@Override
	public void start (final RPSUnit ui) {
		ui.brifPlayer(this, this.diff);
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return true;
	}

}
