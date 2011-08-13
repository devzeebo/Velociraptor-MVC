package com.zeebo.velociraptor.binding.view;

import java.util.List;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;

import com.zeebo.velociraptor.model.Model;

class SpinnerBinding extends ViewBinding<List<?>>
{
	private final JSpinner				componentReference;
	private final SpinnerBindingModel	spinnerModel;

	SpinnerBinding(Model model, String paramName, JSpinner list)
	{
		super(model, paramName, list);

		spinnerModel = new SpinnerBindingModel((List<?>)model.getValue(paramName));

		this.componentReference = list;
		list.setModel(spinnerModel);
	}

	@Override
	final void update(List<?> data)
	{
		spinnerModel.setData(data);
	}

	@Override
	final List<?> getData()
	{
		return spinnerModel.getData();
	}

	final class SpinnerBindingModel extends AbstractSpinnerModel
	{
		private List<?>	data;
		private Object	value;
		private int		index;

		SpinnerBindingModel(List<?> data)
		{
			index = 0;
			setData(data);
		}

		public List<?> getData()
		{
			return data;
		}

		void setData(List<?> data)
		{
			this.data = data;
		}

		@Override
		public Object getValue()
		{
			return value;
		}

		@Override
		public void setValue(Object value)
		{
			this.value = value;
			index = data.indexOf(value);
		}

		@Override
		public Object getNextValue()
		{
			if(index == data.size())
			{
				return null;
			}

			return data.get(index + 1);
		}

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
