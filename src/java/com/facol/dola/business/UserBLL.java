/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facol.dola.business;

import com.facol.dola.models.User;
import com.facol.dola.repository.UserJpaController;
import com.facol.dola.tools.PersistenceUnit;

/**
 *
 * @author dolalima
 */
public class UserBLL {

    private UserBLL instance;
    private final UserJpaController userRepository;

    public UserBLL() {
        userRepository = new UserJpaController(PersistenceUnit.getEntityManagerFactory());
    }

    public UserBLL getInstance() {
        if (instance == null) {
            instance = new UserBLL();
        }
        return instance;

    }

    public void create(User user) {

        userRepository.findByCpf(user.getCpf());

        userRepository.create(user);

    }

    public void update(User user) {
        try {
            if (user.getId() == null || user.getId().equals(0l)) {
                userRepository.create(user);
            } else {
                userRepository.edit(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remove(User user) throws Exception {

        if (user.getActivities().size() > 0) {
            throw new Exception("Usuario contem atividades registradas");
        } else {
            userRepository.destroy(user.getId());
        }

    }

}
