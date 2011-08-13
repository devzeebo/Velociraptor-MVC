package com.zeebo.velociraptor.binding.view;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.zeebo.velociraptor.model.Model;

final class ListBinding extends ViewBinding<List<?>>
{
	private final JList					componentReference;
	private final ListViewBindingModel	listModel;

	public ListBinding(Model model, String paramName, JList list)
	{
		super(model, paramName, list);

		listModel = new ListViewBindingModel((List<?>)model.getValue(paramName));

		this.componentReference = list;
		list.setModel(listModel);
	}

	@Override
	final void update(List<?> data)
	{
		listModel.setData(data);
	}

	@Override
	final List<?> getData()
	{
		return listModel.getData();
	}

	@SuppressWarnings("serial")
	final class ListViewBindingModel extends AbstractListModel
	{
		private List<?>	data;

		ListViewBindingModel(List<?> data)
		{
			setData(data);
		}

		List<?> getData()
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
	}
}
