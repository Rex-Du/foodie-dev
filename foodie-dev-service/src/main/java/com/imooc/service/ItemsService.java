package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;

import java.util.List;

public interface ItemsService {
    public Items queryItemById(String itemId);

    public List<ItemsImg> queryItemImgsByItemId(String itemId);

    public List<ItemsSpec> queryItemSpecsByItemId(String itemId);

    public ItemsParam queryItemParamByItemId(String itemId);
}
