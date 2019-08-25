
package com.jfixby.imc.rps.ui.events;

import com.jfixby.imc.rps.engine.PlayActionResult;
import com.jfixby.imc.rps.ui.game.RPSUnit;
import com.jfixby.r3.activity.api.act.UIAction;
import com.jfixby.r3.activity.api.animation.Animation;

public class ShowComputerResponse implements UIAction<RPSUnit> {

	private final PlayActionResult response;
	private Animation anim;

	public ShowComputerResponse (final PlayActionResult response) {
		this.response = response;
	}

	@Override
	public void start (final RPSUnit ui) {
		this.anim = ui.showComputerResponse(this.response);
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return this.anim.loopsComplete() >= 1f;
	}

}
