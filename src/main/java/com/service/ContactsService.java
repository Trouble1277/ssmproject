package com.service;

import com.entity.ContactsEntity;

import java.util.List;
import java.util.Map;

public interface ContactsService {
    //查询所有
    public List<ContactsEntity> selectContactsAll(Map<String, Object> map);
    //增加
    public void addOneContacts(ContactsEntity contactsEntity);
    //删除
    public void delContacts(Integer contacts_Id);
    //修改
    public void updateContacts(ContactsEntity contactsEntity);
    //查询总数
    public int selectContactsAllSum();
}
