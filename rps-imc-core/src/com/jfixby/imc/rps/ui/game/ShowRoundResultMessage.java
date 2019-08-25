
package com.jfixby.imc.rps.ui.game;

import com.jfixby.imc.rps.engine.PlayActionResult;
import com.jfixby.r3.activity.api.act.UIAction;

public class ShowRoundResultMessage implements UIAction<RPSUnit> {

	private final PlayActionResult result;

	public ShowRoundResultMessage (final PlayActionResult result) {
		this.result = result;
	}

	@Override
	public void start (final RPSUnit ui) {
		ui.ShowRoundResult(this.result);
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return true;
	}

}
