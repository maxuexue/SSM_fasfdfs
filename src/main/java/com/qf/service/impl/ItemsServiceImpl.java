package com.qf.service.impl;

import com.qf.mapper.ItemsMapper;
import com.qf.pojo.Items;
import com.qf.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public int addItems(Items items) {
        int i = itemsMapper.addItems(items);
        return i;
    }

    @Override
    public List<Items> findAll() {
        List<Items> itemsList = itemsMapper.findAll();
        return itemsList;
    }
}
