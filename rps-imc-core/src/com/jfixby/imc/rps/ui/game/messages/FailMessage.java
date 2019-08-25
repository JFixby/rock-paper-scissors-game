
package com.jfixby.imc.rps.ui.game.messages;

import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class FailMessage {

	public FailMessage (final GameMessages gameMessages) {
	}

	private Layer root;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("fail");
		this.root = root.findComponent(path);
	}

	public void hide () {
		this.root.hide();
	}
}
