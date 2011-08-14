package com.zeebo.velociraptor.binding.view;

import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.zeebo.velociraptor.annotation.BindablePolicy;
import com.zeebo.velociraptor.model.Model;

/**
 * Implementation of {@link ViewBinding} that binds a {@link JList} to a {@link List}.
 * 
 * @author Eric Siebeneich
 */
final class ListBinding extends ViewBinding<Object>
{
	/** The {@link ListViewBindingModel} rendered by the {@link JList} */
	private final ListViewBindingModel	listModel;

	/**
	 * @see ViewBinding#ViewBinding(Model, String, BindablePolicy, JComponent)
	 */
	public ListBinding(final Model model, final String readName, final String writeName, final BindablePolicy policy,
		final JList list)
	{
		super(model, readName, policy, list);

		listModel = new ListViewBindingModel(model.getValue(readName));

		list.setModel(listModel);

		list.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if(list.getSelectionMode() == ListSelectionModel.SINGLE_SELECTION)
				{
					model.setValue(writeName, list.getSelectedValue());
				} else
				{
					model.setValue(writeName,
						Arrays.copyOf(list.getSelectedValues(), list.getSelectedValues().length, String[].class));
				}
			}
		});
	}

	/**
	 * Sets the list {@link #listModel} should reference
	 * 
	 * @param data
	 *            the new list to reference
	 */
	@Override
	final void setData(Object data)
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
		ListViewBindingModel(Object data)
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
		void setData(Object data)
		{
			if(data instanceof List<?>)
			{
				this.data = (List<?>)data;
			} else
			{
				this.data = Arrays.asList((Object[])data);
			}
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
