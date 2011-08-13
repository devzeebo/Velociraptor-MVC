package com.zeebo.velociraptor.view;

import javax.swing.JFrame;

import com.zeebo.velociraptor.binding.BindingProcessor;
import com.zeebo.velociraptor.model.Model;

@SuppressWarnings("serial")
public abstract class View<T extends Model> extends JFrame
{
	private T	model;

	public View()
	{
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public final T getModel()
	{
		return model;
	}

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
