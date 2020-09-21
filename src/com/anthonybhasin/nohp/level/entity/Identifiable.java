package com.anthonybhasin.nohp.level.entity;

/**
 * Useful to attach an identifier to an {@link Entity}.
 * 
 * @param <T> - specifies the class of the identifier.
 * 
 *            For example: use String for a name, or Integer for an id.
 */
public interface Identifiable<T> {

	public T getIdentifier();
}
