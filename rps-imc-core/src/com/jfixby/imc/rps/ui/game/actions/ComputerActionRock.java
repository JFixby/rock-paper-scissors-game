
package com.jfixby.imc.rps.ui.game.actions;

import com.jfixby.r3.activity.api.animation.LayersAnimation;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class ComputerActionRock {

	public ComputerActionRock (final ComputerActions playerActions) {
	}

	private LayersAnimation animation;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("rock");
		this.animation = root.findComponent(path);
	}

	public void hide () {
		this.animation.stopAnimation();
		this.animation.hide();
	}

	public LayersAnimation play () {
		this.animation.show();
		this.animation.startAnimation();
		return this.animation;
	}
}
