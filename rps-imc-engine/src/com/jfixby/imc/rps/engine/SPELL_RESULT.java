
package com.jfixby.imc.rps.engine;

public enum SPELL_RESULT {

	FAILS(-1, "fails to"), BEATS(1, "beats"), DRAW(0, "=="),;

	public final int compareResult;
	public final String resultString;

	SPELL_RESULT (final int compareResult, final String resultString) {
		this.compareResult = compareResult;
		this.resultString = resultString;
	}

}
