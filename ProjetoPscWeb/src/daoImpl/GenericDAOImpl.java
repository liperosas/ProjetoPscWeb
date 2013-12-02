/**
 * 
 */
package daoImpl;

import javax.persistence.EntityManager;

import dao.IGenericDAO;


public abstract class GenericDAOImpl<T> implements IGenericDAO<T> {

	private EntityManager manager;

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public EntityManager getManager() {
		return this.manager;
	}

}
