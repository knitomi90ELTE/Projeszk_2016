/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;



/**
 *
 * @author capri
 */
public class RegQuery extends DefaultDao<UserEntity> {

    public RegQuery() {
        super(UserEntity.class);
    }
    
    public void addUser(UserEntity user){
        create(user);
    }
    
    
}
