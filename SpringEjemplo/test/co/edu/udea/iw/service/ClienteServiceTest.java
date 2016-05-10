package co.edu.udea.iw.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.exception.IWDaoException;
import co.edu.udea.iw.exception.IWServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class ClienteServiceTest {
	
	@Autowired
	ClienteService clienteService;

	@Test
	public void testGuardaCliente() {
		
		try{
			clienteService.guardaCliente("2222222", "Cliente", "Prueba", "mal@correo.com", "elver");
		}catch(IWDaoException e){
			e.printStackTrace();
			fail(e.getMessage());
		}catch(IWServiceException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
