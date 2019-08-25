
package com.jfixby.imc.rps.ui.game.controls;

import com.jfixby.imc.rps.ui.game.GameScreen;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class PlayerControls {

	private Layer root;

	final UserControlRock btnRock = new UserControlRock(this);
	final UserControlPaper btnPaper = new UserControlPaper(this);
	final UserControlScissors btnScissors = new UserControlScissors(this);

	public PlayerControls (final GameScreen gameScreen) {
	}

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("controls");
		this.root = root.findComponent(path);
		this.root.print();
// Sys.exit();
		this.btnRock.deploy(this.root);
		this.btnPaper.deploy(this.root);
		this.btnScissors.deploy(this.root);
	}

	public void hide () {
		this.root.hide();
	}

}
