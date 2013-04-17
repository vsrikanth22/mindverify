package verify.jboss.drools.simple;

import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.KnowledgeBuilderResult;
import org.drools.builder.KnowledgeBuilderResults;
import org.drools.builder.ResourceType;
import org.drools.builder.ResultSeverity;
import org.drools.conf.MultithreadEvaluationOption;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.event.rule.ObjectRetractedEvent;
import org.drools.event.rule.ObjectUpdatedEvent;
import org.drools.event.rule.WorkingMemoryEventListener;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class FirstRuleExample {

	public static void main(String[] args) {
		KnowledgeBuilderConfiguration conf = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(conf);
		kbuilder.add(ResourceFactory.newClassPathResource("rules/first.drl"), ResourceType.DRL);

		KnowledgeBuilderResults results = kbuilder.getResults(ResultSeverity.values());
		ResourceFactory.getResourceChangeNotifierService().start();
		for (KnowledgeBuilderResult result : results) {
			ResultSeverity severity = result.getSeverity();
			System.out.println(severity.name());
			int[] lines = result.getLines();
			for (int line : lines) {

				System.out.println(line);
			}
			System.out.println(result.getMessage());
		}

		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors());
			return;
		}
		
		Collection<KnowledgePackage> kpkgs = kbuilder.getKnowledgePackages();
		
		for(KnowledgePackage pkg : kpkgs) {
			System.out.println(pkg.toString());
		}
		
		KnowledgeBaseConfiguration kbconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		//kbconf.setOption(MultithreadEvaluationOption.YES);
		
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kpkgs);
		System.out.println(kbase.toString());
		
		KnowledgeAgent kagent = KnowledgeAgentFactory.newKnowledgeAgent( "MyAgent", kbase);
		kagent.monitorResourceChangeEvents(true);
		KnowledgeBase kbase1 =  kagent.getKnowledgeBase();
		System.out.println(kbase1.toString());
		
		kagent.dispose();
		ResourceFactory.getResourceChangeNotifierService().stop();
		Transaction transaction = new Transaction();
		transaction.setAmount(600000d);
		transaction.setType("WithDraw");
		
		Transaction transaction1 = new Transaction();
		transaction1.setAmount(1d);
		transaction1.setType("WithDraw");
		
		
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		ksession.insert(transaction);
		ksession.insert(transaction1);
		
		ksession.dispose();
		
	}

}
