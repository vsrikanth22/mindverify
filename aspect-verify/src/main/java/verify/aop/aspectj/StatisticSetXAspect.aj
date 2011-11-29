package verify.aop.aspectj;

import verify.aop.entity.Point;

public aspect StatisticSetXAspect {
	
	private int count; 
	
	pointcut count() :  execution(void Point.setX(int));
	
	before() : count() {
		count++;
		System.out.println(count);
	}
	

}
