package verify.aop.aspectj;

import verify.aop.entity.FigureElement;
import verify.aop.entity.Point;

public aspect DemoAspect {

	pointcut move() : execution(void FigureElement.setXy(int, int)) || call(void Point.setX(int)) || call( void Point.setY(int));
	
	before(): move() {
	    System.out.println("about to move");
	}
}
