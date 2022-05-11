package york.eecs.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import york.eecs.source.UndirectedGraph;
import york.eecs.source.UndirectedGraphAlgorithms;

import york.eecs.source.VertexExistsException;

public class StudentTest {

	 /**
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraph.
	  * 
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraphAlgorithms.
	  */
	UndirectedGraphAlgorithms<String> uga;
	@Before
	public void setUp() throws Exception {
		uga = new UndirectedGraphAlgorithms<>();
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testGetSize() {
		UndirectedGraph<String> graph1 = new UndirectedGraph<>();;
		try {
			graph1.addVertex("One");
			graph1.addVertex("Two");
			graph1.addVertex("Three");
			graph1.addVertex("Four");
			graph1.addVertex("Five");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = graph1.getSize();
		int expectedSize = 5;
		assertEquals(size, expectedSize);
	}
	
	

	@Test
	public void testGetVertices() {
		UndirectedGraph<String> graph2 = new UndirectedGraph<>();;
		try {
			graph2.addVertex("b");
			graph2.addVertex("a");
			graph2.addVertex("c");
			graph2.addVertex("e");
			graph2.addVertex("d");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> vertices = (ArrayList<String>) graph2.getVertices();
		vertices.sort((s1, s2) -> s1.compareTo(s2));
		List<String> expectedVertices = new ArrayList<>();
		expectedVertices.add("a");
		expectedVertices.add("b");
		expectedVertices.add("c");
		expectedVertices.add("d");
		expectedVertices.add("e");
		assertEquals(vertices, expectedVertices);
	}
	
	//get adjacent in descending order
	@Test
	public void testGetAdjacent() {
		UndirectedGraph<String> graph3 = new UndirectedGraph<>();;
		try {
			graph3.addVertex("A");
			graph3.addVertex("B");
			graph3.addVertex("C");
			graph3.addVertex("D");
			graph3.addVertex("E");
			graph3.addEdge("A", "B");
			graph3.addEdge("A", "C");
			graph3.addEdge("A", "D");
			graph3.addEdge("A", "E");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> aAdjacent = (ArrayList<String>) graph3.getAdjacent("A");
		aAdjacent.sort((s1, s2) -> s2.compareTo(s1));
		List<String> expectedAdjacent = new ArrayList<>();
		expectedAdjacent.add("E");
		expectedAdjacent.add("D");
		expectedAdjacent.add("C");
		expectedAdjacent.add("B");
		assertEquals(aAdjacent, expectedAdjacent);
	}
	//vertice has no adjacent 
	@Test
	public void testGetNoAdjacent() {
		UndirectedGraph<String> graph4 = new UndirectedGraph<>();;
		try {
			graph4.addVertex("A");
			graph4.addVertex("B");
			graph4.addVertex("C");
			graph4.addVertex("D");
			graph4.addVertex("E");
			graph4.addEdge("A", "B");
			graph4.addEdge("A", "C");
			graph4.addEdge("A", "D");
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> aAdjacent = (ArrayList<String>) graph4.getAdjacent("E");
		aAdjacent.sort((s1, s2) -> s1.compareTo(s2));
		List<String> expectedAdjacent = new ArrayList<>();
		
		assertEquals(aAdjacent, expectedAdjacent);
	}
	
	@Test
	public void testToString() {
		UndirectedGraph<String> graph5 = new UndirectedGraph<>();;
		try {
			graph5.addVertex("A");
			graph5.addVertex("B");
			graph5.addVertex("C");
			graph5.addVertex("D");
			graph5.addVertex("E");
			graph5.addEdge("A", "B");
			graph5.addEdge("B", "A");
			graph5.addEdge("A", "C");
			graph5.addEdge("A", "D");
			graph5.addEdge("B", "A");
			graph5.addEdge("B", "C");
			graph5.addEdge("B", "E");
			graph5.addEdge("E", "B");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String toSt = graph5.toString();
		String expected = "Graph:\nVertex: A & Adjacent Vertices: [B, C, D]\nVertex: B & Adjacent Vertices: [A, C, E]\nVertex: C & Adjacent Vertices: [A, B]\nVertex: D & Adjacent Vertices: [A]\nVertex: E & Adjacent Vertices: [B]\n";
	    assertEquals(toSt, expected);
	}
	
	
	@Test
	public void testBFS() {
		UndirectedGraph<String> graph6 = new UndirectedGraph<>();;
		try {
			graph6.addVertex("1");
			graph6.addVertex("2");
			graph6.addVertex("3");
			graph6.addVertex("4");
			graph6.addEdge("1", "2");
			graph6.addEdge("1", "3");
			graph6.addEdge("1", "4");
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(graph6, "1", "4");
		List<String> expected = new ArrayList<>();
		expected.add("1");
		expected.add("4");
		
		assertEquals(result, expected);
	}
	
	@Test
	public void testBFSTwo() {
		UndirectedGraph<String> graph7 = new UndirectedGraph<>();;
		try {
			graph7.addVertex("1");
			graph7.addVertex("2");
			graph7.addVertex("3");
			graph7.addVertex("4");
			graph7.addVertex("5");
			graph7.addVertex("6");
			graph7.addEdge("1", "2");
			graph7.addEdge("1", "3");
			graph7.addEdge("1", "4");
			graph7.addEdge("4", "5");
			graph7.addEdge("4", "6");
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(graph7, "6", "2");
		List<String> expected = new ArrayList<>();
		expected.add("6");
		expected.add("4");
		expected.add("1");
		expected.add("2");
		
		assertEquals(result, expected);
	}
	
	
	@Test
	public void testBFSThree() {
		UndirectedGraph<String> graph8 = new UndirectedGraph<>();;
		try {
			graph8.addVertex("1");
			graph8.addVertex("2");
			graph8.addVertex("3");
			graph8.addVertex("4");
			graph8.addVertex("5");
			graph8.addVertex("6");
			graph8.addEdge("1", "2");
			graph8.addEdge("1", "3");
			graph8.addEdge("1", "4");
			graph8.addEdge("4", "5");
			graph8.addEdge("4", "6");
			graph8.addEdge("3", "6");
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(graph8, "6", "1");
		List<String> expected = new ArrayList<>();
		expected.add("6");
		expected.add("4");
		expected.add("1");
		
		
		assertEquals(result, expected);
	}
	
	@Test
	public void testBFSFour() {
		UndirectedGraph<String> graph9 = new UndirectedGraph<>();;
		try {
			graph9.addVertex("1");
			graph9.addVertex("2");
			graph9.addVertex("3");
			graph9.addVertex("4");
			graph9.addVertex("5");
			graph9.addVertex("6");
			graph9.addEdge("1", "2");
			graph9.addEdge("1", "3");
			graph9.addEdge("1", "4");
			graph9.addEdge("4", "5");
			
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(graph9, "6", "1");
		List<String> expected = new ArrayList<>();

		assertEquals(result, expected);
	}
	
	@Test
	public void testBFSFive() {
		UndirectedGraph<String> graph10 = new UndirectedGraph<>();;
		try {
			graph10.addVertex("1");
			graph10.addVertex("2");
			graph10.addVertex("3");
			graph10.addVertex("4");
			graph10.addVertex("5");
			graph10.addVertex("6");
			graph10.addVertex("7");
			graph10.addEdge("1", "2");
			graph10.addEdge("1", "3");
			graph10.addEdge("6", "4");
			graph10.addEdge("6", "5");
			graph10.addEdge("6", "7");
			
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(graph10, "6", "2");
		
		List<String> expected = new ArrayList<>();
		
		
		assertEquals(result, expected);
		
	}
	
	@Test
	public void testBFSSix() {
		UndirectedGraph<String> graph10 = new UndirectedGraph<>();;
		try {
			graph10.addVertex("1");
			graph10.addVertex("2");
			graph10.addVertex("3");
			graph10.addVertex("4");
			graph10.addVertex("5");
			graph10.addVertex("6");
			graph10.addVertex("7");
			graph10.addEdge("1", "2");
			graph10.addEdge("1", "3");
			graph10.addEdge("6", "4");
			graph10.addEdge("6", "5");
			graph10.addEdge("6", "7");
			
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(graph10, "7", "4");
		
		List<String> expected = new ArrayList<>();
		expected.add("7");
		expected.add("6");
		expected.add("4");
		
		assertEquals(result, expected);
		
	}
	@Test
	public void testBFSSeven() {
		UndirectedGraph<String> graph10 = new UndirectedGraph<>();;
		try {
			graph10.addVertex("1");
			graph10.addVertex("2");
			graph10.addVertex("3");
			graph10.addVertex("4");
			graph10.addVertex("5");
			graph10.addVertex("6");
			graph10.addVertex("7");
			graph10.addVertex("8");
			graph10.addVertex("9");
			graph10.addEdge("1", "2");
			graph10.addEdge("1", "3");
			graph10.addEdge("6", "4");
			graph10.addEdge("6", "5");
			graph10.addEdge("6", "7");
			graph10.addEdge("6", "4");
			//graph10.addEdge("6", "7");
			graph10.addEdge("4", "8");
			graph10.addEdge("8", "9");
			//graph10.addEdge("6", "7");
			graph10.addEdge("9", "7");
			
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(graph10, "6", "9");
		
		List<String> expected = new ArrayList<>();
		
		expected.add("6");
		expected.add("4");
		expected.add("8");
		expected.add("9");
		
		assertEquals(result, expected);
		
	}



}
