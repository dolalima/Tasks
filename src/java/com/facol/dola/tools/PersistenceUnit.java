package com.facol.dola.tools;


import javassist.bytecode.Bytecode;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos
 */
public abstract class PersistenceUnit {
    
    private static EntityManagerFactory entityManagerFactory;
    
    public static void start(){
        PersistenceUnit.entityManagerFactory = Persistence.createEntityManagerFactory("SistemaPU2");
    }
    
    public static void close(){
        PersistenceUnit.entityManagerFactory.close();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory==null){
           PersistenceUnit.start();
        }
        return entityManagerFactory;
    }
    
    
    
    
            
            
    
}