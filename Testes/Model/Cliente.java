package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class Cliente {

	@Test
	public void testCreateCliente() {
		Model.Entites.Cliente newCliente = new Model.Entites.Cliente(0, "Cristiano");
		String esperado = "Cristiano";
		assertEquals(esperado, newCliente.getNome());
		
	}

}
