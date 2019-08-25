
package com.jfixby.imc.rps.ui.game.actions;

import com.jfixby.imc.rps.ui.game.GameScreen;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class PlayerActions {

	private Layer root;
	final PlayerActionRock playerActionRock = new PlayerActionRock(this);
	final PlayerActionScissors playerActionScissors = new PlayerActionScissors(this);
	final PlayerActionPaper playerActionPaper = new PlayerActionPaper(this);

	public PlayerActions (final GameScreen gameScreen) {
	}

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("player_actions");
		this.root = root.findComponent(path);
		this.root.closeInputValve();

		this.playerActionRock.deploy(this.root);
		this.playerActionScissors.deploy(this.root);
		this.playerActionPaper.deploy(this.root);

	}
}
