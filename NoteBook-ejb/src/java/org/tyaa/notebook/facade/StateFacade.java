/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.notebook.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import org.tyaa.notebook.entity.State;

/**
 *
 * @author student
 */
@Stateless
public class StateFacade extends AbstractFacade<State> {

    @PersistenceContext(unitName = "NoteBook-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StateFacade() {
        super(State.class);
    }
    
    public State findByName(String _name) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(State.class);
        cq.select(root);
        //Равно ли значение в таблице State в поле name параметру, переданному в метод
        cq.where(cb.equal(root.get("name"), _name));
        State result = null;
        try{
            result = (State)em.createQuery(cq).getSingleResult();
        } catch(Exception ex){
        }
        return result;
    }
}
