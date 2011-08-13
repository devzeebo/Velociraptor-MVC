package com.zeebo.velociraptor.binding.view;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.text.JTextComponent;

import com.zeebo.velociraptor.model.Model;

public final class ViewBindingFactory
{
	private ViewBindingFactory()
	{
	}

	public static final ViewBinding<?> newViewBinding(Model model, String argName, JComponent component)
	{
		if(component instanceof JTextComponent)
		{
			return new TextComponentBinding(model, argName, (JTextComponent)component);
		}
		if(component instanceof JLabel)
		{
			return new LabelBinding(model, argName, (JLabel)component);
		}
		if(component instanceof JList)
		{
			return new ListBinding(model, argName, (JList)component);
		}
		if(component instanceof JComboBox)
		{
			return new ComboBoxBinding(model, argName, (JComboBox)component);
		}
		if(component instanceof JProgressBar)
		{
			return new ProgressBarBinding(model, argName, (JProgressBar)component);
		}
		if(component instanceof JSlider)
		{
			return new SliderBinding(model, argName, (JSlider)component);
		}
		if(component instanceof JSpinner)
		{
			return new SpinnerBinding(model, argName, (JSpinner)component);
		}
		return null;
	}
}
