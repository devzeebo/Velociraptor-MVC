package com.zeebo.velociraptor.view.binding;

import javax.swing.text.JTextComponent;

import com.zeebo.velociraptor.model.Model;

final class TextComponentBinding extends ViewBinding<String>
{
	private final JTextComponent	componentReference;

	TextComponentBinding(Model model, String paramName, JTextComponent component)
	{
		super(model, paramName, component);

		this.componentReference = component;
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
