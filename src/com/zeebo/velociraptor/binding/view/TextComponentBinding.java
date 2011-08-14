package com.zeebo.velociraptor.binding.view;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import com.zeebo.velociraptor.annotation.BindablePolicy;
import com.zeebo.velociraptor.model.Model;

/**
 * Implementation of {@link ViewBinding} that binds a {@link JTextComponent} to a {@link String}.
 * 
 * @author Eric Siebeneich
 */
final class TextComponentBinding extends ViewBinding<String>
{
	/** Local reference to the component to prevent unchecked casts */
	private final JTextComponent	componentReference;

	/**
	 * @see ViewBinding#ViewBinding(Model, String, BindablePolicy, JComponent)
	 */
	TextComponentBinding(Model model, String paramName, BindablePolicy policy, JTextComponent component)
	{
		super(model, paramName, policy, component);

		this.componentReference = component;
	}

	/**
	 * Sets the text of {@link #componentReference} to {@code data}
	 * 
	 * @param data
	 *            the new value to display
	 */
	@Override
	final void setData(String data)
	{
		componentReference.setText(data);
	}

	/**
	 * Retrieves the text displayed in {@link #componentReference}
	 * 
	 * @return the text displayed in {@code #componentReference}
	 */
	@Override
	final String getData()
	{
		return componentReference.getText();
	}
}
