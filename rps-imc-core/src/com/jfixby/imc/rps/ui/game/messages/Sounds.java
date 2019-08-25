
package com.jfixby.imc.rps.ui.game.messages;

import com.jfixby.r3.activity.api.audio.SoundEvent;
import com.jfixby.r3.activity.api.layer.Component;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.scarabei.api.collections.CollectionConverter;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.random.Random;

public class Sounds implements CollectionConverter<Component, SoundEvent> {

	List<SoundEvent> events = Collections.newList();

	public void deploy (final Layer soundsLayer) {

		Collections.convertCollection(soundsLayer.listChildren(), this.events, this);
		this.playEvent(-1);
	}

	public void playRandomEvent () {
		final int index = Random.newInt(0, this.events.size() - 1);
		this.playEvent(index);
	}

	private void playEvent (final int index) {
		for (int i = 0; i < this.events.size(); i++) {
			final SoundEvent e = this.events.getElementAt(i);
			if (i == index) {
				e.play();
			} else {
				e.mute();
			}
		}
	}

	@Override
	public SoundEvent convert (final Component input) {
		return (SoundEvent)input;
	}

}
