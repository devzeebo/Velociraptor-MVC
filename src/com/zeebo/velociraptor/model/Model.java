package com.zeebo.velociraptor.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Observable;

import com.zeebo.velociraptor.annotation.Bindable;
import com.zeebo.velociraptor.view.View;

/**
 * Base implementation of {@code Model}. In order for auto binding with a compatible {@link View} to work, a class must extend
 * this class. Additionally, any instance variable that will be bound to a component in a {@code View} must be marked
 * {@link Bindable}.
 * 
 * @author Eric Siebeneich
 */
public class Model extends Observable
{
	/**
	 * Internal mapping of all the {@link Field}s this {@code Model} contains.
	 */
	private HashMap<String, Field>	fields;

	/**
	 * Default Constructor.
	 * 
	 * Creates a new {@code Model}.
	 */
	public Model()
	{
		fields = new HashMap<String, Field>();
		Field[] allFields = getClass().getDeclaredFields();

		for(Field f : allFields)
		{
			fields.put(f.getName(), f);
			f.setAccessible(true);
		}
	}

	/**
	 * Notifies any observers that the parameter {@code arg} has changed. This method forces {@link Observable#setChanged()} so
	 * that the notifications are always sent.
	 * 
	 * @param arg
	 *            the parameter that has changed
	 */
	public final void notifyObservers(String arg)
	{
		setChanged();
		super.notifyObservers(arg);
	}

	/**
	 * This method should be used to set the value of {@code param} in the model. It will automatically notify any observers
	 * watching {@code param}. This is equivalent to setting {@code model.}<i>{@code param}</i>, then calling
	 * {@linkplain #notifyObservers(String) model.notifyObservers("param")}.
	 * 
	 * @param param
	 *            the parameter name
	 * @param value
	 *            the value to set the parameter to
	 * @throws IllegalArgumentException
	 *             if value is of an incorrect type
	 */
	public final void setValue(String param, Object value)
		throws IllegalArgumentException
	{
		setValue2(param, value);
		notifyObservers(param);
	}

	/**
	 * Should only be used by the callback Observer.
	 * 
	 * @param param
	 *            the parameter name
	 * @param value
	 *            the value to set the parameter to
	 * @throws IllegalArgumentException
	 *             if value is of an incorrect type
	 */
	public final void setValue2(String param, Object value)
		throws IllegalArgumentException
	{
		try
		{
			fields.get(param).set(this, value);
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
	public final Object callMethod(String methodName)
		throws Exception // TODO: Fix Exception
	{
		Method m = getClass().getMethod(methodName);

		return m.invoke(this, (Object[])null);
	}
}