package cellularData;


/**
 * One object of Node represents the data of one node.
 * There are five method in this class. The first method construct 
 * a node object. The second method constructs an object 
 * to hold the data of a node. The third method get the data portion of 
 * the node. The fourth method get next node. The six method set the next node.
 * @author zhangJunliu Yuxuan Li
 *
 * @param <T>     generic parameter of type T
 */
public class Node<T> {
	private T data;
	private Node<T> next;
	
	/**
	 * Constructs an object to hold the data
	 * and point to null as the next node.
	 * @param data      the data portion of this node.
	 */
	public Node(T data) {
		this.data = data;
		this.next = null;
	}
	
	/**
	 * Constructs an object to hold the data
	 * and point to another element as the next node.
	 * @param data            the data portion of this node.
	 * @param another         the node following this node.
	 */
	public Node(T data, Node<T> another) {
		this.data = data;
		this.next = another;
	}
	
	/**
	 * Mutator method get the data portion of the node.
	 * @return     the data portion of the node
	 */
	public T getData() { 	
		return this.data; 
	}
	
	/**
	 * Mutator method get the next node.
	 * @return the next node
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * Mutator method sets the next node.
	 * @param another		The node following this node.
	 */
	public void setNext(Node<T> another) {
		this.next = another;		
	}
}


