package com.zeebo.velociraptor.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Observable;

import com.zeebo.common.reflection.ReflectionUtilities;
import com.zeebo.velociraptor.annotation.Bindable;
import com.zeebo.velociraptor.view.ViewPanel;

/**
 * Base implementation of {@code Model}. In order for auto binding with a compatible {@link ViewPanel} to work, a class must extend
 * this class. Additionally, any instance variable that will be bound to a component in a {@code ViewPanel} must be marked
 * {@link Bindable}.
 * 
 * @author Eric Siebeneich
 */
public class Model extends Observable
{
	/**
	 * Internal mapping of all the {@link Field}s this {@code Model} and its parents contain.
	 */
	private HashMap<String, Field>	fields;

	/**
	 * Default Constructor.
	 * 
	 * Creates a new {@code Model}.
	 */
	public Model()
	{
		Field[] fieldArray = ReflectionUtilities.getAllFields(this.getClass(), Model.class);
		fields = new HashMap<String, Field>();

		for(Field f : fieldArray)
		{
			f.setAccessible(true);
			fields.put(f.getName(), f);
		}
	}

	/**
	 * Notifies any observers that the field {@code arg} has changed. This method forces {@link Observable#setChanged()} so that
	 * the notifications are always sent.
	 * 
	 * @param arg
	 *            the field that has changed
	 */
	public final void notifyObservers(String arg)
	{
		setChanged();
		super.notifyObservers(arg);
	}

	/**
	 * This method should be used to set the value of {@code field} in the model. It will automatically notify any observers
	 * watching {@code field}. This is equivalent to setting {@code model.}<i>{@code field}</i>, then calling
	 * {@linkplain #notifyObservers(String) model.notifyObservers("field")}.
	 * 
	 * @param field
	 *            the field name
	 * @param value
	 *            the value to set the field to
	 * @throws IllegalArgumentException
	 *             if value is of an incorrect type
	 */
	public final void setValue(String field, Object value)
		throws IllegalArgumentException
	{
		setValue2(field, value);
		notifyObservers(field);
	}

	/**
	 * Should only be used by the callback Observer.
	 * 
	 * @param field
	 *            the field name
	 * @param value
	 *            the value to set the field to
	 * @throws IllegalArgumentException
	 *             if value is of an incorrect type
	 */
	public final void setValue2(String field, Object value)
		throws IllegalArgumentException
	{
		try
		{
			fields.get(field).set(this, value);
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns the value of the specified field.
	 * 
	 * @param param
	 *            the field name
	 * @return the value of the field
	 */
	public final Object getValue(String param)
	{
		try
		{
			return fields.get(param).get(this);
		}
		catch(IllegalArgumentException iae)
		{
			iae.printStackTrace();
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Invokes the target method with no arguments.
	 * 
	 * @param methodName
	 *            the method to invoke
	 * @return the result of the method
	 * @throws Exception
	 *             if an error occurs while retrieving or invoking the method
	 */
	public final Object callMethod(String methodName, Object... args)
		throws Exception // TODO: Fix Exception
	{
		Class<?>[] argTypes = new Class<?>[args.length];
		for(int x = 0; x < args.length; x++)
		{
			argTypes[x] = args[x].getClass();
		}

		Method m = getClass().getMethod(methodName, argTypes);

		return m.invoke(this, args);
	}
}