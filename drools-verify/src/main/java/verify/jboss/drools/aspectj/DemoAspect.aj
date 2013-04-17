package verify.jboss.drools.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

public aspect DemoAspect {

	pointcut profileAssertObject() : execution(* org.drools..*.assertObject(..)) || execution(* org.drools..*.createAndPropagateAssertLeftTuple(..));

	Object around() : profileAssertObject() {
		System.out.println("Intercepted message: "
				+ thisJoinPointStaticPart.getSignature().getName());
		System.out.println("in class: "
				+ thisJoinPointStaticPart.getSignature().getDeclaringType()
						.getName());
		printParameters(thisJoinPoint);
		System.out.println("Running original method: \n");
		Object result = proceed();
		System.out.println("  result: " + result);
		return result;
	}

	static private void printParameters(JoinPoint jp) {
		System.out.println("Object: " + jp.getThis());
		System.out.println("Arguments: ");
		Object[] args = jp.getArgs();
		String[] names = ((CodeSignature) jp.getSignature())
				.getParameterNames();
		Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		for (int i = 0; i < args.length; i++) {
			System.out.println("  " + i + ". " + names[i] + " : "
					+ types[i].getName() + " = " + args[i]);
		}
	}

}
