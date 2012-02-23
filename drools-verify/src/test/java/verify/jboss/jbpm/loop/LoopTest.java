package verify.jboss.jbpm.loop;

import java.util.HashMap;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

import verify.jboss.jbpm.test.JbpmUnitTestCase;

public class LoopTest extends JbpmUnitTestCase {

	@Test
	public void testSimpleLoop() throws Exception {

		StatefulKnowledgeSession ksession = createKnowledgeSession("loop/Looping.bpmn");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("count", 10);
		params.put("i", 0);

		ksession.startProcess("com.sample.looping", params);

	}

}
