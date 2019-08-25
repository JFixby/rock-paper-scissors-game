
package com.jfixby.imc.rps.ui.events;

import com.jfixby.imc.rps.ui.game.RPSUnit;
import com.jfixby.r3.activity.api.act.UIAction;

public class ShowControls implements UIAction<RPSUnit> {

	@Override
	public void start (final RPSUnit ui) {
		ui.showUserControls();
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return true;
	}

}
