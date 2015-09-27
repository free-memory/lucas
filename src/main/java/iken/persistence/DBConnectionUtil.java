package iken.persistence;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by ken on 15/8/26.
 */
public class DBConnectionUtil {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static List getQuery(String queryString, int pageNo, int pageSize) throws HibernateException {
        final Session session = getSession();
        List lstObject;
        try {
            Query query = session.createQuery(queryString);
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            lstObject = query.list();
            System.out.println("querying result count = " + lstObject.size());

        } finally {
            session.close();
        }
        return lstObject;
    }

    public static List getQuery(String queryString) throws HibernateException {
        final Session session = getSession();
        int pageNo = 1;
        int pageSize = 100;

        List lstObject;
        try {
            Query query = session.createQuery(queryString);
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            lstObject = query.list();
            System.out.println("querying result count = " + lstObject.size());

        } finally {
            session.close();
        }
        return lstObject;
    }

    public static List getData(String criteria) throws HibernateException {
        final Session session = getSession();
        List lstObject;
        try {
            lstObject = session.createCriteria(criteria).list();
            System.out.println("querying result count = " + lstObject.size());
        } finally {
            session.close();
        }
        return lstObject;
    }

    public static boolean updateData(Object obj) throws HibernateException {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        boolean status = false;
        try {
            session.update(obj);
            transaction.commit();
            status = true;
            System.out.println("Saved a object = " + obj);
        } finally {
            session.close();
        }
        return status;
    }

    public static boolean addData(Object obj) throws HibernateException {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        boolean status = false;
        try {
            session.save(obj);
            transaction.commit();
            status = true;
            System.out.println("Saved a object = " + obj);
        } finally {
            session.close();
        }
        return status;
    }

    public static boolean deleteData(String className, int id) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println("Delete record = " + id);
            session.delete(session.get(className, id));
            transaction.commit();

            return true;
        } finally {
            session.close();
        }
    }

    public static Object getAObject(String className, int id) {
        final Session session = getSession();
        try {
            System.out.println("Get record = " + id);
            return session.get(className, id);

        } finally {
            session.close();
        }
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entity...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                for (Object o : getQuery("from " + entityName)) {
                    System.out.println("  " + o);
                }
//                for (Object o : getData(entityName)) {
//                    System.out.println("  " + o);
//                }
            }

        } finally {
            session.close();
        }
    }
}
