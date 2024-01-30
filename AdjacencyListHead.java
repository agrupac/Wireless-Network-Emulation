import java.util.*;
/**
* This class represents the head node of an adjacency list.
*/
public class AdjacencyListHead {
	/**
	* The underlying node.
	*/
	private NetNode node;
	/**
	* The adjacency list.
	*/
	private LinkedList<Adjacent> adjacencyList;
	/**
	* A constructor that sets node and creates empty adjacency list.
	*
	* @param node a NetNode
	*/
	public AdjacencyListHead(NetNode node){
		this.node = node;
		this.adjacencyList = new LinkedList<Adjacent>();
	}
	/**
	* A constructor that assigns arguments to fields.
	*
	* @param node a NetNode
	* @param adjacencyList an adjacency list
	*/
	public AdjacencyListHead(NetNode node,LinkedList<Adjacent> adjacencyList){
		this.node = node;
		this.adjacencyList = adjacencyList;
	}
	/**
	* A setter for node.
	*
	* @param node a NetNode
	*/
	public void setNetNode(NetNode node){
		this.node = node;
	}
	/**
	* A setter for adjacencyList.
	*
	* @param adjacencyList an adjacency list
	*/
	public void setAdjacencyList(LinkedList<Adjacent> adjacencyList){
		this.adjacencyList = adjacencyList;
	}
	/**
	* A getter for node.
	*
	* @return node the node
	*/
	public NetNode getNetNode(){
		return node;
	}
	/**
	* A getter for adjacencyList.
	*
	* @return adjacencyList the adjacencyList
	*/
	public LinkedList<Adjacent> getAdjacencyList(){
		return adjacencyList;
	}

}
