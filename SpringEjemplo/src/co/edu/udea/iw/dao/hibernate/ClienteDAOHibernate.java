package co.edu.udea.iw.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.ClienteDAO;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.exception.IWDaoException;

public class ClienteDAOHibernate extends HibernateDaoSupport implements ClienteDAO {

	@Override
	public Cliente insertar(Cliente cliente) throws IWDaoException {
		
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(cliente);
		}catch(HibernateException e){
			throw new IWDaoException(e);
		}
		return cliente;
	}

	@Override
	public Cliente modificar(Cliente cliente) throws IWDaoException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.update(cliente);
			
		}catch(HibernateException e){
			throw new IWDaoException(e);
		}
		
		return cliente;
	}

	
	@Override
	public List<Cliente> obtener() throws IWDaoException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			Criteria criteria = session.createCriteria(Cliente.class);
			
			clientes = criteria.list();
			
		}catch(HibernateException e){
			throw new IWDaoException(e);
		}
		
		return clientes;
	}

	@Override
	public Cliente obtener(String cedula) throws IWDaoException {
		Cliente cliente = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			cliente = (Cliente)session.get(Cliente.class, cedula);
			
		}catch(HibernateException e){
			throw new IWDaoException(e);
		}
		
		return cliente;
	}

}
