import java.util.*;
/**
* This class represents a graph of nodes and links.
*/
public class NetGraph {
	/**
	* All the nodes in the graph.
	*/
	public ArrayList<AdjacencyListHead> nodesList;
	/**
	* A constructor.
	*
	* @param nodesList a nodes list
	*/
	public NetGraph(ArrayList<AdjacencyListHead> nodesList){
		this.nodesList = nodesList;
	}
	/**
	* A getter for nodesList.
	*
	* @return nodesList the nodes list
	*/
	public ArrayList<AdjacencyListHead> getNodesList(){
		return nodesList;
	}
	/**
	* Finds the number of nodes.
	*
	* @return nodeList.size() the number of nodes
	*/
	public int getNumNodes(){
		//Implement this method
		//Should return the number of nodes in the Graph
		return nodesList.size();
	}
	/**
	* Finds the number of links.
	*
	* @return sum / 2 the number of adjacents divided by two
	*/
	public int getNumLinks(){
		int sum = 0;
		for(AdjacencyListHead a : nodesList){
			sum += a.getAdjacencyList().size();
		}
		return sum / 2;
	}
	/**
	* Inserts a node into the graph.
	*
	* @param id an ID
	* @param name a name
	* @param xcoordinate an x coordinate
	* @param ycoordinate a y coordinate
	* @throws IllegalArgumentException if node already exists
	*/
	public void insertNetNode(int id, String name, double xcoordinate, double ycoordinate) throws IllegalArgumentException{

		for(AdjacencyListHead head : nodesList){
			if(head.getNetNode().getId() == id) throw new IllegalArgumentException("Node already exists in graph.");
		}
		AdjacencyListHead node = new AdjacencyListHead(new NetNode(id, name, xcoordinate, ycoordinate));
		nodesList.add(node);
	}
	/**
	* Creates a link between two nodes with a given weight.
	*
	* @param node1 the first node
	* @param node2 the second node
	* @param weight the edge weight
	* @throws IllegalArgumentException if node is null or does not exist
	*/
	public void addLink(NetNode node1, NetNode node2, double weight) throws IllegalArgumentException{

		if(node1.equals(node2)) return;

		if(node1 == null || node2 == null) throw new IllegalArgumentException("Node cannot be null.");

		AdjacencyListHead nodeToFind1 = new AdjacencyListHead(null);
		AdjacencyListHead nodeToFind2 = new AdjacencyListHead(null);
		boolean n1Exists = false;
		boolean n2Exists = false;

		for(AdjacencyListHead head : nodesList){
			if(head.getNetNode().getId() == node1.getId()){
				nodeToFind1 = head;
				n1Exists = true;
			}
			else if(head.getNetNode().getId() == node2.getId()){
				nodeToFind2 = head;
				n2Exists = true;
			}
			if(n1Exists && n2Exists){
				break;
			}
		}
		if(!n1Exists || !n2Exists) throw new IllegalArgumentException("Node does not exist.");

		Adjacent adjacentToAdd1 = new Adjacent(node2, weight);
		Adjacent adjacentToAdd2 = new Adjacent(node1, weight);
		boolean a1Exists = false;
		boolean a2Exists = false;

		for(Adjacent adjacent : nodeToFind1.getAdjacencyList()){
			if(adjacent.getNeighbor().getId() == node2.getId()){
				a1Exists = true;
				break;
			}
		}
		for(Adjacent adjacent : nodeToFind2.getAdjacencyList()){
			if(adjacent.getNeighbor().getId() == node1.getId()){
				a2Exists = true;
				break;
			}
		}

		if(!a1Exists && !a2Exists){
			nodeToFind1.getAdjacencyList().add(adjacentToAdd1);
			nodeToFind2.getAdjacencyList().add(adjacentToAdd2);
		}

	}
	/**
	* Removes a node from the graph.
	*
	* @param node the node to remove
	* @throws IllegalArgumentException if node is null or does not exist
	*/
	public void deleteNetNode(NetNode node) throws IllegalArgumentException{

		if(node == null) throw new IllegalArgumentException("Node cannot be null.");

		AdjacencyListHead nodeToRemove = new AdjacencyListHead(null);
		boolean exists = false;

		for(AdjacencyListHead head : nodesList){
			if(head.getNetNode().getId() == node.getId()){
				nodeToRemove = head;
				exists = true;
				break;
			}
		}
		if(!exists) throw new IllegalArgumentException("Node does not exist.");

		for(AdjacencyListHead head : nodesList){
			for(Adjacent adjacent : head.getAdjacencyList()){
				if(adjacent.getNeighbor().equals(node)){
					head.getAdjacencyList().remove(adjacent);
				}
			}
		}
		nodesList.remove(nodeToRemove);

	}
	/**
	* Removes a link between two nodes.
	*
	* @param node1 the first node
	* @param node2 the second node
	* @throws IllegalArgumentException if node is null or does not exist
	*/
	public void removeLink(NetNode node1, NetNode node2) throws IllegalArgumentException{

		if(node1 == null || node2 == null) throw new IllegalArgumentException("Node cannot be null.");

		AdjacencyListHead nodeToRemove1 = new AdjacencyListHead(null);
		AdjacencyListHead nodeToRemove2 = new AdjacencyListHead(null);
		boolean n1Exists = false;
		boolean n2Exists = false;

		for(AdjacencyListHead head : nodesList){
			if(head.getNetNode().getId() == node1.getId()){
				nodeToRemove1 = head;
				n1Exists = true;
			}
			else if(head.getNetNode().getId() == node2.getId()){
				nodeToRemove2 = head;
				n2Exists = true;
			}
			if(n1Exists && n2Exists){
				break;
			}
		}
		if(!n1Exists || !n2Exists) throw new IllegalArgumentException("Node does not exist.");

		Adjacent adjacentToRemove1 = new Adjacent(null, 0);
		Adjacent adjacentToRemove2 = new Adjacent(null, 0);
		boolean a1Exists = false;
		boolean a2Exists = false;

		for(Adjacent adjacent : nodeToRemove1.getAdjacencyList()){
			if(adjacent.getNeighbor().equals(node2)){
				adjacentToRemove1 = adjacent;
				a1Exists = true;
				break;
			}
		}
		for(Adjacent adjacent : nodeToRemove2.getAdjacencyList()){
			if(adjacent.getNeighbor().equals(node1)){
				adjacentToRemove2 = adjacent;
				a2Exists = true;
				break;
			}
		}

		if(a1Exists && a2Exists){
			nodeToRemove1.getAdjacencyList().remove(adjacentToRemove1);
			nodeToRemove2.getAdjacencyList().remove(adjacentToRemove2);
		}

	}
	/**
	* Returns the adjacency list of a given node.
	*
	* @param node the node to find
	* @return nodeToFind.getAdjacencyList() the adjacency list
	* @throws IllegalArgumentException if node is null or does not exist
	*/
	public LinkedList<Adjacent> getAdjacents(NetNode node) throws IllegalArgumentException{

		if(node == null) throw new IllegalArgumentException("Node cannot be null.");

		AdjacencyListHead nodeToFind = new AdjacencyListHead(null);
		boolean exists = false;

		for(AdjacencyListHead head : nodesList){
			if(head.getNetNode().getId() == node.getId()){
				nodeToFind = head;
				exists = true;
				break;
			}
		}
		if(!exists) throw new IllegalArgumentException("Node does not exist.");

		return nodeToFind.getAdjacencyList();

	}
	/**
	* Returns the index of a given node.
	*
	* @param node the node to find
	* @return index the index
	* @throws IllegalArgumentException if node is null or does not exist
	*/
	int getNodeIndex(NetNode node) throws IllegalArgumentException{

		if(node == null) throw new IllegalArgumentException("Node cannot be null.");

		int index = 0;
		boolean exists = false;

		for(AdjacencyListHead head : nodesList){
			if(head.getNetNode().getId() == node.getId()){
				exists = true;
				break;
			}
			index++;
		}
		if(!exists) throw new IllegalArgumentException("Node does not exist.");

		return index;
	}
	/**
	* Returns the degree of a given node.
	*
	* @param node the node to find
	* @return nodeToFind.getAdjacencyList().size() the size of the adjacency list
	* @throws IllegalArgumentException if node is null or does not exist
	*/
	public int degree(NetNode node) throws IllegalArgumentException{

		if(node == null) throw new IllegalArgumentException("Node cannot be null.");

		AdjacencyListHead nodeToFind = new AdjacencyListHead(null);
		boolean exists = false;

		for(AdjacencyListHead head : nodesList){
			if(head.getNetNode().getId() == node.getId()){
				nodeToFind = head;
				exists = true;
				break;
			}
		}
		if(!exists) throw new IllegalArgumentException("Node does not exist.");

		return nodeToFind.getAdjacencyList().size();
	}
	/**
	* Returns the degree of the node with the highest degree.
	*
	* @return maxDegree the highest degree
	*/
	public int getGraphMaxDegree(){

		int maxDegree = 0;

		for(AdjacencyListHead head : nodesList){
			if(degree(head.getNetNode()) > maxDegree) maxDegree = degree(head.getNetNode());
		}

		return maxDegree;
	}
	/**
	* Returns the node at a given index.
	*
	* @param index the index of the node
	* @return nodesList.get(index).getNetNode() the node
	*/
	public NetNode nodeFromIndex(int index){

		return nodesList.get(index).getNetNode();
	}
	/**
	* Returns the string format of the graph.
	*
	* @return output the graph string
	*/
	public String printGraph(){

		String output = "";

		for(AdjacencyListHead a : nodesList){
			output += a.getNetNode().getName() + ":{";
			int n = a.getAdjacencyList().size();
			for(Adjacent b : a.getAdjacencyList()){
				double weight = b.getWeight();
				weight = Math.floor(weight * 10) / 10;
				output += "(" + b.getNeighbor().getName() + "," + weight + ")";
				n--;
				if(n != 0) output += ",";
			}
			output += "}\n";
		}

		return output;
	}

}
