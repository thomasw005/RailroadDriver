/* Thomas Wilson
   Dr. Steinberg
   COP3503 Spring 2025
   Programming Assignment 5
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Railroad
{
    // Global variables.
    private DisjointSet ds = new DisjointSet();
    private RailroadEdge[] edges;

    // Railroad constructor.
    public Railroad(int numEdges, String fileName) throws IOException
    {
        this.edges = new RailroadEdge[numEdges];
        read(numEdges, fileName);
    }

    public String buildRailroad()
    {
        // Initialize tracker variables.
        int totalCost = 0;
        StringBuilder finalResult = new StringBuilder();

        // Sort array of edges by cost.
        Arrays.sort(edges, Comparator.comparingInt(RailroadEdge::getCost));

        // Loop through edges and ensure no cycles are made.
        for (RailroadEdge edge : edges)
        {
            if (!ds.connected(edge.getSource(), edge.getDest()))
            {
                // Update total cost if a safe edge is found.
                totalCost += edge.getCost();

                // Add edge to final string.
                if (edge.getSource().compareTo(edge.getDest()) < 0)
                {
                    finalResult.append(edge.getSource()).append("---").append(edge.getDest()).append("\t$").append(edge.getCost()).append("\n");
                } else
                {
                    finalResult.append(edge.getDest()).append("---").append(edge.getSource()).append("\t$").append(edge.getCost()).append("\n");
                }

                // Union the two sets containing source and destination.
                ds.union(edge.getSource(), edge.getDest());
            }
        }

        // Return final string.
        finalResult.append("The cost of the railroad is $").append(totalCost).append(".");
        return finalResult.toString();
    }

    // Read in list of edges from text file.
    public void read(int numEdges, String fileName) throws IOException
    {
        BufferedReader childReader = new BufferedReader(new FileReader(fileName));

        // Read each line of file and add to array of edges.
        for (int i = 0; i < numEdges; i++)
        {
            String line = childReader.readLine();
            String[] splitLine = line.split(" ");

            // Build disjoint set
            ds.makeSet(splitLine[0]);
            ds.makeSet(splitLine[1]);

            // Add list of edges.
            RailroadEdge newEdge = new RailroadEdge(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]));
            edges[i] = newEdge;
        }

        childReader.close();
    }

    // Class that defines each edge.
    private class RailroadEdge
    {
        private String source;
        private String dest;
        private int cost;

        public RailroadEdge(String source, String dest, int cost)
        {
            this.source = source;
            this.dest = dest;
            this.cost = cost;
        }

        public String getSource()
        {
            return source;
        }

        public String getDest()
        {
            return dest;
        }

        public int getCost()
        {
            return cost;
        }
    }

    // DisjointSet helper class.
    public class DisjointSet
    {
        private Map<String, String> parent;

        public DisjointSet()
        {
            parent = new HashMap<>();
        }

        // Creates a new set for an element if it doesn't exist
        public void makeSet(String x)
        {
            if (!parent.containsKey(x))
            {
                parent.put(x, x);
            }
        }

        // Find the representative of the set containing the element
        public String find(String x)
        {
            if (!parent.containsKey(x))
            {
                return null;
            }
            while (!x.equals(parent.get(x)))
            {
                x = parent.get(x);
            }
            return x;
        }

        // Merge the sets containing the two elements
        public void union(String x, String y)
        {
            String rootX = find(x);
            String rootY = find(y);

            if (rootX == null || rootY == null) return;
            if (!rootX.equals(rootY))
            {
                parent.put(rootX, rootY);
            }
        }

        // Checks to see if two elements are in same set.
        public boolean connected(String x, String y)
        {
            return find(x) != null && find(x).equals(find(y));
        }
    }
}
