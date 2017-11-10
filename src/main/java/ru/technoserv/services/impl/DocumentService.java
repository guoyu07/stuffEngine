package ru.technoserv.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.technoserv.dao.impl.DocumentDao;
import ru.technoserv.domain.HeaderEntity;

import java.util.List;

@Service
public class DocumentService {

    private static final Logger logger = Logger.getLogger(DocumentService.class);

    @Autowired
    private DocumentDao documentDao;

    public HeaderEntity getHeaderById(int headerId) {
        logger.info("Запрос к DAO на чтение документов клиента с id = " + headerId);
        return documentDao.getHeaderById(headerId);
    }

    public List<HeaderEntity> getClientHeaders(int clientId) {
        logger.info("Запрос к DAO на чтение header'ов клиента с id = " + clientId);
        return documentDao.getClientHeaders(clientId);
    }

//    public List<DocumentEntity> getClientDocuments(int clientId) {
//        logger.info("Запрос к DAO на чтение документов клиента с id = " + clientId);
//        return documentDao.getClientDocuments(clientId);
//    }
}
