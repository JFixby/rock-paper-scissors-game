
package com.jfixby.imc.rps.ui.game.messages;

import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class DrawMessage {

	private Layer root;

	public DrawMessage (final GameMessages gameMessages) {
	}

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("victory");
		this.root = root.findComponent(path);
	}

}
