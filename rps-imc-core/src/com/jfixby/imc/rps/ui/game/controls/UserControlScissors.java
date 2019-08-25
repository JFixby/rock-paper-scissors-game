
package com.jfixby.imc.rps.ui.game.controls;

import com.jfixby.imc.rps.engine.SPELL;
import com.jfixby.r3.activity.api.input.Button;
import com.jfixby.r3.activity.api.input.OnClickListener;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class UserControlScissors implements OnClickListener {

	private final PlayerControls master;

	public UserControlScissors (final PlayerControls playerControls) {
		this.master = playerControls;
	}

	private Button button;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("scissors").child("button");
		this.button = root.findComponent(path);
		this.button.setOnClickListener(this);
	}

	@Override
	public void onClick () {
		this.master.processSpell(SPELL.SCISSORS);
	}

}
