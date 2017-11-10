package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import ru.technoserv.domain.DocumentEntity;
import ru.technoserv.domain.HeaderEntity;

import java.util.List;


@Repository
public class DocumentDao {

    @Autowired
    private SessionFactory sessionFactory;


    private static final Logger logger = Logger.getLogger(DocumentDao.class);

    @Autowired
    public DocumentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public HeaderEntity getHeaderById(int headerId) {
        logger.info("Запрос к базе на чтение header'а с id = " + headerId);
        Session session = getSession();
        HeaderEntity header = (HeaderEntity) session.get(HeaderEntity.class, headerId);
        logger.info(header.toString());

        return header;
    }

    public List<HeaderEntity> getClientHeaders(int clientId) {
        logger.info("Запрос к базе на чтение header'ов клиента с client_id = " + clientId);
        Session session = getSession();
        Criteria criteria = session.createCriteria(HeaderEntity.class);
        List<HeaderEntity> headers = null;
        try {
            headers = criteria.add(Expression.eq("clientId", clientId)).list();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.info("Результат запроса = " + headers);

        return headers;
    }

    public List<DocumentEntity> getClientDocuments(int clientId) {
        logger.info("Запрос к базе на чтение документов клиента с client_id = " + clientId);
        Session session = getSession();
        Criteria criteria = session.createCriteria(DocumentEntity.class);
        List<DocumentEntity> documents = null;
        try {
            documents = criteria.add(Expression.eq("clientId", clientId)).list();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.info("Результат запроса = " + documents);

        return documents;
    }
}
