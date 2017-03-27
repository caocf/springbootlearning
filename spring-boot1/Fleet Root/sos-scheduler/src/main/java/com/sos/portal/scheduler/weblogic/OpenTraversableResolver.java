package com.sos.portal.scheduler.weblogic;

import java.lang.annotation.ElementType;

import javax.validation.Path;
import javax.validation.TraversableResolver;

public  class OpenTraversableResolver implements TraversableResolver {

    	public final boolean isReachable(Object traversableObject, Path.Node traversableProperty, Class<?> rootBeanType, Path pathToTraversableObject, ElementType elementType) {
    	return true;
    	}
    	public final boolean isCascadable(Object traversableObject, Path.Node traversableProperty, Class<?> rootBeanType, Path pathToTraversableObject, ElementType elementType) {
    	return true;
    	}
    }