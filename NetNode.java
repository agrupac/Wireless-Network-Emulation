/**
* This class represents a basic node and its attributes.
*/
public class NetNode{
	/**
	* The ID of the node.
	*/
	private int id;
	/**
	* The name of the node.
	*/
	private String name;
	/**
	* The x coordinate.
	*/
	private double xcoordinate;
	/**
	* The y coordinate.
	*/
	private double ycoordinate;
	/**
	* A constructor.
	*
	* @param id an ID
	* @param name a name
	* @param xcoordinate an x coordinate
	* @param ycoordinate a y coordinate
	*/
	public NetNode(int id, String name, double xcoordinate, double ycoordinate){
		this.id = id;
		this.name = name;
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
	}
	/**
	* A setter for ID.
	*
	* @param id an ID
	*/
	public void setId(int id){
		this.id = id;
	}
	/**
	* A setter for name.
	*
	* @param name a name
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* A setter for x coordinate.
	*
	* @param xcoordinate an x coordinate
	*/
	public void setxcoordinate(double xcoordinate){
		this.xcoordinate = xcoordinate;
	}
	/**
	* A setter for y coordinate.
	*
	* @param ycoordinate an y coordinate
	*/
	public void setycoordinate(double ycoordinate){
		this.ycoordinate = ycoordinate;
	}
	/**
	* A getter for id.
	*
	* @return id the ID
	*/
	public int getId(){
		return id;
	}
	/**
	* A getter for name.
	*
	* @return name the name
	*/
	public String getName(){
		return name;
	}
	/**
	* A getter for x coordinate.
	*
	* @return xcoordinate the x coordinate
	*/
	public double getxcoordinate(){
		return xcoordinate;
	}
	/**
	* A getter for y coordinate.
	*
	* @return ycoordinate the y coordinate
	*/
	public double getycoordinate(){
		return ycoordinate;
	}

}
