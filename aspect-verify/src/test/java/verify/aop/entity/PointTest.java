package verify.aop.entity;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

	@Test
	public void testPointSetX() throws Exception {

		Point p = new Point();
		p.setX(1);

		Assert.assertEquals(1, p.getX());

	}

}
