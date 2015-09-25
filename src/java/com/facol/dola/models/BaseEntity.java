/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facol.dola.models;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author dolalima
 */
abstract class BaseEntity  {
    
    private Long id;
    
    private Time creation;
            
    private Time lastModification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getCreation() {
        return creation;
    }

    public void setCreation(Time creation) {
        this.creation = creation;
    }

    public Time getLastModification() {
        return lastModification;
    }

    public void setLastModification(Time lastModification) {
        this.lastModification = lastModification;
    }
    
    
}
