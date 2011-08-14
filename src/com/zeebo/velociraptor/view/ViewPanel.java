package com.zeebo.velociraptor.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.binding.BindingProcessor;
import com.zeebo.velociraptor.model.Model;

/**
 * Base implementation of a {@code ViewPanel}. In order for auto binding with a compatible {@link Model} to work, a class must
 * extend this class. Additionally, any components that should be bound to an instance variable in the {@code Model} must be
 * marked with a {@link Bind} annotation.
 * 
 * @author Eric Siebeneich
 * 
 * @param <T>
 *            the type of {@code Model} this view displays
 */
@SuppressWarnings("serial")
public abstract class ViewPanel<T extends Model> extends JPanel
{
	/**
	 * Reference to the {@link Model} this {@code ViewPanel} is displaying.
	 */
	private T	model;

	/**
	 * Default Constructor.
	 * 
	 * Creates a new {@code ViewPanel} instance, 800x600 pixels in size, centered in the screen, with defaultCloseOperation set to
	 * {@link JFrame#EXIT_ON_CLOSE}.
	 */
	public ViewPanel()
	{
	}

	/**
	 * Returns the {@link Model} this {@code ViewPanel} is currently displaying.
	 * 
	 * @return the {@code Model} this {@code ViewPanel} is currently displaying
	 */
	public final T getModel()
	{
		return model;
	}

	/**
	 * Sets and binds the {@link Model} for this {@code ViewPanel} to display
	 * 
	 * @param model
	 *            the {@code Model} for this {@code ViewPanel} to display
	 */
	public final void setModel(T model)
	{
		if(this.model != null)
		{
			this.model.deleteObservers();
		}
		this.model = model;
		BindingProcessor.attachBindings(model, this);
	}
}
