/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facol.dola.business;

import com.facol.dola.models.Activity;
import com.facol.dola.repository.ActivityJpaController;
import com.facol.dola.tools.PersistenceUnit;

/**
 *
 * @author dolalima
 */
public class ActivityBLL {
    
    
    private final ActivityJpaController actitityRepository;  

    public ActivityBLL() {
        actitityRepository = new ActivityJpaController(PersistenceUnit.getEntityManagerFactory());
    }
    
    
    
    public void create(Activity activity){
        
        actitityRepository.create(activity);
    }
    public void update(Activity activity){
    }
    public void remove(Activity activity){
    }
    
}
