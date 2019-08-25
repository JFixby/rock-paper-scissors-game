
package com.jfixby.imc.rps.ui.events;

import com.jfixby.imc.rps.engine.GAME_DIFFICULTY;
import com.jfixby.imc.rps.ui.game.RPSUnit;
import com.jfixby.r3.activity.api.act.UIAction;

public class GoGame implements UIAction<RPSUnit> {

	private final GAME_DIFFICULTY diff;

	public GoGame (final GAME_DIFFICULTY diff) {
		this.diff = diff;
	}

	@Override
	public void start (final RPSUnit ui) {
		ui.showGame(this.diff);
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return true;
	}

}
