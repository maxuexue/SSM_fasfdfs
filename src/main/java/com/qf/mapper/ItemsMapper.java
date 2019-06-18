package com.qf.mapper;

import com.qf.pojo.Items;

import java.util.List;

public interface ItemsMapper {

    //新增
    public int addItems(Items items);

    //查询所有
    public List<Items> findAll();
}
