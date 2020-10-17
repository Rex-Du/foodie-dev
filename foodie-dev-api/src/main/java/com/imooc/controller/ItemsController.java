package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemsService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商品详情", tags = "商品详情展示的相关接口")
@RequestMapping("items")
@RestController
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @ApiOperation(value = "获取商品详情", notes = "获取商品详情", httpMethod = "GET")
    @GetMapping("info/{itemId}")
    public IMOOCJSONResult info(@ApiParam(name = "itemId", value = "商品ID", required = true)
                                @PathVariable String itemId) {
        Items item = itemsService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemsService.queryItemImgsByItemId(itemId);
        List<ItemsSpec> itemsSpecs = itemsService.queryItemSpecsByItemId(itemId);
        ItemsParam itemsParam = itemsService.queryItemParamByItemId(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemsImgs);
        itemInfoVO.setItemSpecList(itemsSpecs);
        itemInfoVO.setItemParams(itemsParam);

        return IMOOCJSONResult.ok(itemInfoVO);
    }

}
