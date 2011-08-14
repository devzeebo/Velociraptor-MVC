package com.zeebo.velociraptor.view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.annotation.Bindable;
import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.ViewTest.ViewTestModel;

@SuppressWarnings("serial")
public class ViewTest extends ViewPanel<ViewTestModel>
{
	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new ViewTest());

		frame.setVisible(true);
	}

	@Bind("name")
	private JTextField	name;

	@Bind("name")
	private JLabel		nameLabel;

	@Bind(value = "list", write = "listSelected")
	private JList		list;

	@Bind("listSelected")
	private JLabel		listSelected;

	public ViewTest()
	{
		setLayout(new FlowLayout());
		name = new JTextField(30);
		add(name);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(list);

		nameLabel = new JLabel();
		add(nameLabel);

		listSelected = new JLabel();
		add(listSelected);

		setModel(new ViewTestModel());
	}

	class ViewTestModel extends Model
	{
		@Bindable
		private String			name;

		@Bindable
		private List<String>	list;

		@Bindable
		private String			listSelected;

		public ViewTestModel()
		{
			list = new ArrayList<String>();
			for(int x = 0; x < 5; x++)
			{
				list.add("" + x);
			}

			System.err.println(list);
		}
	}
}
