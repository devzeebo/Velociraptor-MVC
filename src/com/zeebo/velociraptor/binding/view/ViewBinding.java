package com.zeebo.velociraptor.binding.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import com.zeebo.velociraptor.model.Model;

public abstract class ViewBinding<T> implements Observer
{
	protected Model				model;
	protected final String		paramName;
	protected final JComponent	component;

	ViewBinding(Model model, String paramName, JComponent component)
	{
		setModel(model);
		this.paramName = paramName;
		this.component = component;
	}

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

	@Override
	public final void update(Observable o, Object arg)
	{
		if(((String)arg).equals(paramName) && o instanceof Model && (getData() == null || !getData().equals(getParamValue())))
		{
			update(getParamValue());

			component.repaint();
			component.revalidate();
		}
	}

	abstract T getData();

	abstract void update(T data);

	@SuppressWarnings("unchecked")
	private final T getParamValue()
	{
		return (T)model.getValue(paramName);
	}
}
