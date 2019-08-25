
package com.jfixby.imc.rps.ui.game;

import com.jfixby.r3.activity.api.ComponentsFactory;
import com.jfixby.r3.activity.api.RootLayer;
import com.jfixby.r3.activity.api.input.MouseExitEvent;
import com.jfixby.r3.activity.api.input.MouseMovedEvent;
import com.jfixby.r3.activity.api.input.MouseScrolledEvent;
import com.jfixby.r3.activity.api.input.TouchArea;
import com.jfixby.r3.activity.api.input.TouchAreaSpecs;
import com.jfixby.r3.activity.api.input.TouchDownEvent;
import com.jfixby.r3.activity.api.input.TouchDraggedEvent;
import com.jfixby.r3.activity.api.input.TouchUpEvent;
import com.jfixby.r3.activity.api.input.UserInputFactory;
import com.jfixby.r3.activity.api.layer.Layer;
import com.jfixby.r3.activity.api.raster.Raster;
import com.jfixby.r3.activity.api.user.MouseInputEventListener;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.projections.OffsetProjection;
import com.jfixby.scarabei.api.names.Names;

public class InputTest {
	private final MouseInputEventListener listener = new MouseInputEventListener() {

		@Override
		public boolean onMouseMoved (final MouseMovedEvent event) {
			InputTest.this.updateCursor(event.getCanvasPosition(), event.getLayerPosition());
			return super.onMouseMoved(event);

		}

		@Override
		public boolean onTouchDown (final TouchDownEvent event) {
			InputTest.this.updateCursor(event.getCanvasPosition(), event.getLayerPosition());
			return super.onTouchDown(event);
		}

		@Override
		public boolean onTouchUp (final TouchUpEvent event) {
			InputTest.this.updateCursor(event.getCanvasPosition(), event.getLayerPosition());
			return super.onTouchUp(event);
		}

		@Override
		public boolean onTouchDragged (final TouchDraggedEvent event) {
			InputTest.this.updateCursor(event.getCanvasPosition(), event.getLayerPosition());
			return super.onTouchDragged(event);
		}

		@Override
		public boolean onMouseExit (final MouseExitEvent event) {
			InputTest.this.updateCursor(event.getCanvasPosition(), event.getLayerPosition());
			return super.onMouseExit(event);
		}

		@Override
		public boolean onMouseScrolled (final MouseScrolledEvent event) {
			InputTest.this.updateCursor(event.getCanvasPosition(), event.getLayerPosition());
			return super.onMouseScrolled(event);
		}

	};
	private Raster canvasCursor;
	private Raster layerCursor;

	private void updateCursor (final ReadOnlyFloat2 canvasPosition, final ReadOnlyFloat2 layerPosition) {
		this.canvasCursor.setPosition(canvasPosition);
		this.layerCursor.setPosition(layerPosition);
	}

	public void deploy (final RootLayer root) {
		final ComponentsFactory components_factory = root.getComponentsFactory();
		final Layer experimental = components_factory.newLayer();

		this.canvasCursor = components_factory.getRasterDepartment().newRaster(Names.newID("123"));
		this.canvasCursor.setSize(10, 20);
		this.canvasCursor.setDebugRenderFlag(true);

		this.layerCursor = components_factory.getRasterDepartment().newRaster(Names.newID("123"));
		this.layerCursor.setSize(5, 10);
		this.layerCursor.setDebugRenderFlag(true);

		root.attachComponent(this.canvasCursor);

		final UserInputFactory uif = components_factory.getUserInputDepartment();
		final TouchAreaSpecs tas = uif.newTouchAreaSpecs();

		tas.setName("base");
		tas.setArea(0, 0, 1024 / 2, 768 / 2);

		final TouchArea ta = uif.newTouchArea(tas);
		ta.setInputListener(this.listener);

		experimental.attachComponent(ta);
		experimental.attachComponent(this.layerCursor);
		final OffsetProjection offset = Geometry.getProjectionFactory().newOffset(100, 50);
		experimental.setProjection(offset);
		root.attachComponent(experimental);
	}
}
