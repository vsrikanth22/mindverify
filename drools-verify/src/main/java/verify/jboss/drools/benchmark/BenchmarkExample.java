package verify.jboss.drools.benchmark;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.builder.conf.DefaultDialectOption;
import org.drools.common.BetaConstraints;
import org.drools.definition.KnowledgePackage;
import org.drools.impl.KnowledgeBaseImpl;
import org.drools.io.ResourceFactory;
import org.drools.reteoo.BetaNode;
import org.drools.reteoo.ObjectSink;
import org.drools.reteoo.ObjectSinkPropagator;
import org.drools.reteoo.ObjectSource;
import org.drools.reteoo.ObjectTypeNode;
import org.drools.reteoo.Rete;
import org.drools.reteoo.ReteooRuleBase;
import org.drools.spi.BetaNodeFieldConstraint;

public class BenchmarkExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		KnowledgeBuilderConfiguration conf = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
		conf.setOption(DefaultDialectOption.get("java"));
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(conf);
		kbuilder.add(ResourceFactory.newClassPathResource("benchmark/benchmark.drl"), ResourceType.DRL);

		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors());
			return;
		}

		Collection<KnowledgePackage> kpkgs = kbuilder.getKnowledgePackages();

		for (KnowledgePackage pkg : kpkgs) {
			System.out.println(pkg.toString());
		}

		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kpkgs);

		KnowledgeBaseImpl kbImpl = (KnowledgeBaseImpl) kbase;
		ReteooRuleBase ruleBase = (ReteooRuleBase) kbImpl.ruleBase;

		Rete rete = ruleBase.getRete();
		for (ObjectTypeNode node : rete.getObjectTypeNodes()) {
			System.out.println(node.toString());
			iterateSinkPropagator(node.getSinkPropagator());

		}

	}

	private static void iterateSinkPropagator(ObjectSinkPropagator propagator) {
		for (ObjectSink sink : propagator.getSinks()) {
			
			if (sink instanceof ObjectSource) {
				System.out.println(sink.toString());
				ObjectSource source = (ObjectSource) sink;

				iterateSinkPropagator(source.getSinkPropagator());
			}
			
			if( sink instanceof BetaNode) {
				BetaNode betaNode = (BetaNode)sink;

				System.out.println(sink.toString());
				for(BetaNodeFieldConstraint constraint : betaNode.getConstraints() ) {
					System.out.println("  >>  " + constraint.toString());
				}
				//iterateSinkPropagator(betaNode.getSinkPropagator());
			}
		}
	}

	private List<UseCaseTxn> prepareData() {
		List<UseCaseTxn> txns = new LinkedList<UseCaseTxn>();
		return txns;

	}

}
