package com.service.impl;

import com.dao.ContactsDao;
import com.entity.ContactsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="ContactsServiceImpl")
public class ContactsServiceImpl {
    @Autowired
    ContactsDao contactsDao;
    //查询所有
    public List<ContactsEntity> selectContactsAll(Map<String, Object> map){
        return contactsDao.selectContactsAll(map);
    };
    //增加
    public void addOneContacts(ContactsEntity contactsEntity){

    };
    //删除
    public void delContacts(Integer contacts_Id){

    };
    //修改
    public void updateContacts(ContactsEntity contactsEntity){

    };
    //查询总数
    public int selectContactsAllSum(){
       return contactsDao.selectContactsAllSum();
    };
}
