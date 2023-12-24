package com.sp.chat.dao;

import com.sp.chat.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao{
    //@PersistenceContext
    private EntityManager em;
    @Override
    public List<Message> getAllMessageByDate() {
        return em.createQuery("select t from " + Message.class.getSimpleName() + " t").getResultList();
    }
}
