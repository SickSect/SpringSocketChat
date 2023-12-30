package com.sp.chat.dao;

import com.sp.chat.model.Message;
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
        return em.createQuery("""
select u.login, m.date,  m.msg
from message m
join user u on u.id = m.user_id""").getResultList();
    }

    @Override
    @Transactional
    public void save(Message message) {
        em.persist(message);
    }
}
