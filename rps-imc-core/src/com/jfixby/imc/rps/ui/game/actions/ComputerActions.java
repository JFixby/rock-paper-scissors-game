
package com.jfixby.imc.rps.ui.game.actions;

import com.jfixby.imc.rps.ui.game.GameScreen;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class ComputerActions {

	private Layer root;
	final ComputerActionRock computerActionRock = new ComputerActionRock(this);
	final ComputerActionScissors computerActionScissors = new ComputerActionScissors(this);
	final ComputerActionPaper computerActionPaper = new ComputerActionPaper(this);

	public ComputerActions (final GameScreen gameScreen) {
	}

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("computer_actions");
		this.root = root.findComponent(path);
		this.root.closeInputValve();

		this.computerActionRock.deploy(this.root);
		this.computerActionScissors.deploy(this.root);
		this.computerActionPaper.deploy(this.root);

	}
}
