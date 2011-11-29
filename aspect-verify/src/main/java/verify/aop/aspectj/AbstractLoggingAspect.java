package verify.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public abstract class AbstractLoggingAspect {
	
	public abstract void traced();

}
