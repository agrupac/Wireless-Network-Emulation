import java.util.*;
/**
* This class represents a network which contains graph.
*/
public class Network {
	/**
	* The graph.
	*/
	public NetGraph networkGraph;
	/**
	* A default constructor that creates a Network consisting of 1000 nodes located in an area of 200x200.
	*/
	public Network(){

		Network sampleNetwork = new Network(1000,200);
	}
	/**
	* A constructor that initializes a graph with a given number of nodes and side length.
	*
	* @param numOfNodes the number of nodes
	* @param side the side length
	*/
	public Network(int numOfNodes, double side){

		ArrayList<AdjacencyListHead> nodesList = new ArrayList<AdjacencyListHead>();

		//create nodes with random coordinates and generate netgraph
		for (int i = 0; i < numOfNodes; i++){
			double xcoordinate = side * Math.random();
			double ycoordinate = side * Math.random();
			int id = i;
			String name = "node " + i;
			NetNode node = new NetNode(id, name, xcoordinate, ycoordinate);
			nodesList.add(new AdjacencyListHead(node));
		}

		networkGraph = new NetGraph(nodesList);

		//iterate over all the node pairs in the graph and connect the nodes with a distance <= 20root2  with links
		double maxDist = 20 * Math.sqrt(2);
		for(int i = 0; i < numOfNodes; i++){
			for(int j = i; j < numOfNodes; j++){
				NetNode n1 = nodesList.get(i).getNetNode();
				NetNode n2 = nodesList.get(j).getNetNode();
				if(euclideanDistance(n1, n2) <= maxDist){
					networkGraph.addLink(n1, n2, euclideanDistance(n1, n2));
				}
			}
		}
	}
	/**
	* Finds the euclidean distance between two nodes in the graph.
	*
	* @param node1 the first node
	* @param node2 the second node
	* @return distance
	*/
	double euclideanDistance(NetNode node1, NetNode node2){

		double x1 = node1.getxcoordinate();
		double x2 = node2.getxcoordinate();
		double y1 = node1.getycoordinate();
		double y2 = node2.getycoordinate();

		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

	}
	/**
	* Finds the minimum spanning tree representation of the graph using Prim's algorithm.
	*
	* @return mst the tree
	*/
	public int[][] minSpanningTree(){

		int num = networkGraph.getNumNodes();
		double[] key = new double[num];
		int[] parent = new int[num];
		boolean[] visited = new boolean[num];
		Arrays.fill(key, Double.MAX_VALUE);

		//make sure node 0 is selected first
		key[0] = 0;

		//iterate for all nodes in graph
		for(int i = 0; i < num; i++){

			//find node with lowest key
			int currentIndex = -1;
			double min = Double.MAX_VALUE;
			for(int j = 0; j < num; j++){
				if(key[j] < min && visited[j] == false){
					min = key[j];
					currentIndex = j;
				}
			}
			//mark as visited
			visited[currentIndex] = true;

			AdjacencyListHead currentNode = networkGraph.nodesList.get(currentIndex);
			LinkedList<Adjacent> adjList = currentNode.getAdjacencyList();

			//update keys
			for(Adjacent adj : adjList){
				int adjIndex = networkGraph.getNodeIndex(adj.getNeighbor());
				//update neighbor's key if its weight is now less
				if(visited[adjIndex] == false && key[adjIndex] > adj.getWeight()){
					key[adjIndex] = adj.getWeight();
					parent[adjIndex] = currentIndex;
				}
			}
		}
		//generate adjacency matrix using parent array
		int[][] tree = new int[num][num];
		for(int k = 1; k < num; k++){
			tree[k][parent[k]] = 1;
			tree[parent[k]][k] = 1;
		}

		return tree;
	}
	/**
	* Finds the shortest path between two nodes using Dijkstra's algorithm.
	*
	* @param node1 the first node
	* @param node2 the second node
	* @return path the shortest path
	*/
	public ArrayList<NetNode> getShortestPath(NetNode node1, NetNode node2){

		int num = networkGraph.getNumNodes();
		double[] distance = new double[num];
		NetNode[] parent = new NetNode[num];
		boolean[] visited = new boolean[num];
		Arrays.fill(distance, Double.MAX_VALUE);
		Arrays.fill(parent, null);

		//make sure node1 is selected first
		distance[networkGraph.getNodeIndex(node1)] = 0;
		visited[0] = true;

		//priority queue which orders nodes based on their distance value
		PriorityQueue<AdjacencyListHead> pq = new PriorityQueue<>((a, b) -> Double.compare(distance[networkGraph.getNodeIndex(a.getNetNode())], distance[networkGraph.getNodeIndex(b.getNetNode())]));

		AdjacencyListHead start = networkGraph.nodesList.get(networkGraph.getNodeIndex(node1));
		AdjacencyListHead end = networkGraph.nodesList.get(networkGraph.getNodeIndex(node2));

		//begin with node1
		pq.add(start);

		while(!pq.isEmpty()){
			//get lowest distance node from queue
			AdjacencyListHead current = pq.poll();
			int currIndex = networkGraph.getNodeIndex(current.getNetNode());
			visited[currIndex] = true;
			//if retrieved node is destination, finish
			if(current.getNetNode().equals(end)) break;

			//iterate over neighbors of current node and update distance values as necessary
			for(Adjacent adj : current.getAdjacencyList()){

				int adjIndex = networkGraph.getNodeIndex(adj.getNeighbor());
				//if current distance plus the weight of current neighbor offers lower cost than the distance of the neighbor AND has not been visited, update and add to queue
				if(distance[currIndex] + adj.getWeight() < distance[adjIndex] && visited[adjIndex] == false){
					distance[adjIndex] = distance[currIndex] + adj.getWeight();
					parent[adjIndex] = current.getNetNode();
					AdjacencyListHead a = networkGraph.nodesList.get(adjIndex);
					pq.add(a);
				}
			}
		}

		ArrayList<NetNode> path = new ArrayList<>();
		NetNode n = end.getNetNode();
		//cycle through parents to create reverse path
		while(n != null) {
			path.add(n);
			n = parent[networkGraph.getNodeIndex(n)];
		}
		//reorder path
		Collections.reverse(path);

		return path;
	}

}
