package com.zeebo.velociraptor.binding.view;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.zeebo.velociraptor.model.Model;

/**
 * Implementation of {@link ViewBinding} that binds a {@link JList} to a {@link List}.
 * 
 * @author Eric Siebeneich
 */
final class ListBinding extends ViewBinding<List<?>>
{
	/** The {@link ListViewBindingModel} rendered by the {@link JList} */
	private final ListViewBindingModel	listModel;

	/**
	 * @see ViewBinding#ViewBinding(Model, String, javax.swing.JComponent)
	 */
	public ListBinding(Model model, String paramName, JList list)
	{
		super(model, paramName, list);

		listModel = new ListViewBindingModel((List<?>)model.getValue(paramName));

		list.setModel(listModel);
	}

	/**
	 * Sets the list {@link #listModel} should reference
	 * 
	 * @param data
	 *            the new list to reference
	 */
	@Override
	final void setData(List<?> data)
	{
		listModel.setData(data);
	}

	/**
	 * Retrieves the list referenced by {@link #listModel}
	 * 
	 * @return the list referenced by {@code listModel}
	 */
	@Override
	final List<?> getData()
	{
		return listModel.getData();
	}

	/**
	 * Internal class that allows for a {@link List} reference to be displayed and modified remotely.
	 * 
	 * @author Eric Siebeneich
	 */
	@SuppressWarnings("serial")
	final class ListViewBindingModel extends AbstractListModel
	{
		/** The referenced {@link List} this model displays */
		private List<?>	data;

		/**
		 * Constructs a new {@code ListViewBindingModel}, referencing {@code data}.
		 * 
		 * @param data
		 *            {@link #data}
		 */
		ListViewBindingModel(List<?> data)
		{
			setData(data);
		}

		/**
		 * Returns the referenced {@link List}
		 * 
		 * @return {@link #data}
		 */
		List<?> getData()
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
	}
}
