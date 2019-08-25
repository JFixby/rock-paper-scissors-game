
package com.jfixby.imc.rps.ui.game;

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

}
