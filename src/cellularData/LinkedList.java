package cellularData;


import java.util.Iterator;

/**
 * One object of this class represents the list of object.
 * There are eight method in this class. The constructor create a 
 * empty list object. The second method check if the first node in
 * the list is null. The third method add a node to the beginning of the list.
 * The fourth method check if the object can be found in the list.
 * The fifth method get index of a node. The sixth method get the
 * size of the list. The seventh method return a string containing 
 * all the requested information. The eighth method insert a node
 * into the list. The ninth method create a iterator object.
 * @author zhangJunliu Yuxuan Li
 *
 * @param <T>      generic parameter of type T
 */
public class LinkedList<T> implements Iterable<T>{
	private Node<T> head;
	private int length;
    
	/**
	 * inner class of LinkedList. ListIterator 
	 * moves through the data of LinkedList.
	 */
	private class ListIterator implements Iterator<T> {
		private Node<T> current;
		
		/**
		 * Constructor that point current to head
		 */
		public ListIterator() {
			current = head;
		}
		
		/**
		 * check if the next node is valid if node 
		 * is invalid, return false. otherwise we 
		 * haven't reached the end of the list
		 */
		public boolean hasNext() {
			if (current == null)
				return false;
			return true;
		}
		
		/**
		 * return the next available element
		 */
		public T next() {
			if (current == null)
			{
				throw new java.util.NoSuchElementException();
			}
			
			T data = current.getData();
			current = current.getNext();
			return data;
		}
		
		/**
		 * throw exception if currently implementation is not supported
		 */
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}
	}
		
	
	/**
	 * The constructor create an empty new list.  
	 */
	public LinkedList () {
		this.length = 0;
		head = null;
	}
	
	/**
	 * This method check if head is pointing to any node.
	 * @return        true if head is null
	 */
	public boolean isEmpty()
	{
		if (this.head == null)	
			return true;

		return false;
	}
	
	/**
	 * This method add a node to the beginning of the list.
	 * @param theData        the data portion of this node.
	 */
	public void add(T theData) {
		Node<T> newNode = new Node<T>(theData);
		if (this.isEmpty()) {
			head = newNode;
		    this.length++;
		    return;
		}
		
		Node<T> current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(newNode);
		this.length++;
	}
	
	/**
	 * This method remove a specific node in the list
	 * @param theData		the object that is going to remove
	 */
	public void remove(T theData){
		if(this.isEmpty()){
			return;
		}
		
		Node<T> walker = head;
		if(walker.getData().equals(theData)){
			head = head.getNext();
			return;
		}
		
		while( walker.getNext() != null){
			if(walker.getNext().getData().equals(theData)){
				walker.setNext(walker.getNext().getNext());
				break;
			}
			walker = walker.getNext();
		} 
	}
	
	/**
	 * This method check if the object can be found in the list. 
	 * @param toTest      one object represents object to test
	 * @return            null if the object cannot be found in the list.
	 */
	public T contains(T toTest) {
		Node<T> walker = head;
		while (walker != null) {
			if (toTest.equals(walker.getData())) {
		    	return walker.getData();
			}
		    walker = walker.getNext();			
		}
		return null;
	}
	
	/**
	 * This method get index of one node in the list.
	 * @param index      the index of one node in list
	 * @return           the data of the node at index
	 */
	public T getIndex(int index) {
		Node<T> walker = head;
		int i = 0;
		while(walker != null && i < index) {			
			walker = walker.getNext();
			i++;
		}
		return walker.getData();
	}
	
	/**
	 * This method get size of the list.
	 * @return       the size of the list
	 */
	public int size() {
		return this.length;		
	}
	
	/**
	 * This method return a string containing of data portion of the nodes.
	 */
	public String toString() {
		Iterator<T> iterator = this.iterator();
		String myString = "\n";
		while(iterator.hasNext()) {
			myString += iterator.next() + "\n";
		}
		return myString;
	}
	
	/**
	 * This method insert node into list at index. If index less than 
	 * zero, throw an exception. If index greater than the list size plus one, 
	 * add node to the end of the list. 
	 * @param theData     the data portion of the node
	 * @param index       the index to insert node
	 */
	public void insertAtIndex(T theData, int index) {
		Node<T> newNode = new Node<T>(theData);
		
		Node<T> prev = head;
		int count = 0;
		if (index < 0) {
			throw new IndexOutOfBoundsException("The requested year "
					+ "should be equal to or greater than 0");
		}
		else if (index > length + 1) {
			while (prev.getNext() != null) {
				prev = prev.getNext();				
			}
			prev.setNext(newNode);
			length++;
		}
		else if (index == 0) {
			head = newNode;
			head.setNext(prev);
		}
		else {
		    while (count < index - 1) {
			    prev = prev.getNext();
			    count++;
		    }
		    Node<T> current = prev.getNext();
		    prev.setNext(newNode);
		    newNode.setNext(current);
		    length++;
		}		
	}
	
	/**
	 * create an iterator object that starts at beginning of the list
	 */
	public ListIterator iterator() {
		return new ListIterator();
	}
}