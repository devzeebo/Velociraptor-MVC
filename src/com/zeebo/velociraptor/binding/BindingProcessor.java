package com.zeebo.velociraptor.binding;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.JComponent;

import com.zeebo.common.reflection.ReflectionUtilities;
import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.annotation.Bindable;
import com.zeebo.velociraptor.binding.view.ViewBindingFactory;
import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.ViewPanel;

/**
 * This class is a utility class to create all the bindings between a {@link Model} and {@link ViewPanel}. It cannot be instantiated,
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
	public static void attachBindings(Model model, ViewPanel<?> view)
	{
		Field[] modelFieldArray = ReflectionUtilities.getAllFields(model.getClass(), Model.class);
		HashMap<String, Field> modelFields = new HashMap<String, Field>();

		for(Field f : modelFieldArray)
		{
			f.setAccessible(true);
			modelFields.put(f.getName(), f);
		}

		Field[] viewFields = ReflectionUtilities.getAllFields(view.getClass(), ViewPanel.class);

		for(Field f : viewFields)
		{
			Bind viewBinding = f.getAnnotation(Bind.class);

			if(ReflectionUtilities.testSubclassOf(f.getType(), JComponent.class) && viewBinding != null)
			{
				if(modelFields.get(viewBinding.value()) == null)
				{
					throw new RuntimeException("Field '" + viewBinding.value() + "' cannot be found for Model type "
						+ model.getClass());
				}
				Bindable modelBinding = modelFields.get(viewBinding.value()).getAnnotation(Bindable.class);
				if(modelBinding != null)
				{
					f.setAccessible(true);

					try
					{
						ViewBindingFactory.newViewBinding(model, viewBinding.value(), viewBinding.write(), modelBinding.value(),
							(JComponent)f.get(view));
					}
					catch(IllegalAccessException iae)
					{
					}
				} else
				{
					throw new RuntimeException("Attempting to bind to an unbindable field: '" + f.getName()
						+ "' cannot be bound to '" + viewBinding.value() + "'");
				}
			}
		}
	}
}
