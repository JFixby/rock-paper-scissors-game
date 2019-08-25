
package com.jfixby.imc.rps.engine;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.random.Random;

public class RPSEngine {

	public static RPSEngine engine = new RPSEngine();
	private GAME_DIFFICULTY diff;
	private int round;

	public void restartGame (final GAME_DIFFICULTY diff) {
		this.diff = diff;
		this.round = 0;
	}

	public PlayActionResult userPlays (final SPELL spell) {
		Debug.checkNull("diff", this.diff);
		if (this.diff == GAME_DIFFICULTY.EASY) {
			return this.easyResponse(spell);
		}
		if (this.diff == GAME_DIFFICULTY.HARD) {
			return this.hardResponse(spell);
		}
		if (this.diff == GAME_DIFFICULTY.IMPOSSIBLE) {
			return this.impossibleHardResponse(spell);
		}
		Err.reportError("Bad state");
		return null;
	}

	private PlayActionResult impossibleHardResponse (final SPELL playerSpell) {
		final PlayActionResult result = new PlayActionResult();
		result.computerReponse = playerSpell.weakTo();
		result.playerSpell = playerSpell;
		result.result = SPELL.compare(playerSpell, result.computerReponse);
// L.d("impossibleHardResponse", result);
		return result;
	}

	private PlayActionResult hardResponse (final SPELL playerSpell) {
		final PlayActionResult result = new PlayActionResult();
		final boolean win = Random.newCoin();
// L.d("hardResponse", win);
		if (win) {
			result.computerReponse = playerSpell.strongTo();
		} else {
			result.computerReponse = playerSpell.weakTo();
		}
		result.playerSpell = playerSpell;
		result.result = SPELL.compare(playerSpell, result.computerReponse);
// L.d(" ", result);
		return result;
	}

	private PlayActionResult easyResponse (final SPELL playerSpell) {
		final PlayActionResult result = new PlayActionResult();
		final double rand = Random.newDouble(0d, 3d);
// L.d("easyResponse", rand);
		if (rand >= 1d) {
			result.computerReponse = playerSpell.strongTo();
		} else {
			result.computerReponse = playerSpell.weakTo();
		}
		result.playerSpell = playerSpell;
		result.result = SPELL.compare(playerSpell, result.computerReponse);
// L.d(" ", result);
		return result;
	}

}
