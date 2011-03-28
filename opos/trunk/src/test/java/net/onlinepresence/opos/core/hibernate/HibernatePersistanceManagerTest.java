package net.onlinepresence.opos.core.hibernate;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HibernatePersistanceManagerTest {
	
	 	private HibernatePersistenceManager daoImpl;

	    protected Session session;

	    protected Criteria criteria;
	    
	    protected Serializable id;
	    
	    protected SessionFactory sessionFactory;
	    
	    protected HibernatePersistenceManager createTestedObject(){
	        return new HibernatePersistenceManager();
	    }
	    
	    @BeforeMethod
	    public void setUp() throws Exception {
	        daoImpl = createTestedObject();
	        session = createStrictMock(Session.class);
	        sessionFactory = createStrictMock(SessionFactory.class);
	        daoImpl.setSessionFactory(sessionFactory);
	        criteria = createStrictMock(Criteria.class);
	        id = new Long(1);
	    }    
	    
	    @SuppressWarnings("unchecked")
		@Test()
	    public void testGetByType(){
	    	expect(sessionFactory.getCurrentSession()).andReturn(session);
	    	expect(session.createCriteria(Object.class)).andReturn(criteria);
	        List queryResult = new LinkedList();
	        expect(criteria.list()).andReturn(queryResult);
	        replay(sessionFactory);
	        replay(session);
	        replay(criteria);
	        assertEquals("Search for all objects should return List queryResult.", //$NON-NLS-1$
	                queryResult, daoImpl.get(Object.class));
	        verify(sessionFactory);
	        verify(session);
	        verify(criteria);
	    }

	    
	    @Test()
	    public void testGetByTypeThrownHibernateException(){
	    	expect(sessionFactory.getCurrentSession()).andReturn(session);
	    	expect(session.createCriteria(Object.class)).andReturn(criteria);
	    	expect(criteria.list()).andThrow(new HibernateException("Expected"));
	    	replay(sessionFactory);
	    	replay(session);
	        replay(criteria);
	    	try{
	            daoImpl.get(Object.class);
	            fail("HibernateException should have been thrown.");
	        }catch(HibernateException expected){
	            assertTrue("Expected exception has been thrown.", true);
	        }
	        verify(session);
	        verify(sessionFactory);
	        verify(criteria);
	    }

	 
	    @SuppressWarnings("unchecked")
		@Test()
	    public void testGetByTypeID(){
	    	expect(sessionFactory.getCurrentSession()).andReturn(session);
	    	expect(session.createCriteria(Object.class)).andReturn(criteria);
	        List queryResult = new LinkedList();
	        Object resultObject = new Object();
	        queryResult.add(resultObject);
	        expect(criteria.add(isA(Criterion.class))).andReturn(criteria);
	        expect(criteria.setMaxResults(eq(1))).andReturn(criteria);
	        expect(criteria.list()).andReturn(queryResult);
	        replay(session);
	        replay(sessionFactory);
	        replay(criteria);
	        assertEquals("Search should return specific object.",
	                resultObject, daoImpl.get(id, Object.class));
	        verify(session);
	        verify(sessionFactory);
	        verify(criteria);
	    }

	    
	    @SuppressWarnings("unchecked")
		@Test()
	    public void testGetByTypeIDThrownHibernateException(){
	    	expect(sessionFactory.getCurrentSession()).andReturn(session);
	        expect(session.createCriteria(Object.class)).andReturn(criteria);
	        List queryResult = new LinkedList();
	        Object resultObject = new Object();
	        queryResult.add(resultObject);
	        expect(criteria.add(isA(Criterion.class))).andReturn(criteria);
	        expect(criteria.setMaxResults(eq(1))).andReturn(criteria);
	        expect(criteria.list()).andThrow(new HibernateException("Expected"));
	        replay(session);
	        replay(sessionFactory);
	        replay(criteria);
	        try{
	            daoImpl.get(id, Object.class);
	            fail("HibernateException should have been thrown."); //$NON-NLS-1$
	        }catch(HibernateException expected){
	            assertTrue("Expected exception has been thrown.", true); //$NON-NLS-1$
	        }
	        verify(session);
	        verify(sessionFactory);
	        verify(criteria);
	    }

	    
	    @SuppressWarnings("unchecked")
		@Test()
	    public void testGetByTypeExample(){
	    	expect(sessionFactory.getCurrentSession()).andReturn(session);
	    	expect(session.createCriteria(Object.class)).andReturn(criteria);
	        List queryResult = new LinkedList();
	        Object resultObject = new Object();
	        queryResult.add(resultObject);
	        expect(criteria.add(isA(Criterion.class))).andReturn(criteria);
	        expect(criteria.list()).andReturn(queryResult);
	        replay(session);
	        replay(sessionFactory);
	        replay(criteria);
	        assertEquals("Search for all objects should return List queryResult.",
	                queryResult, daoImpl.get(Object.class, new Object()));
	        verify(session);
	        verify(sessionFactory);
	        verify(criteria);
	    }

	    
	    @SuppressWarnings("unchecked")
		@Test()
	    public void testGetByTypeExampleThrownHibernateException(){
	    	expect(sessionFactory.getCurrentSession()).andReturn(session);
	    	expect(session.createCriteria(Object.class)).andReturn(criteria);
	        List queryResult = new LinkedList();
	        Object resultObject = new Object();
	        queryResult.add(resultObject);
	        expect(criteria.add(isA(Criterion.class))).andReturn(criteria);
	        expect(criteria.list()).andThrow(new HibernateException("Expected"));;
	        replay(session);
	        replay(sessionFactory);
	        replay(criteria);
	        try{
	            daoImpl.get(Object.class, new Object());
	            fail("HibernateException should have been thrown."); //$NON-NLS-1$
	        }catch(HibernateException expected){
	            assertTrue("Expected exception has been thrown.", true); //$NON-NLS-1$
	        }
	        verify(session);
	        verify(sessionFactory);
	        verify(criteria);
	    }

	    
	    @Test()
	    public void testSave(){
	    	expect(sessionFactory.getCurrentSession()).andReturn(session);
	    	Serializable arg = createNiceMock(Serializable.class);
	        session.saveOrUpdate(arg);
	        replay(session);
	        replay(sessionFactory);
	        daoImpl.save(arg);
	        assertTrue("Saving has been executed sucessfully.", true); //$NON-NLS-1$
	        verify(session);
	        verify(sessionFactory);
	    }


}
