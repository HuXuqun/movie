package lab.io.rush.datanucleus.dao.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.io.rush.datanucleus.dao.DataNucleusDao;

import javax.jdo.*;
import javax.jdo.annotations.PrimaryKey;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by cpt72 on 2016/12/11.
 */

public class DataNucleusDaoImpl implements DataNucleusDao {



	private PersistenceManagerFactory persistenceManagerFactory;
	
	
	

	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}

	public void setPersistenceManagerFactory(PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;
	}

	public <T> T selectByPrimaryKey(Class<T> var, Object key) {
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();

        T result = null;
        try {
            result = pm.getObjectById(var, key);
        } catch (JDOObjectNotFoundException e) {
           System.out.println("DataNucleusDaoImpl.selectByPrimaryKey key=" + key + " \n" + e.getMessage());
        } catch (Exception e) {
        	 System.out.println("DataNucleusDaoImpl.selectByPrimaryKey error:key=" + key + " \n" + e.getMessage());
        } finally {
            pm.close();
        }
        return result;
	}

	public <T> List<T> selectByQuery(Class<T> var, String query) {
		PersistenceManager pm =persistenceManagerFactory.getPersistenceManager();
        List<T> list = null;
        try {
            Query q = pm.newQuery(var, query);
            list = (List<T>) q.execute();
        } catch (JDOObjectNotFoundException e) {
           System.out.println("DataNucleusDaoImpl.selectByQuery query=" + query + " \n" + e.getMessage());
        } catch (Exception e) {
           System.out.println("DataNucleusDaoImpl.selectByQuery error:query=" + query + " \n" + e.getMessage());
        } finally {
            pm.close();
        }
        return list;
	}

	public <T> Object insert(T object) {
        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        Object id = null;
        try {
            tx.begin();
            pm.makePersistent(object);
            tx.commit();
            id = getObjectId(object);
        } catch (Exception e) {
            System.out.println("DataNucleusDaoImpl.insert error:object=" + object + " \n" + e.getMessage()+e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return id;
	}


	public <T> boolean update(T object) {
		  boolean flag = false;
	        PersistenceManager pm =persistenceManagerFactory.getPersistenceManager();
	        Transaction tx = pm.currentTransaction();
	        try {
	            tx.begin();
	            Object temp = pm.getObjectById((Class<T>) object.getClass(), getObjectId(object));
	            if (temp == null)
	                return false;
	            BeanUtils.copyProperties(object, temp);
	            tx.commit();
	            flag = true;
	        } catch (JDOObjectNotFoundException e) {
	        	System.out.println("DataNucleusDaoImpl.update object=" + object + " \n" + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("DataNucleusDaoImpl.update error:object=" + object + " \n" + e.getMessage()+ e);
	        } finally {
	            if (tx.isActive()) {
	                tx.rollback();
	            }
	            pm.close();
	        }
	        return flag;
	}

	public <T> boolean deleteByPrimaryKey(Class<T> var, Object key) {
		boolean flag = false;
        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Object temp = pm.getObjectById(var, key);
            if (temp == null)
                return false;
            pm.deletePersistent(temp);
            tx.commit();
            flag = true;
        } catch (JDOObjectNotFoundException e) {
        	System.out.println("DataNucleusDaoImpl.deleteByPrimaryKey key=" + key + " \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("DataNucleusDaoImpl.deleteByPrimaryKey error:key=" + key + " \n" + e.getMessage()+ e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return flag;
	}
	/**
     * 获取对象主键值
     *
     * @param object 数据对象
     * @return 主键对象
     * @throws IllegalAccessException 非法访问异常
     */
    private Object getObjectId(Object object) throws IllegalAccessException {

        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
            if (annotation != null) {
                field.setAccessible(true);
                return field.get(object);
            }
        }
        return null;
    }
}

