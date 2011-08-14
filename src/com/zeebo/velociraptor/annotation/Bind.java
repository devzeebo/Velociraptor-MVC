package com.zeebo.velociraptor.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.ViewPanel;

/**
 * This annotation must be present on any {@link ViewPanel} component that needs to be bound to an instance variable in the
 * {@link Model}.
 * 
 * @author Eric Siebeneich
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bind
{
	/**
	 * The name of the field to bind to in the {@link Model}
	 */
	String value();

	/**
	 * The name of the field to write to in the {@link Model}. Not required
	 */
	String write() default "";
}