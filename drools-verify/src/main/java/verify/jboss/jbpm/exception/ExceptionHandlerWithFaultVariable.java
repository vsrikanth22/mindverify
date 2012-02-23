package verify.jboss.jbpm.exception;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.instance.impl.demo.DoNothingWorkItemHandler;

public class ExceptionHandlerWithFaultVariable {
	
	public static void main(String[] args) {
		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger(ksession, "test", 1000);
			// start a new process instance
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("raiseException", true);
			ksession.startProcess("ExceptionHandlerWithFaultVariable", params);
			logger.close();
			System.out.println("Process Completed");
			System.exit(0);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("exception/ExceptionHandlerWithFaultVariable.bpmn2"),
				ResourceType.BPMN2);
		return kbuilder.newKnowledgeBase();
	}
}
