package verify.mvel.demo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.mvel2.MVEL;
import org.mvel2.optimizers.OptimizerFactory;

public class MvelDemo {
	
	public static void main(String[] args) {
		

		ScriptEngine jsEngine =  new ScriptEngineManager().getEngineByName("JavaScript");;
		
		List<ScriptEngineFactory> factories = new ScriptEngineManager().getEngineFactories();
		
		for(ScriptEngineFactory factory : factories) {
			Object value = factory.getParameter("THREADING");
			System.out.println(factory.getEngineName() + ":" + value.toString());
		}
		
		Serializable compiled = MVEL.compileExpression("System.out.println($user.name)");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("$user", new User());
		
		MVEL.executeExpression(compiled, variables);
		
		compiled = MVEL.compileExpression("!($user.name != null)");
		Boolean result = (Boolean)MVEL.executeExpression(compiled, variables);
		System.out.println(result);
	}

}
