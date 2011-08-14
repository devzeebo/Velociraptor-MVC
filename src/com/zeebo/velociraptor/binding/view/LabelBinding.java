package com.zeebo.velociraptor.binding.view;

import javax.swing.JLabel;

import com.zeebo.velociraptor.model.Model;

/**
 * Implementation of {@link ViewBinding} that binds a {@link JLabel} to a {@link String}.
 * 
 * @author Eric Siebeneich
 */
class LabelBinding extends ViewBinding<String>
{
	/** Local reference to the component to prevent unchecked casts */
	private final JLabel	componentReference;

	/**
	 * @see ViewBinding#ViewBinding(Model, String, javax.swing.JComponent)
	 */
	LabelBinding(Model model, String paramName, JLabel component)
	{
		super(model, paramName, component);

		componentReference = component;
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
