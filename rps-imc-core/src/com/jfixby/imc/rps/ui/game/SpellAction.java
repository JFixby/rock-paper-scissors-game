
package com.jfixby.imc.rps.ui.game;

import com.jfixby.imc.rps.engine.SPELL;
import com.jfixby.r3.activity.api.act.UIAction;
import com.jfixby.r3.activity.api.animation.Animation;

public class SpellAction implements UIAction<RPSUnit> {

	private final SPELL spell;
	private Animation anim;

	public SpellAction (final SPELL spell) {
		this.spell = spell;
	}

	@Override
	public void start (final RPSUnit ui) {
		this.anim = ui.playerSpellsAction(this.spell);
	}

	@Override
	public void push (final RPSUnit ui) {
	}

	@Override
	public boolean isDone (final RPSUnit ui) {
		return this.anim.loopsComplete() > 0;
	}

}
