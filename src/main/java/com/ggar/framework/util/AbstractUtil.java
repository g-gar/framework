package com.ggar.framework.util;

import com.ggar.framework.container.Container;

public abstract class AbstractUtil<Type, ReturnType> implements Util<Type, ReturnType> {

	protected final Container container;
	
	public AbstractUtil(Container container) {
		this.container = container;
	}

}
