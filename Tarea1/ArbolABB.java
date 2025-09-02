public class ArbolABB <T> {
	private NodoABB<T> raiz, nodoNuevo;
	private T dr;
	
	public ArbolABB() {
		raiz=null;
	}
	public boolean Insertar(T dato) {
		if(raiz == null) {
			raiz=new NodoABB(dato);
			return true;
		}
		return Insertar(raiz,dato);
	}
	private boolean Insertar(NodoABB<T> root, T dato) {
		if( root==null)
			return false;
		
		Comparable datoNuevo = (Comparable) dato;
		Comparable datoRoot= (Comparable) root.getInfo();
		int res = datoNuevo.compareTo(datoRoot);

		if(res > 0 ) {
			if( root.getSubDer()!= null)
				return Insertar(root.getSubDer(),dato);
			if( !CrearNodo(dato))
				return false;
			root.setSubDer(nodoNuevo);;
			return true;
		} 
		if(res<0) {
			if( root.getSubIzq()!= null)
				return Insertar(root.getSubIzq(),dato);
			if( !CrearNodo(dato))
				return false;
			root.setSubIzq(nodoNuevo);;
			return true;		
		}
		
		return false;
	}
	public boolean Retirar(T dato) {
		dr=null;
		return Retirar(raiz,null,false,dato);
	}
	private boolean Retirar(NodoABB<T> root, T dato) {
		if( root==null)
			return false;
		Comparable datoNuevo = (Comparable) dato;
		Comparable datoRoot= (Comparable) root.getInfo();
		int res = datoNuevo.compareTo(datoRoot);
		if(res>0)
			return Retirar(root.getSubDer(),dato);
		if(res<0)
			return Retirar(root.getSubIzq(),dato);
		// Encontró al nodo, señaloadp por root
		if( dr == null) 
			dr=root.getInfo();
		if( root.getSubDer()!=null && root.getSubIzq()!= null) {
			NodoABB<T> aux = root.getSubDer();
			
			while ( aux.getSubIzq() != null)
				aux=aux.getSubIzq();
			
			root.setInfo(aux.getInfo());
			dato=aux.getInfo();
			return Retirar(root.getSubDer(),dato);
		}
		else { // 1 o 0 hijos.
			if( root.getSubDer() == null)
				root=root.getSubIzq();
			else
				root=root.getSubDer();
		}
		return true;
		
	}
	private boolean Retirar(NodoABB<T> root,NodoABB<T> anterior,boolean sentido, T dato) {
		if( root==null)
			return false;
		
		Comparable datoNuevo = (Comparable) dato;
		Comparable datoRoot= (Comparable) root.getInfo();
		int res = datoNuevo.compareTo(datoRoot);
		if(res>0)
			return Retirar(root.getSubDer(),root,true,dato);
		if(res<0)
			return Retirar(root.getSubIzq(),root,false,dato);
		// Encontró al nodo, señaloadp por root
		if( dr == null) 
			dr=root.getInfo();

		
		if( root.getSubDer()!=null && root.getSubIzq()!= null) {
			
			NodoABB<T> aux = root.getSubDer();
					
			while ( aux.getSubIzq() != null) {
				aux=aux.getSubIzq();
			}

			root.setInfo(aux.getInfo());
			dato=aux.getInfo();
			
			return Retirar( root.getSubDer(),root,true,dato);
		}
		else { 
			if( root == raiz) {
				if( root.getSubIzq()==null && root.getSubDer()==null) {
					raiz=null;
				}else {
					raiz=root.getSubDer()==null?root.getSubIzq():root.getSubDer();
				}
				return true;
			}
			
			
			// 1 o 0 hijos.
			// llegue por el subIzq
			if(sentido) {
				anterior.setSubDer(root.getSubIzq()==null?root.getSubDer():root.getSubIzq());

			} else {
				
				anterior.setSubIzq(root.getSubIzq()==null?root.getSubDer():root.getSubIzq());
			}
		}
		return true;
	}
	public boolean Buscar(T dato) { 

		return Buscar(raiz,dato);
	}
	private boolean Buscar(NodoABB<T> root, T dato) {
//		if( root==null)
//			return false;
//		Comparable datoNuevo = (Comparable) dato;
//		Comparable datoRoot= (Comparable) root.getInfo();
//		int res = datoNuevo.compareTo(datoRoot);
//		if(res==0) { // encontro el nodo con la información de dato
//			dr=root.getInfo();
//			return true;
//		}
//		if(res > 0 ) 
//			return Buscar(root.getSubDer(),dato);
//		return Buscar(root.getSubIzq(),dato);
		Comparable datoNuevo = (Comparable) dato;
		while (root!=null) {
			Comparable datoRoot = (Comparable) root.getInfo();
			int res = datoNuevo.compareTo(datoRoot);
			if(res == 0) {
				dr=root.getInfo();
				return true;
			}
			if( res>0)
				root=root.getSubDer();
			else
				root=root.getSubIzq();
		}
		return false;
	}
	private boolean CrearNodo(T dato) {
		try {
			nodoNuevo= new NodoABB(dato);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public int cantidadNodos() {
		return cantidadNodos(raiz);
	}
	private int cantidadNodos(NodoABB<T> root) {
		if(root==null)
			return 0;
		return cantidadNodos(root.getSubIzq())+cantidadNodos(root.getSubDer())+1;
	}
	public int Altura() {
		return Altura(raiz,1);
	}
	private int Altura(NodoABB<T> root, int nivel) {
			if(root==null)
				return nivel-1;
			int alturaIzq,alturaDer;
			alturaIzq=Altura(root.getSubIzq(),nivel+1);
			alturaDer=Altura(root.getSubDer(),nivel+1);
//			if(alturaIzq>alturaDer)
//				return alturaIzq;
//			return alturaDer;
			return alturaIzq>alturaDer? alturaIzq:alturaIzq;
		
	
	}
	public T getDr() {
		return dr;
	}
	public NodoABB<T> getRaiz() {
		return raiz;
	}
}
