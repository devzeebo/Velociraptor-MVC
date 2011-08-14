package com.zeebo.velociraptor.binding.view;

import javax.swing.JComponent;
import javax.swing.JProgressBar;

import com.zeebo.velociraptor.annotation.BindablePolicy;
import com.zeebo.velociraptor.model.Model;

/**
 * Implementation of {@link ViewBinding} that binds a {@link JProgressBar} to an {@link Integer}.
 * 
 * @author Eric Siebeneich
 */
class ProgressBarBinding extends ViewBinding<Integer>
{
	/** Local reference to the component to prevent unchecked casts */
	private final JProgressBar	componentReference;

	/**
	 * @see ViewBinding#ViewBinding(Model, String, BindablePolicy, JComponent)
	 */
	ProgressBarBinding(Model model, String paramName, BindablePolicy policy, JProgressBar component)
	{
		super(model, paramName, policy, component);

		componentReference = component;
	}

	/**
	 * Sets the value of {@link #componentReference} to {@code data}
	 * 
	 * @param data
	 *            the new value to display
	 */
	@Override
	final void setData(Integer data)
	{
		componentReference.setValue(data);
	}

	/**
	 * Retrieves the value displayed by {@link #componentReference}
	 * 
	 * @return the value displayed by {@code #componentReference}
	 */
	@Override
	final Integer getData()
	{
		return componentReference.getValue();
	}
}
