package com.zeebo.velociraptor.binding.view;

import javax.swing.JLabel;

import com.zeebo.velociraptor.model.Model;

class LabelBinding extends ViewBinding<String>
{
	private final JLabel	componentReference;

	LabelBinding(Model model, String paramName, JLabel component)
	{
		super(model, paramName, component);

		componentReference = component;
	}

	@Override
	final void update(String data)
	{
		componentReference.setText(data);
	}

	@Override
	final String getData()
	{
		return componentReference.getText();
	}
}
