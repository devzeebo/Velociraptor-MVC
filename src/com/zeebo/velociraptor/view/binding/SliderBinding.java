package com.zeebo.velociraptor.view.binding;

import javax.swing.JSlider;

import com.zeebo.velociraptor.model.Model;

class SliderBinding extends ViewBinding<Integer>
{
	private final JSlider	componentReference;

	SliderBinding(Model model, String paramName, JSlider component)
	{
		super(model, paramName, component);

		componentReference = component;
	}

	@Override
	final void update(Integer data)
	{
		componentReference.setValue(data);
	}

	@Override
	final Integer getData()
	{
		return componentReference.getValue();
	}
}
