
package com.jfixby.imc.rps.engine;

public class PlayActionResult {

	public SPELL computerReponse;
	public SPELL playerSpell;
	public SPELL_RESULT result;

	public boolean isPlayerVictory () {
		return this.result == SPELL_RESULT.BEATS;
	}

	public boolean isComputerVictory () {
		return this.result == SPELL_RESULT.FAILS;
	}

	public boolean isDraw () {
		return this.result == SPELL_RESULT.DRAW;
	}

	@Override
	public String toString () {
		return "PlayActionResult [playerSpell=" + this.playerSpell + ", computerReponse=" + this.computerReponse + ", result="
			+ this.result + "]";
	}

}
