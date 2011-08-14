package com.zeebo.velociraptor.binding.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import com.zeebo.velociraptor.model.Model;

/**
 * Abstract class that provides the basic functionality required to bind a {@link JComponent} to a field in a {@link Model}.
 * 
 * @author Eric Siebeneich
 * 
 * @param <T>
 *            The class of the field in the bound {@link Model}
 */
public abstract class ViewBinding<T> implements Observer
{
	/** The {@link Model} the binding is bound to */
	private Model				model;

	/** The name of the field the binding is bound to */
	protected final String		fieldName;

	/** The {@link JComponent} the binding is bound to */
	protected final JComponent	component;

	/**
	 * Default constructor.
	 * 
	 * @param model
	 *            The {@link Model} to bind this {@code ViewBinding} to
	 * @param fieldName
	 *            {@link #fieldName}
	 * @param component
	 *            {@link #component}
	 */
	ViewBinding(Model model, String fieldName, JComponent component)
	{
		setModel(model);
		this.fieldName = fieldName;
		this.component = component;
	}

	/**
	 * Sets the {@link Model} this binding is bound to.
	 * 
	 * @param model
	 *            The {@link Model} to bind this {@code ViewBinding} to
	 */
	final void setModel(Model model)
	{
		try
		{
			this.model.deleteObserver(this);
		}
		catch(NullPointerException npe)
		{
		}

		this.model = model;
		this.model.addObserver(this);
	}

	/**
	 * This method listens to the {@link Model} and updates {@link #component} if a change has occurred to the value referenced by
	 * {@link #fieldName}.
	 * 
	 * @param o
	 *            This will be the {@link Model} this binding is bound to
	 * @param arg
	 *            The field that was changed in the {@code Model}
	 */
	@Override
	public final void update(Observable o, Object arg)
	{
		if(((String)arg).equals(fieldName) && o instanceof Model && (getData() == null || !getData().equals(getParamValue())))
		{
			setData(getParamValue());

			component.repaint();
			component.revalidate();
		}
	}

	/**
	 * Abstract method used to retrieve data from {@link #component}. Implementation specific.
	 * 
	 * @return the data currently being displayed in {@code component}
	 */
	abstract T getData();

	/**
	 * Abstract method used to set the data displayed by {@link #component}. Implementation specific.
	 * 
	 * @param data
	 *            the data that {@code component} should display
	 */
	abstract void setData(T data);

	/**
	 * Private method used to retrieve the value of {@link #fieldName} from {@link #model}
	 * 
	 * @return the value of {@code fieldName} in {@code model}
	 */
	@SuppressWarnings("unchecked")
	private final T getParamValue()
	{
		return (T)model.getValue(fieldName);
	}
}
