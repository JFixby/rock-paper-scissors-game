
package com.jfixby.imc.rps.engine;

public class PlayActionResult {

	public SPELL computerReponse;
	public SPELL playerSpell;
	public SPELL_RESULT result;

	public boolean isPlayerVictory () {
		return false;
	}

	public boolean isComputerVictory () {
		return false;
	}

	public boolean isDraw () {
		return false;
	}

}
