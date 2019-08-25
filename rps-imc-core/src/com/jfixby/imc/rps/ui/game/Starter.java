
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.ActivityManager;
import com.jfixby.r3.activity.api.DefaultUnit;
import com.jfixby.r3.activity.api.act.UIEventsManager;
import com.jfixby.scarabei.api.names.Names;

public class Starter extends DefaultUnit {

	@Override
	public void onCreate (final ActivityManager unitManager) {
		super.onCreate(unitManager);

		UIEventsManager.loadUnit(Names.newID("com.jfixby.imc.rps.ui.game.RPSUnit"));
		UIEventsManager.allowUserInput();
	}

}
