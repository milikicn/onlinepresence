package net.onlinepresence.opos.core.hibernate;

import java.io.Serializable;
import java.util.List;

import net.onlinepresence.opos.core.persistance.PersistenceManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class HibernatePersistenceManager extends HibernateDaoSupport implements
		PersistenceManager<Session> {

	/**
	 * Retrieves <code>Session</code> that manages object that will be searched.
	 * 
	 * @return session
	 */
	public Session provideManager() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> get(Class<T> searchForType) {
		Session session = provideManager();
		Criteria criteria = session.createCriteria(searchForType);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> get(Class<T> searchForType, int firstResult,
			int maxResults) {
		Session session = provideManager();
		Criteria criteria = session.createCriteria(searchForType);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> T get(Serializable id, Class<T> searchForType) {
		Session session = provideManager();
		Criteria criteria = session.createCriteria(searchForType);
		criteria.add(Restrictions.idEq(id)).setMaxResults(1);
		return (T) criteria.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> get(Class<T> searchForType, T example) {
		Session session = provideManager();
		Criteria criteria = session.createCriteria(searchForType);
		criteria.add(Example.create(example));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> get(Class<T> searchForType, T example, int firstResult,
			int maxResults) {
		Session session = provideManager();
		Criteria criteria = session.createCriteria(searchForType);
		criteria.add(Example.create(example));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> get(T example) {
		return get((Class<T>) example.getClass(), example);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> get(T example, int firstResult, int maxResults) {
		return get((Class<T>) example.getClass(), example, firstResult,
				maxResults);
	}

	@Transactional
	public void save(Object object) {
		Session session = provideManager();
		session.saveOrUpdate(object);
	}

	@Transactional
	public void delete(Object object) {
		Session session = provideManager();
		session.delete(object);
	}

	@Transactional
	public void delete(Serializable id) {
		Object toBeDeleted = get(id);
		delete(toBeDeleted);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public void deleteAll(Class clazz) {
		Session session = provideManager();
		session.delete("from " + clazz.getName());
	}

	@Transactional
	public void flush() {
		Session session = provideManager();
		session.flush();
	}

	@Transactional
	public void update(Object o) {
		Session session = provideManager();
		session.update(o);
	}

	@Transactional
	public <T> T write(T input) {
		provideManager().saveOrUpdate(input);
		return input;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public List runQuery(String queryString){
		Session session = provideManager();		
		Query query = session.createQuery(queryString);
		List list = query.list();		
		return list;		
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public List runSQLQuery(String queryString){
		Session session = provideManager();		
		Query query = session.createSQLQuery(queryString);
		List list = query.list();		
		return list;		
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Transactional
//	public List getEagerMemberships(String queryString){
//		Session session = provideManager();		
//		Query query = session.createQuery(queryString);
//		List list = (List<Membership>) query.list();
//		
//		for (Object object : list) {
//			object.toString();
//		}
//		return list;		
//	}

}
