
package com.jfixby.imc.rps.ui.game.messages;

import com.jfixby.r3.activity.api.animation.Animation;
import com.jfixby.r3.activity.api.animation.LayersAnimation;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class FailMessage {

	public FailMessage (final GameMessages gameMessages) {
	}

	private Layer root;
	private LayersAnimation animation;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("fail");
		this.root = root.findComponent(path);
		this.animation = this.root.findComponent("animation");
		this.animation.stopAnimation();
	}

	public void hide () {
		this.root.hide();
		this.animation.stopAnimation();
	}

	public Animation show () {
		this.root.show();
		this.animation.startAnimation();
		return this.animation;
	}
}
