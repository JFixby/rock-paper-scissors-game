
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.input.Button;
import com.jfixby.r3.activity.api.input.OnClickListener;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class MenuScreen {

	private Layer root;
	private Button easyButton;
	private Button hardButton;
	private Button impossibleButton;
	private final RPSUnit master;

	public MenuScreen (final RPSUnit rpsUnit) {
		this.master = rpsUnit;
	}

	public void deploy (final Layer root) {
// root.print();
		final RelativePath relativeMenu = Utils.newRelativePath().child("rps").child("menu");
		this.root = root.findComponent(relativeMenu);

		{
			final RelativePath path = relativeMenu.child("easy").child("button");
			final Button button = root.findComponent(path);
			button.setOnClickListener(this.easyButtonListener);
			this.easyButton = button;
		}
		{
			final RelativePath path = relativeMenu.child("hard").child("button");
			final Button button = root.findComponent(path);
			button.setOnClickListener(this.hardButtonListener);
			this.hardButton = button;
		}
		{
			final RelativePath path = relativeMenu.child("impossible").child("button");
			final Button button = root.findComponent(path);
			button.setOnClickListener(this.impossibleButtonListener);
			this.impossibleButton = button;
		}
// this.hide();
	}

	private void goEasy () {
		this.master.goGame(GAME_DIFFICULTY.EASY);
	}

	private void goImpossible () {
		this.master.goGame(GAME_DIFFICULTY.IMPOSSIBLE);
	}

	private void goHard () {
		this.master.goGame(GAME_DIFFICULTY.HARD);
	}

	private final OnClickListener easyButtonListener = new OnClickListener() {
		@Override
		public void onClick () {
			MenuScreen.this.goEasy();
		}

	};
	private final OnClickListener hardButtonListener = new OnClickListener() {
		@Override
		public void onClick () {
			MenuScreen.this.goHard();
		}

	};
	private final OnClickListener impossibleButtonListener = new OnClickListener() {
		@Override
		public void onClick () {
			MenuScreen.this.goImpossible();
		}

	};

	public void show () {
		this.root.show();
	}

	public void hide () {
		this.root.hide();
	}
}
