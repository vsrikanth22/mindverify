package verify.jboss.jbpm;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;

public abstract class JbpmTestHelper extends Assert {
	
	protected StatefulKnowledgeSession createKnowledgeSession(String... process) {
		KnowledgeBase kbase = createKnowledgeBase(process);
		return createKnowledgeSession(kbase);
	}
	

	protected StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) {
	    StatefulKnowledgeSession result;
        final KnowledgeSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
	    // Do NOT use the Pseudo clock yet.. 
        // conf.setOption( ClockTypeOption.get( ClockType.PSEUDO_CLOCK.getId() ) );
        
		if (sessionPersistence) {
		    Environment env = createEnvironment(emf);
		    result = JPAKnowledgeService.newStatefulKnowledgeSession(kbase, conf, env);
		    new JPAWorkingMemoryDbLogger(result);
		    if (log == null) {
		    	log = new JPAProcessInstanceDbLog(result.getEnvironment());
		    }
		} else {
		    Environment env = EnvironmentFactory.newEnvironment();
		    env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, emf);
			result = kbase.newStatefulKnowledgeSession(conf, env);
			logger = new WorkingMemoryInMemoryLogger(result);
		}
		knowledgeSessionSetLocal.get().add(result);
		return result;
	}
	
	
	 protected KnowledgeBase createKnowledgeBase(String... process) {
	        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
	        for (String p: process) {
	            kbuilder.add(ResourceFactory.newClassPathResource(p), ResourceType.BPMN2);
	        }
	        
	        // Check for errors
	        if (kbuilder.hasErrors()) {
	            if (kbuilder.getErrors().size() > 0) {
	                boolean errors = false;
	                for (KnowledgeBuilderError error : kbuilder.getErrors()) {
	                    //testLogger.warn(error.toString());
	                    errors = true;
	                }
	                assertFalse("Could not build knowldge base.", errors);
	            }
	        }
	        return kbuilder.newKnowledgeBase();
	    }
	
	
	

}
