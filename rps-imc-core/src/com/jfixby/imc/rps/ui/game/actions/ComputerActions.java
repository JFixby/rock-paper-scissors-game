
package com.jfixby.imc.rps.ui.game.actions;

import com.jfixby.imc.rps.ui.game.GameScreen;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class ComputerActions {

	private Layer root;
	final ComputerActionRock actionRock = new ComputerActionRock(this);
	final ComputerActionScissors actionScissors = new ComputerActionScissors(this);
	final ComputerActionPaper actionPaper = new ComputerActionPaper(this);

	public ComputerActions (final GameScreen gameScreen) {
	}

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("computer_actions");
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
