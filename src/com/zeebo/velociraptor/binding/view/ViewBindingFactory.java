package com.zeebo.velociraptor.binding.view;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import com.zeebo.velociraptor.annotation.BindablePolicy;
import com.zeebo.velociraptor.model.Model;

/**
 * Factory class to provide a simple, uniform way to create a new {@link ViewBinding}.
 * 
 * This class provides default implementations of {@code ViewBinding} for the following Swing classes:
 * 
 * <pre>
 *      {@link JTextComponent}, including {@link JTextField}, {@link JFormattedTextField}, {@link JPasswordField}, {@link JTextArea}, {@link JEditorPane}, {@link JTextPane}
 *      {@link JComboBox}
 *      {@link JLabel}
 *      {@link JList}
 *      {@link JProgressBar}
 *      {@link JSlider}
 *      {@link JSpinner}
 * </pre>
 * 
 * @author Eric Siebeneich
 */
public final class ViewBindingFactory
{
	/**
	 * Private to prevent instantiation.
	 */
	private ViewBindingFactory()
	{
	}

	/**
	 * Method to create a new {@link ViewBinding}, whose type is determined by the class of {@code component}. Returns
	 * {@code null} if there does not exist a default implementation of {@code ViewBinding} for {@code component}.
	 * 
	 * @param model
	 *            the {@link Model} to bind to
	 * @param argName
	 *            the field to bind to in {@code model}
	 * @param policy
	 *            the {@link BindablePolicy} to enforce
	 * @param component
	 *            the {@link JComponent} to bind
	 * @return a new {@link ViewBinding} that binds {@code model.argName} and {@code component}
	 */
	public static final ViewBinding<?> newViewBinding(Model model, String argName, BindablePolicy policy, JComponent component)
	{
		if(component instanceof JTextComponent)
		{
			return new TextComponentBinding(model, argName, policy, (JTextComponent)component);
		}
		if(component instanceof JLabel)
		{
			return new LabelBinding(model, argName, policy, (JLabel)component);
		}
		if(component instanceof JList)
		{
			return new ListBinding(model, argName, policy, (JList)component);
		}
		if(component instanceof JComboBox)
		{
			return new ComboBoxBinding(model, argName, policy, (JComboBox)component);
		}
		if(component instanceof JProgressBar)
		{
			return new ProgressBarBinding(model, argName, policy, (JProgressBar)component);
		}
		if(component instanceof JSlider)
		{
			return new SliderBinding(model, argName, policy, (JSlider)component);
		}
		if(component instanceof JSpinner)
		{
			return new SpinnerBinding(model, argName, policy, (JSpinner)component);
		}
		return null;
	}
}
