package com.power.lesson6.service;

import com.power.lesson6.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shenli on 2017/1/30.
 */
public class BaseService<T> {

    @Autowired
    private BaseDao<T> dao;

    public void createDomain(T domain){
        dao.createDomain(domain);
    }

    public T findById(Serializable id){
        return dao.findById(id);
    }

    public void updateDomain(T domain){
        dao.updateDomain(domain);
    }

    public void deleteDomain(Serializable id) {
        dao.deleteDomain(id);
    }

    public List<T> findAll(){
        return dao.findAll();
    }

}
