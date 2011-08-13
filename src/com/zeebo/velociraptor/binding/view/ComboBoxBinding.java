package com.zeebo.velociraptor.binding.view;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.zeebo.velociraptor.model.Model;

class ComboBoxBinding extends ViewBinding<List<?>>
{
	private final JComboBox				componentReference;
	private final ComboBoxBindingModel	comboModel;

	ComboBoxBinding(Model model, String paramName, JComboBox list)
	{
		super(model, paramName, list);

		comboModel = new ComboBoxBindingModel((List<?>)model.getValue(paramName));

		this.componentReference = list;
		componentReference.setModel(comboModel);
	}

	@Override
	final void update(List<?> data)
	{
		comboModel.setData(data);
	}

	@Override
	final List<?> getData()
	{
		return comboModel.getData();
	}

	@SuppressWarnings("serial")
	final class ComboBoxBindingModel extends DefaultComboBoxModel
	{
		private List<?>	data;

		ComboBoxBindingModel(List<?> data)
		{
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
		public int getSize()
		{
			return data.size();
		}

		@Override
		public Object getElementAt(int index)
		{
			return data.get(index);
		}

		@Override
		public int getIndexOf(Object anObject)
		{
			return data.indexOf(anObject);
		}
	}
}
