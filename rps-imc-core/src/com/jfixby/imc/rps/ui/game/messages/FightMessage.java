
package com.jfixby.imc.rps.ui.game.messages;

import com.jfixby.r3.activity.api.animation.LayersAnimation;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class FightMessage {

	public FightMessage (final GameMessages gameMessages) {
	}

	private Layer root;
	private LayersAnimation animation;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("fight");
		this.root = root.findComponent(path);
		this.animation = this.root.findComponent("animation");
		this.animation.stopAnimation();
	}

	public void hide () {
		this.root.hide();
		this.animation.stopAnimation();
	}

	public void show () {
		this.root.show();
		this.animation.startAnimation();
	}
}
