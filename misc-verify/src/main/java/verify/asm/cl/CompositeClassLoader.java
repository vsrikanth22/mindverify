package verify.asm.cl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CompositeClassLoader extends ClassLoader {
	
	private List<ClassLoader> classLoaders = new CopyOnWriteArrayList<ClassLoader>();
	
	
	
}
