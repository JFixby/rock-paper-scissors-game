
package com.jfixby.imc.rps.ui.game;

import com.jfixby.imc.rps.engine.GAME_DIFFICULTY;
import com.jfixby.imc.rps.engine.PlayActionResult;
import com.jfixby.imc.rps.engine.SPELL;
import com.jfixby.imc.rps.ui.events.GoGame;
import com.jfixby.imc.rps.ui.events.GoMenu;
import com.jfixby.imc.rps.ui.events.PlayFightIntro;
import com.jfixby.imc.rps.ui.events.ShowComputerResponse;
import com.jfixby.imc.rps.ui.events.ShowControls;
import com.jfixby.imc.rps.ui.events.SpellAction;
import com.jfixby.r3.activity.api.act.UIAction;

public class UIActions {

	public static UIAction<RPSUnit> goMenu () {
		return new GoMenu();
	}

	public static UIAction<RPSUnit> goGame (final GAME_DIFFICULTY diff) {
		return new GoGame(diff);
	}

	public static UIAction<RPSUnit> PlayFightIntro (final GAME_DIFFICULTY diff) {
		return new PlayFightIntro(diff);
	}

	public static UIAction<RPSUnit> ShowControls () {
		return new ShowControls();
	}

	public static UIAction<RPSUnit> SpellAction (final SPELL spell) {
		return new SpellAction(spell);
	}

	public static UIAction<RPSUnit> ShowComputerResponse (final PlayActionResult response) {
		return new ShowComputerResponse(response);
	}

	public static UIAction<RPSUnit> ShowRoundResultMessage (final PlayActionResult result) {
		return new ShowRoundResultMessage(result);
	}

}
