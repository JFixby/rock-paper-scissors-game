
package com.jfixby.imc.rps.engine;

import com.jfixby.scarabei.api.err.Err;

public enum SPELL {

	ROCK(consts.rock, consts.paper, consts.scissors), //
	PAPER(consts.paper, consts.scissors, consts.rock), //
	SCISSORS(consts.scissors, consts.rock, consts.paper);//

	private String name;
	private String weakTo;
	private String strongTo;

	SPELL (final String name, final String weakTo, final String strongTo) {
		this.name = name;
		this.weakTo = weakTo;
		this.strongTo = strongTo;

	}

	public static SPELL_RESULT compare (final SPELL one, final SPELL other) {
		if (one == other) {
			return SPELL_RESULT.DRAW;
		}
		if (one.weakTo.equals(other.name)) {
			return SPELL_RESULT.FAILS;
		}
		if (one.strongTo.equals(other.name)) {
			return SPELL_RESULT.BEATS;
		}
		Err.reportError("Time paradox detected.");
		return null;
	}

}

class consts {
	public static final String rock = "ROCK";
	public static final String paper = "PAPER";
	public static final String scissors = "SCISSORS";
}
