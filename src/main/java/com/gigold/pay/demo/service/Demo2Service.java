/**
 * Title: Demo2Service.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.demo.dao.DemoDAO;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.PendingException;

/**
 * Title: Demo2Service<br/>
 * Description: <br/>
 * Company: gigold<br/>
 *
 * @author Devin
 * @date 2015年9月28日上午11 :55:23
 */
@Service
public class Demo2Service extends Domain {

    @Autowired
    private DemoService service;

    @Autowired
    private DemoDAO dao;

    /**
     * Add person string.
     *
     * @param p the p
     * @return the string
     * @throws AbortException the abort exception
     */
    //理论上应该也两条都回滚
    @Transactional(rollbackFor = { AbortException.class })
    public String addPerson(Person p) throws AbortException {
        dao.addPerson(p);
        debug("调用addPerson");
        p.setName(p.getName() + "我是第二条");
        return service.addPerson(p);
    }

    /**
     * Modify person boolean.
     *
     * @param p the p
     * @return the boolean
     * @throws AbortException the abort exception
     */
    public boolean modifyPerson(Person p) throws AbortException {
        debug("调用modifyPerson");
        return service.modifyPerson(p);
    }

    /**
     * Query person.
     *
     * @param name the name
     * @return the person
     */
    public Person query(String name) {
        return service.query(name);
    }

    /**
     * Sets dao.
     *
     * @param dao the dao
     */
    public void setDao(DemoDAO dao) {
        this.dao = dao;
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    public void setService(DemoService service) {
        this.service = service;
    }
}
