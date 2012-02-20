package verify.jboss.jbpm.test;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.impl.EnvironmentFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JbpmUnitTestCase extends Assert {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	protected StatefulKnowledgeSession createKnowledgeSession(String... process) {
		KnowledgeBase kbase = createKnowledgeBase(process);
		return createKnowledgeSession(kbase);
	}

	protected StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) {
		StatefulKnowledgeSession result;
		final KnowledgeSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();

		result = kbase.newStatefulKnowledgeSession(conf, EnvironmentFactory.newEnvironment());

		return result;
	}

	protected KnowledgeBase createKnowledgeBase(String... process) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		for (String p : process) {
			kbuilder.add(ResourceFactory.newClassPathResource(p), ResourceType.BPMN2);
		}

		// Check for errors
		if (kbuilder.hasErrors()) {
			if (kbuilder.getErrors().size() > 0) {
				boolean errors = false;
				for (KnowledgeBuilderError error : kbuilder.getErrors()) {
					// testLogger.warn(error.toString());
					logger.error(error.toString());
					errors = true;
				}
				assertFalse("Could not build knowldge base.", errors);
			}
		}
		return kbuilder.newKnowledgeBase();
	}

}
