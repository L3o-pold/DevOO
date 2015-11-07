package modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

		  private final List<Intersection> nodes;
		  private final List<Troncon> Troncons;
		  private Set<Intersection> settledNodes;
		  private Set<Intersection> unSettledNodes;
		  private Map<Intersection, Intersection> predecessors;
		  private Map<Intersection, Double> distance;

		  public DijkstraAlgorithm(Plan plan) {
		    // create a copy of the array so that we can operate on this array
		    this.nodes = new ArrayList<Intersection>(plan.getIntersections());
		    this.Troncons = new ArrayList<Troncon>(plan.getTroncons());
		  }

		  public void execute(Intersection source) {
		    settledNodes = new HashSet<Intersection>();
		    unSettledNodes = new HashSet<Intersection>();
		    distance = new HashMap<Intersection, Double>();
		    predecessors = new HashMap<Intersection, Intersection>();
		    distance.put(source, 0.0);
		    unSettledNodes.add(source);
		    while (unSettledNodes.size() > 0) {
		      Intersection node = getMinimum(unSettledNodes);
		      settledNodes.add(node);
		      unSettledNodes.remove(node);
		      findMinimalDistances(node);
		    }
		  }

		  private void findMinimalDistances(Intersection node) {
		    List<Intersection> adjacentNodes = getNeighbors(node);
		    for (Intersection target : adjacentNodes) {
		      if (getShortestDistance(target) > getShortestDistance(node)
		          + getDistance(node, target)) {
		        distance.put(target, getShortestDistance(node)
		            + getDistance(node, target));
		        predecessors.put(target, node);
		        unSettledNodes.add(target);
		      }
		    }

		  }

		  private double getDistance(Intersection node, Intersection target) {
		    for (Troncon Troncon : Troncons) {
		      if (Troncon.getDepart().equals(node)
		          && Troncon.getArrivee().equals(target)) {
		        return Troncon.getDuree();
		      }
		    }
		    throw new RuntimeException("Should not happen");
		  }

		  private List<Intersection> getNeighbors(Intersection node) {
		    List<Intersection> neighbors = new ArrayList<Intersection>();
		    for (Troncon Troncon : Troncons) {
		      if (Troncon.getDepart().equals(node)
		          && !isSettled(Troncon.getArrivee())) {
		        neighbors.add(Troncon.getArrivee());
		      }
		    }
		    return neighbors;
		  }

		  private Intersection getMinimum(Set<Intersection> Intersectiones) {
		    Intersection minimum = null;
		    for (Intersection Intersection : Intersectiones) {
		      if (minimum == null) {
		        minimum = Intersection;
		      } else {
		        if (getShortestDistance(Intersection) < getShortestDistance(minimum)) {
		          minimum = Intersection;
		        }
		      }
		    }
		    return minimum;
		  }

		  private boolean isSettled(Intersection Intersection) {
		    return settledNodes.contains(Intersection);
		  }

		  private double getShortestDistance(Intersection destination) {
		    Double d = distance.get(destination);
		    if (d == null) {
		      return Integer.MAX_VALUE;
		    } else {
		      return d;
		    }
		  }

		  /*
		   * This method returns the path from the source to the selected target and
		   * NULL if no path exists
		   */
		  public LinkedList<Intersection> getPath(Intersection target) {
		    LinkedList<Intersection> path = new LinkedList<Intersection>();
		    Intersection step = target;
		    // check if a path exists
		    if (predecessors.get(step) == null) {
		      return null;
		    }
		    path.add(step);
		    while (predecessors.get(step) != null) {
		      step = predecessors.get(step);
		      path.add(step);
		    }
		    // Put it into the correct order
		    Collections.reverse(path);
		    return path;
		  }

		 

}
