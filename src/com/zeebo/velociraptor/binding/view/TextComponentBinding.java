package com.zeebo.velociraptor.binding.view;

import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
	TextComponentBinding(final Model model, final String paramName, final BindablePolicy policy, final JTextComponent component)
	{
		super(model, paramName, policy, component);

		this.componentReference = component;

		this.componentReference.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				model.setValue(paramName, componentReference.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				model.setValue(paramName, componentReference.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				model.setValue(paramName, componentReference.getText());
			}
		});
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
