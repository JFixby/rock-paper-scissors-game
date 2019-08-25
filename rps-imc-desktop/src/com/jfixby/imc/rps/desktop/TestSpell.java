
package com.jfixby.imc.rps.desktop;

import com.jfixby.imc.rps.engine.SPELL;
import com.jfixby.imc.rps.engine.SPELL_RESULT;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class TestSpell {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final SPELL one = SPELL.values()[i];
				final SPELL other = SPELL.values()[j];
				final SPELL_RESULT compare = SPELL.compare(one, other);
				L.d(one + " " + compare.resultString + " " + other);
			}
		}

	}

}
