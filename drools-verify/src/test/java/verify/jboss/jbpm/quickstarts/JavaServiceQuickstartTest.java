package verify.jboss.jbpm.quickstarts;

import java.util.HashMap;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

import verify.jboss.jbpm.test.JbpmUnitTestCase;

/**
 * This is a sample file to test a process.
 */
public class JavaServiceQuickstartTest extends JbpmUnitTestCase {

	@Test
	public void testSimpleScript() {
		StatefulKnowledgeSession ksession = createKnowledgeSession("quickstarts/ScriptTask.bpmn");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("person", new Person("krisv"));
	
		ksession.startProcess("com.sample.script", params);
	}
	
	/*@Test
	public void testConditionSimpleScript() {
		StatefulKnowledgeSession ksession = createKnowledgeSession("quickstarts/ConditionScriptTask.bpmn");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("person", new Person("john"));
		ksession.startProcess("com.sample.script", params);
	}*/


}