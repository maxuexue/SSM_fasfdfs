package com.qf.service;

import com.qf.pojo.Items;

import java.util.List;

public interface ItemsService {
    //新增
    public int addItems(Items items);

    //查询所有
    public List<Items> findAll();
}
