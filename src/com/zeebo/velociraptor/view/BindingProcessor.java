package com.zeebo.velociraptor.view;

import java.lang.reflect.Field;

import javax.swing.JComponent;

import com.zeebo.common.reflection.ReflectionUtilities;
import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.binding.ViewBindingFactory;

class BindingProcessor
{
	private BindingProcessor()
	{
	}

	static final void processBindings(Model model, View<?> view)
	{
		Field[] allFields = view.getClass().getDeclaredFields();

		for(Field f : allFields)
		{
			Bind binding = f.getAnnotation(Bind.class);

			if(ReflectionUtilities.testSubclassOf(f.getType(), JComponent.class) && binding != null)
			{
				f.setAccessible(true);

				try
				{
					ViewBindingFactory.newViewBinding(model, binding.value(), (JComponent)f.get(view));
				}
				catch(IllegalAccessException iae)
				{
				}
			}
		}
	}
}
