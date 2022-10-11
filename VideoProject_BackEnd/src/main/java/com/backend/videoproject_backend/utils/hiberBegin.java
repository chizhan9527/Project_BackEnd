package com.backend.videoproject_backend.utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.Session;
public class hiberBegin {
    public Session session;
    SessionFactory sessionFactory;
    public void creatConn()
    {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }
    public void closeConn()
    {
        session.close();
        sessionFactory.close();
    }


}
