package allacciamentoidrico.test;

import static org.junit.Assert.*;

import org.junit.Test;

import allacciamentoidrico.AllacciamentoIdrico;
import it.uniupo.graphLib.UndirectedGraph;

public class TestAllacciamentoIdrico {

AllacciamentoIdrico allId;
	
	@Test
	public void testCreate() {
		UndirectedGraph mappa = new UndirectedGraph("4;1 2 2;1 0 5;2 3 3; 0 3 1");
		int[][] costoScavo = {{0,6,-1,8},{6,0,7,-1},{-1,7,0,10},{8,-1,10,0}};
		int costoTubo = 3;
		int puntoAll = 1;
		allId = new AllacciamentoIdrico(mappa, costoScavo, costoTubo,puntoAll);
		assertNotNull(allId);
	}

	@Test
	public void test01() {
		UndirectedGraph mappa = new UndirectedGraph("4;1 2 2;1 0 5;2 3 3; 0 3 1");
		int[][] costoScavo = {{0,6,-1,8},{6,0,7,-1},{-1,7,0,10},{8,-1,10,0}};
		int costoTubo = 3;
		int puntoAll = 1;
		allId = new AllacciamentoIdrico(mappa, costoScavo, costoTubo,puntoAll);
		assertTrue(allId.progettoProprietari().hasEdge(2,3));
		assertTrue(!allId.progettoProprietari().hasEdge(0,3));
		int[] distanze = allId.getDistanzeDijkstra();
		assertEquals("Mi aspetto che la distanza minima sia 0",0,distanze[1]);
		assertEquals("Mi aspetto che la distanza minima sia 5",5,distanze[0]);
		assertEquals("Mi aspetto che la distanza minima sia 2",2,distanze[2]);
		assertEquals("Mi aspetto che la distanza minima sia 5",5,distanze[3]);
		assertEquals("Mi aspetto che il costo sia 6",6,allId.getCostoProprietario(2));
		assertEquals("Mi aspetto che il costo sia 15",15,allId.getCostoProprietario(0));
		assertEquals("Mi aspetto che il costo sia 15",15,allId.getCostoProprietario(3));
		assertEquals("Mi aspetto che il costo sia 0",0,allId.getCostoProprietario(1));
		assertTrue(allId.progettoComune().hasEdge(0,3));
		assertTrue(!allId.progettoComune().hasEdge(2,3));
		assertEquals("Mi aspetto che il costo del comune sia 21",21,allId.getCostoComune());
		assertEquals(2,allId.speseExtraComune());
		assertEquals(3,allId.speseExtraProprietario(3));
	}

}
