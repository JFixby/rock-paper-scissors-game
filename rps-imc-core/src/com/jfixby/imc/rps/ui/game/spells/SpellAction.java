
package com.jfixby.imc.rps.ui.game.spells;

import com.jfixby.imc.rps.ui.game.messages.Sounds;
import com.jfixby.r3.activity.api.animation.LayersAnimation;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class SpellAction {

	private final String name;
	private final Sounds sounds = new Sounds();

	public SpellAction (final String name) {
		this.name = name;
	}

	private LayersAnimation animation;
	private Layer root;

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child(this.name);
		this.root = root.findComponent(path);
		this.animation = this.root.findComponent("anim");
		this.animation.stopAnimation();

		final Layer soundsLayer = this.root.findComponent("sounds");
		this.sounds.deploy(soundsLayer);
	}

	public void hide () {
		this.root.hide();
		this.animation.stopAnimation();
	}

	public LayersAnimation play () {
		this.root.show();
		this.animation.startAnimation();
		this.sounds.playRandomEvent();
		return this.animation;
	}

}
