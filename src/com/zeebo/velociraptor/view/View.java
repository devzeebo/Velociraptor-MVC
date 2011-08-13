package com.zeebo.velociraptor.view;

import javax.swing.JFrame;

import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.binding.BindingProcessor;
import com.zeebo.velociraptor.model.Model;

/**
 * Base implementation of a {@code View}. In order for auto binding with a compatible {@link Model} to work, a class must extend
 * this class. Additionally, any components that should be bound to an instance variable in the {@code Model} must be marked with
 * a {@link Bind} annotation.
 * 
 * @author Eric Siebeneich
 * 
 * @param <T>
 *            the type of {@code Model} this view displays
 */
@SuppressWarnings("serial")
public abstract class View<T extends Model> extends JFrame
{
	/**
	 * Reference to the {@link Model} this {@code View} is displaying.
	 */
	private T	model;

	/**
	 * Default Constructor.
	 * 
	 * Creates a new {@code View} instance, 800x600 pixels in size, centered in the screen, with defaultCloseOperation set to
	 * {@link JFrame#EXIT_ON_CLOSE}.
	 */
	public View()
	{
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Returns the {@link Model} this {@code View} is currently displaying.
	 * 
	 * @return the {@code Model} this {@code View} is currently displaying
	 */
	public final T getModel()
	{
		return model;
	}

	/**
	 * Sets and binds the {@link Model} for this {@code View} to display
	 * 
	 * @param model
	 *            the {@code Model} for this {@code View} to display
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
