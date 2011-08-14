package com.zeebo.velociraptor.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zeebo.velociraptor.annotation.Bind;
import com.zeebo.velociraptor.annotation.Bindable;
import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.ViewTest.ViewTestModel;

@SuppressWarnings("serial")
public class ViewTest extends View<ViewTestModel>
{
	public static void main(String args[])
	{
		new ViewTest();
	}

	@Bind("name")
	private JTextField	name;

	@Bind("name")
	private JLabel		nameLabel;

	public ViewTest()
	{
		setLayout(new FlowLayout());
		name = new JTextField(30);
		add(name);

		nameLabel = new JLabel();
		add(nameLabel);

		setModel(new ViewTestModel());

		JButton button = new JButton("Test");
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				getModel().setValue("name", "123");
			}
		});
		add(button);

		setVisible(true);
	}

	class ViewTestModel extends Model
	{
		@Bindable
		private String	name;
	}
}
