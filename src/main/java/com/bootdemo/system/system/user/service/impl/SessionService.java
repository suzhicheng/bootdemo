package com.bootdemo.system.system.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdemo.system.system.user.entity.UserEntity;
import com.bootdemo.system.system.user.entity.UserOnline;

@Service
public class SessionService {
    private final SessionDAO sessionDAO;

    @Autowired
    public SessionService(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    
    public List<UserOnline> list() {
        List<UserOnline> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            UserOnline userOnline = new UserOnline();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                UserEntity userDO = (UserEntity) principalCollection.getPrimaryPrincipal();
                userOnline.setUserName(userDO.getUserName());
            }
            userOnline.setId((String) session.getId());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            list.add(userOnline);
        }
        return list;
    }

    
    public List<UserEntity> listOnlineUser() {
        List<UserEntity> list = new ArrayList<>();
        UserEntity userDO;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                userDO = (UserEntity) principalCollection.getPrimaryPrincipal();
                list.add(userDO);
            }
        }
        return list;
    }

    
    public Collection<Session> sessionList() {
        return sessionDAO.getActiveSessions();
    }

    
    public boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
