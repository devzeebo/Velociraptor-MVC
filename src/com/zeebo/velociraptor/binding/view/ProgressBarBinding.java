package com.zeebo.velociraptor.binding.view;

import javax.swing.JProgressBar;

import com.zeebo.velociraptor.model.Model;

class ProgressBarBinding extends ViewBinding<Integer>
{
	private final JProgressBar	componentReference;

	ProgressBarBinding(Model model, String paramName, JProgressBar component)
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
