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
    
    private UserJpaController userRepository;
    
    public UserBLL(){
        userRepository = new UserJpaController(PersistenceUnit.getEntityManagerFactory());
    }
    
   public void create(User user){
       
       userRepository.findBy();
       
       userRepository.create(user);
       
    }
    public void update(User user){
    }
    public void remove(User user){
    }
    
}
