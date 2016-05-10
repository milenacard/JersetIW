package co.edu.udea.iw.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.CiudadDAO;
import co.edu.udea.iw.dto.Ciudad;
import co.edu.udea.iw.exception.IWDaoException;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class CiudadDAOHibernateTest {
	
	@Autowired
	CiudadDAO ciudadDao;

	@Test
	public void testObtener() {
		
		List<Ciudad> ciudades = null;
		
		try{
			ciudades = ciudadDao.obtener();
			
			for(Ciudad ciudad : ciudades){
				System.out.println("Nombre ciudad: " + ciudad.getNombre());
			}
			
			assertTrue(true);
		}catch(IWDaoException e){
			fail(e.getMessage());
		}
	}

}
