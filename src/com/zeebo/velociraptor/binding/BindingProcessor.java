package com.zeebo.velociraptor.binding;

import java.lang.reflect.Field;

import javax.swing.JComponent;

import com.zeebo.common.reflection.ReflectionUtilities;
import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.binding.view.ViewBindingFactory;
import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.View;

/**
 * This class is a utility class to create all the bindings between a {@link Model} and {@link View}. It cannot be instantiated,
 * and should be used in a static context.
 * 
 * @author Eric Siebeneich
 */
public final class BindingProcessor
{
	private BindingProcessor()
	{
	}

	/**
	 * This method creates the bindings between {@code model} and {@code view}
	 * 
	 * @param model
	 *            the model instance to link
	 * @param view
	 *            the view instance to link
	 */
	public static void attachBindings(Model model, View<?> view)
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
