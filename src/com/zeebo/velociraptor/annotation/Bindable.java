package com.zeebo.velociraptor.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.ViewPanel;

/**
 * This annotation must be present on any {@link Model} instance variable that needs to be bound to a component in the
 * {@link ViewPanel}. <br />
 * <br />
 * A {@code Bindable} field can be marked either {@link BindablePolicy#READ_ONLY}, {@link BindablePolicy#READ_WRITE}, or
 * {@link BindablePolicy#WRITE_ONLY}. The value if unspecified is {@link BindablePolicy#READ_WRITE}.
 * 
 * @author Eric Siebeneich
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bindable
{
	/** Defines the operations allowed on the field */
	BindablePolicy value() default BindablePolicy.READ_WRITE;
}
