
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.act.UIAction;

public class GoMenu implements UIAction<RPSUnit> {

	@Override
	public void start (final RPSUnit ui) {
		ui.showMenu();
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return true;
	}

}
