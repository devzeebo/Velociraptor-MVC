package com.zeebo.velociraptor.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.zeebo.velociraptor.model.Model;
import com.zeebo.velociraptor.view.View;

/**
 * This annotation must be present on any {@link Model} instance variable that needs to be bound to a component in the
 * {@link View}.
 * 
 * @author Eric Siebeneich
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bindable
{
}
