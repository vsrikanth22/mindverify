package verify.utils;

public interface EntityAdapter<T1, T2> {
	
	public  T2 transform(T1 object);

}
