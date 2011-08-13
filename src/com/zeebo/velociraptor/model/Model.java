package com.zeebo.velociraptor.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Observable;

import com.zeebo.velociraptor.binding.view.ViewBinding;

public class Model extends Observable
{
	private HashMap<String, Field>	fields;

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
	 * Called by the {@link ViewBinding} to retrieve data from the model.
	 * 
	 * @param param
	 *            the parameter name
	 * @return the value of the parameter
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