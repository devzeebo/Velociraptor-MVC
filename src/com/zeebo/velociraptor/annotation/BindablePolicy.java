package com.zeebo.velociraptor.annotation;

/**
 * Enum for use with the {@link Bindable} annotation to mark the access rights a binding has on a field.
 * 
 * @author Eric Siebeneich
 */
public enum BindablePolicy
{
	/** Marks a field so that only read operations are permitted by the bindings */
	READ_ONLY,

	/** Marks a field so that both read and write operations are permitted by the bindings */
	READ_WRITE,

	/** Marks a field so that only write operations are permitted by the bindings */
	WRITE_ONLY
}
