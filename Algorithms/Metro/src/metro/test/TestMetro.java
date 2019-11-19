package metro.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniupo.graphLib.UndirectedGraph;
import metro.Metro;

public class TestMetro {

	 UndirectedGraph grafo;
	 Metro metro;
	
	@Before
	public void test() {
		grafo = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
		metro = new Metro(grafo);
		assertNotNull(metro);
	}
	
	//***********test numero fermate**************************
	@Test
	public void test1Distanza() {
		assertEquals("Mi aspetto che la distanza sia 2",2,metro.numeroFermate(0, 3));
	}
	
	@Test
	public void test2Distanza() {
		grafo = new UndirectedGraph("8;0 2;0 1;2 3;1 3;1 4;3 5;2 5;3 6;5 6;4 6;4 7");
		metro = new Metro(grafo);
		assertEquals("Mi aspetto che la distanza sia 2",3,metro.numeroFermate(0, 7));
		assertEquals("Mi aspetto che la distanza sia 2",2,metro.numeroFermate(0, 5));
		assertEquals("Mi aspetto che la distanza sia 2",3,metro.numeroFermate(3, 7));
	}
	
	
	//***********test sulle fermate**************************
	@Test
	public void test3Distanza() {
		grafo = new UndirectedGraph("8;0 2;0 1;2 3;1 3;1 4;3 5;2 5;3 6;5 6;4 6;4 7");
		metro = new Metro(grafo);
		assertEquals("Mi aspetto che le fermate siano 0 2 5","[0, 2, 5]",metro.fermate(0, 5).toString());
		assertEquals("Mi aspetto che le fermate siano 0 1 4 7","[0, 1, 4, 7]",metro.fermate(0, 7).toString());
		assertEquals("Mi aspetto che le fermate siano 3 1 4 7","[3, 1, 4, 7]",metro.fermate(3, 7).toString());
	}
	
	@Test
	public void test4Distanza() {
		grafo = new UndirectedGraph("10;0 2;0 1;2 3;1 3;1 4;3 5;2 5;3 6;5 6;4 6;4 7;8 9");
		metro = new Metro(grafo);
		assertEquals("Mi aspetto che non sia raggiungibile",null,metro.fermate(0, 8));
	}
	
	
	//***********test sulle eccezioni**************************
	@Test (expected = IllegalArgumentException.class)
	public void test1Eccezione() {
		grafo = new UndirectedGraph("10;0 2;0 1;2 3;1 3;1 4;3 5;2 5;3 6;5 6;4 6;4 7;8 9");
		metro = new Metro(grafo);
		assertEquals("Mi aspetto che non sia raggiungibile",null,metro.fermate(-1, 8));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test2Eccezione() {
		grafo = new UndirectedGraph("10;0 2;0 1;2 3;1 3;1 4;3 5;2 5;3 6;5 6;4 6;4 7;8 9");
		metro = new Metro(grafo);
		assertEquals("Mi aspetto che non sia raggiungibile",null,metro.fermate(0, -1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test3Eccezione() {
		grafo = new UndirectedGraph("10;0 2;0 1;2 3;1 3;1 4;3 5;2 5;3 6;5 6;4 6;4 7;8 9");
		metro = new Metro(grafo);
		assertEquals("Mi aspetto che non sia raggiungibile",null,metro.fermate(12, 24));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test4Eccezione() {
		grafo = new UndirectedGraph("10;0 2;0 1;2 3;1 3;1 4;3 5;2 5;3 6;5 6;4 6;4 7;8 9");
		metro = new Metro(grafo);
		assertEquals("Mi aspetto che non sia raggiungibile",null,metro.fermate(88, -2));
	}

}
