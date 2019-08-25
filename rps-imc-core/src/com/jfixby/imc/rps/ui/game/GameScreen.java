
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class GameScreen {
	private Layer root;
	private final RPSUnit master;

	public GameScreen (final RPSUnit rpsUnit) {
		this.master = rpsUnit;
	}

	public void deploy (final Layer root) {
		final RelativePath relativeGame = Utils.newRelativePath().child("rps").child("game");
		this.root = root.findComponent(relativeGame);
		this.root.print();
	}

	public void hide () {
		this.root.hide();
	}

	public void show () {
		this.root.show();
	}

}
