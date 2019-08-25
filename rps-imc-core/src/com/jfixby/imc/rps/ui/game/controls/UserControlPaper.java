
package com.jfixby.imc.rps.ui.game.controls;

import com.jfixby.r3.activity.api.input.Button;
import com.jfixby.r3.activity.api.input.OnClickListener;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class UserControlPaper implements OnClickListener {

	public UserControlPaper (final PlayerControls playerControls) {
	}

	private Button button;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("paper").child("button");
		this.button = root.findComponent(path);
		this.button.setOnClickListener(this);
	}

}
