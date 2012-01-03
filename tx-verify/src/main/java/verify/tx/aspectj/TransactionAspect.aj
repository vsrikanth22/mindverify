package verify.tx.aspectj;

import verify.tx.Transactional;

public aspect TransactionAspect {

	/**
	 * Matches the execution of any public method in a type with the
	 * Transactional annotation, or any subtype of a type with the Transactional
	 * annotation.
	 */
	public pointcut executionOfAnyPublicMethodInAtTransactionalType() :
			execution(public * ((@Transactional *)+).*(..)) && @this(Transactional);

	/**
	 * Matches the execution of any method with the Transactional annotation.
	 */
	public pointcut executionOfTransactionalMethod() :
			execution(* *(..)) && @annotation(Transactional);

	/**
	 * Definition of pointcut from super aspect - matched join points will have
	 * Spring transaction management applied.
	 */
	public pointcut transactionalMethodExecution(Object txObject) :
			(executionOfAnyPublicMethodInAtTransactionalType()
			 || executionOfTransactionalMethod() )
			 && this(txObject);

}
