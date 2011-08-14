package com.zeebo.velociraptor.binding;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.JComponent;

import com.zeebo.common.reflection.ReflectionUtilities;
import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.annotation.Bindable;
import com.zeebo.velociraptor.binding.view.ViewBindingFactory;
import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.View;

/**
 * This class is a utility class to create all the bindings between a {@link Model} and {@link View}. It cannot be instantiated,
 * and should be used only in a static context.
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
		Field[] modelFieldArray = ReflectionUtilities.getAllFields(model.getClass(), Model.class);
		HashMap<String, Field> modelFields = new HashMap<String, Field>();

		for(Field f : modelFieldArray)
		{
			f.setAccessible(true);
			modelFields.put(f.getName(), f);
		}

		Field[] viewFields = ReflectionUtilities.getAllFields(view.getClass(), View.class);

		for(Field f : viewFields)
		{
			Bind viewBinding = f.getAnnotation(Bind.class);
			Bindable modelBinding = modelFields.get(viewBinding.value()).getAnnotation(Bindable.class);

			if(modelBinding != null && ReflectionUtilities.testSubclassOf(f.getType(), JComponent.class) && viewBinding != null)
			{
				f.setAccessible(true);

				try
				{
					ViewBindingFactory.newViewBinding(model, viewBinding.value(), modelBinding.value(), (JComponent)f.get(view));
				}
				catch(IllegalAccessException iae)
				{
				}
			}
		}
	}
}
