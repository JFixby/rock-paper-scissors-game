
package com.jfixby.imc.rps.ui.game.messages;

import com.jfixby.imc.rps.ui.game.GameScreen;
import com.jfixby.r3.activity.api.animation.Animation;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class GameMessages {

	private Layer root;

	final VictoryMessage victoryMessage = new VictoryMessage(this);
	final DrawMessage drawMessage = new DrawMessage(this);
	final FailMessage failMessage = new FailMessage(this);
	final FightMessage fightMessage = new FightMessage(this);

	public GameMessages (final GameScreen gameScreen) {
	}

	public void deploy (final Layer root) {
		final RelativePath path = Utils.newRelativePath().child("messages");
		this.root = root.findComponent(path);
		this.root.closeInputValve();

		this.victoryMessage.deploy(this.root);
		this.drawMessage.deploy(this.root);
		this.failMessage.deploy(this.root);
		this.fightMessage.deploy(this.root);

	}

	public void hideAll () {
		this.victoryMessage.hide();
		this.failMessage.hide();
		this.drawMessage.hide();
		this.fightMessage.hide();
	}

	public Animation sayFight () {
		return this.fightMessage.show();
	}

}
