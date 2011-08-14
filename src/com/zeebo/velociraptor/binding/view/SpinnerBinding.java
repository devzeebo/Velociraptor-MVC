package com.zeebo.velociraptor.binding.view;

import java.util.List;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;

import com.zeebo.velociraptor.model.Model;

/**
 * Implementation of {@link ViewBinding} that binds a {@link JSpinner} to a {@link List}.
 * 
 * @author Eric Siebeneich
 */
class SpinnerBinding extends ViewBinding<List<?>>
{
	/** The {@link SpinnerBindingModel} rendered by the {@link JSpinner} */
	private final SpinnerBindingModel	spinnerModel;

	/**
	 * @see ViewBinding#ViewBinding(Model, String, javax.swing.JComponent)
	 */
	SpinnerBinding(Model model, String paramName, JSpinner list)
	{
		super(model, paramName, list);

		spinnerModel = new SpinnerBindingModel((List<?>)model.getValue(paramName));

		list.setModel(spinnerModel);
	}

	/**
	 * Sets the list {@link #spinnerModel} should reference
	 * 
	 * @param data
	 *            the new list to reference
	 */
	@Override
	final void setData(List<?> data)
	{
		spinnerModel.setData(data);
	}

	/**
	 * Retrieves the list referenced by {@link #spinnerModel}
	 * 
	 * @return the list referenced by {@code spinnerModel}
	 */
	@Override
	final List<?> getData()
	{
		return spinnerModel.getData();
	}

	/**
	 * Internal class that allows for a {@link List} reference to be displayed and modified remotely.
	 * 
	 * @author Eric Siebeneich
	 */
	final class SpinnerBindingModel extends AbstractSpinnerModel
	{
		/** The referenced {@link List} this model displays */
		private List<?>	data;

		/** The current value of the model */
		private Object	value;

		/** The current index of the model */
		private int		index;

		/**
		 * Constructs a new {@code SpinnerBindingModel}, referencing {@code data}.
		 * 
		 * @param data
		 *            {@link #data}
		 */
		SpinnerBindingModel(List<?> data)
		{
			index = 0;
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
		public Object getValue()
		{
			return value;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void setValue(Object value)
		{
			this.value = value;
			index = data.indexOf(value);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Object getNextValue()
		{
			if(index == data.size())
			{
				return null;
			}

			return data.get(index + 1);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public Object getPreviousValue()
		{
			if(index == 0)
			{
				return null;
			}

			return data.get(index - 1);
		}
	}
}
