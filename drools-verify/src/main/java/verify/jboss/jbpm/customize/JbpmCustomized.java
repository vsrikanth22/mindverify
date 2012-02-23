package verify.jboss.jbpm.customize;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.common.AbstractRuleBase;
import org.drools.definition.process.Node;
import org.drools.impl.InternalKnowledgeBase;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.workflow.core.node.SubProcessNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import verify.jboss.jbpm.quickstarts.Person;

public class JbpmCustomized {
	
	final static private Logger logger = LoggerFactory.getLogger(JbpmCustomized.class);

	public static void main(String[] args) {

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		kbuilder.add(ResourceFactory.newClassPathResource("customized/ScriptTask.bpmn"), ResourceType.BPMN2);
		if (kbuilder.hasErrors()) {
			if (kbuilder.getErrors().size() > 0) {
				for (KnowledgeBuilderError error : kbuilder.getErrors()) {
					// testLogger.warn(error.toString());
					logger.error(error.toString());
				}
			}
		}
		
	  KnowledgeBase kb =	 kbuilder.newKnowledgeBase();
	  
	  org.drools.definition.process.Process process =((AbstractRuleBase) ((InternalKnowledgeBase) kb).getRuleBase()).getProcess("com.sample.script");
	  
	KnowledgeBase kb1 = KnowledgeBaseFactory.newKnowledgeBase();
	
	RuleFlowProcess process2 = (RuleFlowProcess)process;
	Node[] nodes = process2.getNodes();
	
	for(Node node: nodes) {
		SubProcessNode processNode = (SubProcessNode)node;
		processNode.setProcessId("com.sample.xxx");
	}
	
	((AbstractRuleBase) ((InternalKnowledgeBase) kb1).getRuleBase()).addProcess(process);
	
	StatefulKnowledgeSession session =  kb1.newStatefulKnowledgeSession();
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("person", new Person("john"));
	session.startProcess("com.sample.script", params);
	

	}

}
