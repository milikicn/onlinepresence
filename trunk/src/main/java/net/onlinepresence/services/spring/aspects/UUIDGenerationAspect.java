package net.onlinepresence.services.spring.aspects;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.services.spring.OPOResourceFactory;
import net.onlinepresence.util.urigenerator.URIBuilder;

public class UUIDGenerationAspect implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args,
			Object target) throws Throwable {
		
		ResourceBean resource = (ResourceBean) target;
		URIBuilder.instance().generateURI(resource, OPOResourceFactory.namespace);
	}
}