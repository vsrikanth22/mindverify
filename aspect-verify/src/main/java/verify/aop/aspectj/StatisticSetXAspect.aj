package verify.aop.aspectj;

import verify.aop.entity.Point;

public aspect StatisticSetXAspect {
	
	private int count; 
	
	pointcut count(int x) :  execution(void Point.setX(int)) && args(x);
	
	before(int x) : count(x) {
		count++;
		System.out.println("The setX's value is " + x);
		System.out.println(count);
	}
	

}
