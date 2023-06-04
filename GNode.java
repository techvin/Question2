package question2;

import java.util.ArrayList;
import java.util.List;

public interface GNode {

	public String getName();
	public void setName(String name);
	public GNode[] getChildren();
	public List<List<Integer>> getAdjacencyList();
	public void printAdjacencyList();
	public ArrayList<Integer> getNoChildVerticesList();
	@SuppressWarnings("rawtypes")
	public ArrayList paths(GNode node);
}
