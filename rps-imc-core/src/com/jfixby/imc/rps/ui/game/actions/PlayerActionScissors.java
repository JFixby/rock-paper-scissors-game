
package com.jfixby.imc.rps.ui.game.actions;

import com.jfixby.r3.activity.api.animation.LayersAnimation;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class PlayerActionScissors {

	public PlayerActionScissors (final PlayerActions playerActions) {
	}

	private LayersAnimation animation;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("scissors");
		this.animation = root.findComponent(path);
	}

	public void hide () {
		this.animation.stopAnimation();
		this.animation.hide();
	}

}