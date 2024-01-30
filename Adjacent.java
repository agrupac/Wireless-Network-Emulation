/**
* This class represents an adjacent node in an adjacency list.
*/
public class Adjacent {
	/**
	* The underlying NetNode.
	*/
	private NetNode neighbor;
	/**
	* The weight of the edge.
	*/
	private double weight;
	/**
	* A constructor.
	*
	* @param neighbor the neighbor
	* @param weight the weight
	*/
	public Adjacent(NetNode neighbor,double weight){
		this.neighbor = neighbor;
		this.weight = weight;
	}
	/**
	* A setter for neighbor.
	*
	* @param neighbor a neighbor
	*/
	public void setNeighbor(NetNode neighbor){
		this.neighbor = neighbor;
	}
	/**
	* A setter for weight.
	*
	* @param weight a weight
	*/
	public void setWeight(double weight){
		this.weight = weight;
	}
	/**
	* A getter for neighbor.
	*
	* @return neighbor the neighbor
	*/
	public NetNode getNeighbor(){
		return neighbor;
	}
	/**
	* A getter for weight.
	*
	* @return weight the weight
	*/
	public double getWeight(){
		return weight;
	}

}
