package com.zeebo.velociraptor.binding.view;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.zeebo.velociraptor.annotation.BindablePolicy;
import com.zeebo.velociraptor.binding.view.ListBinding.ListViewBindingModel;
import com.zeebo.velociraptor.model.Model;

/**
 * Implementation of {@link ViewBinding} that binds a {@link JComboBox} to a {@link List}.
 * 
 * @author Eric Siebeneich
 */
class ComboBoxBinding extends ViewBinding<List<?>>
{
	/** The {@link ListViewBindingModel} rendered by the {@link JComboBox} */
	private final ComboBoxBindingModel	comboModel;

	/**
	 * @see ViewBinding#ViewBinding(Model, String, BindablePolicy, JComponent)
	 */
	ComboBoxBinding(Model model, String paramName, BindablePolicy policy, JComboBox list)
	{
		super(model, paramName, policy, list);

		comboModel = new ComboBoxBindingModel((List<?>)model.getValue(paramName));

		list.setModel(comboModel);
	}

	/**
	 * Sets the list {@link #comboModel} should reference
	 * 
	 * @param data
	 *            the new list to reference
	 */
	@Override
	final void setData(List<?> data)
	{
		comboModel.setData(data);
	}

	/**
	 * Retrieves the list referenced by {@link #comboModel}
	 * 
	 * @return the list referenced by {@code comboModel}
	 */
	@Override
	final List<?> getData()
	{
		return comboModel.getData();
	}

	/**
	 * Internal class that allows for a {@link List} reference to be displayed and modified remotely.
	 * 
	 * @author Eric Siebeneich
	 */
	@SuppressWarnings("serial")
	final class ComboBoxBindingModel extends DefaultComboBoxModel
	{
		/** The referenced {@link List} this model displays */
		private List<?>	data;

		/**
		 * Constructs a new {@code ListViewBindingModel}, referencing {@code data}.
		 * 
		 * @param data
		 *            {@link #data}
		 */
		ComboBoxBindingModel(List<?> data)
		{
			setData(data);
		}

		/**
		 * Returns the referenced {@link List}
		 * 
		 * @return {@link #data}
		 */
		public List<?> getData()
		{
			return data;
		}

		/**
		 * Sets the referenced {@link List}
		 * 
		 * @param data
		 *            {@link #data}
		 */
		void setData(List<?> data)
		{
			this.data = data;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public int getSize()
		{
			return data.size();
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Object getElementAt(int index)
		{
			return data.get(index);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public int getIndexOf(Object anObject)
		{
			return data.indexOf(anObject);
		}
	}
}
