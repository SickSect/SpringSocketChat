package com.sp.chat.dao;

import com.sp.chat.model.Message;
import com.sp.chat.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao{
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    @Override
    public List<Message> getAllMessageByDate() {
        return em.createQuery("Select t from " + Message.class.getSimpleName() + " t").getResultList();
    }

    public List<User> getAllUsers(){
        return em.createQuery("Select t from " + User.class.getSimpleName() + " t").getResultList();
    }

    @Override
    @Transactional
    public void save(Message message) {
        em.persist(message);
    }
}
