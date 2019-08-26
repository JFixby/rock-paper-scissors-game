
package com.jfixby.imc.rps.ui.events;

import com.jfixby.imc.rps.engine.GAME_DIFFICULTY;
import com.jfixby.imc.rps.ui.game.RPSUnit;
import com.jfixby.r3.activity.api.act.UIAction;
import com.jfixby.r3.activity.api.audio.SoundEvent;

public class PlayFightIntro implements UIAction<RPSUnit> {

	private final GAME_DIFFICULTY diff;
	private SoundEvent event;

	public PlayFightIntro (final GAME_DIFFICULTY diff) {
		this.diff = diff;
	}

	@Override
	public void start (final RPSUnit ui) {
		this.event = ui.brifPlayer(this, this.diff);
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return true;
	}

}
