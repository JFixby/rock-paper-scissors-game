
package com.jfixby.imc.rps.ui.game.actions;

import com.jfixby.imc.rps.ui.game.GameScreen;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class PlayerActions {

	private Layer root;
	final PlayerActionRock actionRock = new PlayerActionRock(this);
	final PlayerActionScissors actionScissors = new PlayerActionScissors(this);
	final PlayerActionPaper actionPaper = new PlayerActionPaper(this);

	public PlayerActions (final GameScreen gameScreen) {
	}

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("player_actions");
		this.root = root.findComponent(path);
		this.root.closeInputValve();

		this.actionRock.deploy(this.root);
		this.actionScissors.deploy(this.root);
		this.actionPaper.deploy(this.root);

	}

	public void hideAll () {
		this.actionRock.hide();
		this.actionScissors.hide();
		this.actionPaper.hide();
	}
}
