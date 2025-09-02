package ED;

public class NodoABB <T>{
	private NodoABB<T>   subIzq;
	private T Info;
	private NodoABB<T>   subDer;
	public NodoABB(T dato)
	{  	Info=dato;
		subIzq=null;
		subDer=null;
	}
	public NodoABB<T> getSubIzq() {
		return subIzq;
	}
	public void setSubIzq(NodoABB<T> subIzq) {
		this.subIzq = subIzq;
	}
	public T getInfo() {
		return Info;
	}
	public void setInfo(T info) {
		Info = info;
	}
	public NodoABB<T> getSubDer() {
		return subDer;
	}
	public void setSubDer(NodoABB<T> subDer) {
		this.subDer = subDer;
	}	

}