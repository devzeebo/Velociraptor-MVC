package com.zeebo.velociraptor.controller;

import java.util.HashMap;

import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.ViewPanel;

public class Controller<M extends Model, V extends ViewPanel<M>>
{
	private HashMap<String, M>	models;
	private HashMap<String, V>	views;

	public Controller()
	{
		models = new HashMap<String, M>();
		views = new HashMap<String, V>();
	}

	public final void addModel(String name, M model)
	{
		if(models.get(name) != null)
		{
			models.get(name).deleteObservers();
		}
		models.put(name, model);
	}

	public final M getModel(String name)
	{
		return models.get(name);
	}

	public final void removeModel(String name)
	{
		if(models.get(name) != null)
		{
			models.get(name).deleteObservers();
		}
		models.remove(name);
	}

	public final void addView(String name, V view)
	{
		views.put(name, view);
	}

	public final V getView(String name)
	{
		return views.get(name);
	}

	public final void removeView(String name)
	{
		views.remove(name);
	}

	public void bind(String model, String view)
	{
		views.get(view).setModel(models.get(model));
	}
}
