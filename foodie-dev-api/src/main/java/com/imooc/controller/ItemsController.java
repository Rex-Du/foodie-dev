package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.ItemCommentCountVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemsService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品详情", tags = "商品详情展示的相关接口")
@RequestMapping("items")
@RestController
public class ItemsController {
    private static final Integer COMMENT_PAGE_SIZE = 10;

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

    @ApiOperation(value = "获取商品评价分类数", notes = "获取商品评价分类数", httpMethod = "GET")
    @GetMapping("commentLevel")
    public IMOOCJSONResult commentLevel(@ApiParam(name = "itemId", value = "商品ID", required = true)
                                        @RequestParam String itemId) {
        ItemCommentCountVO itemCommentCountVO = itemsService.queryItemCommentCount(itemId);
        return IMOOCJSONResult.ok(itemCommentCountVO);
    }

    @ApiOperation(value = "获取商品评价", notes = "获取商品评价", httpMethod = "GET")
    @GetMapping("comments")
    public IMOOCJSONResult comments(@ApiParam(name = "itemId", value = "商品ID", required = true)
                                    @RequestParam String itemId,
                                    @ApiParam(name = "level", value = "评价等级", required = false)
                                    @RequestParam Integer level,
                                    @ApiParam(name = "page", value = "第几页", required = false)
                                    @RequestParam Integer page,
                                    @ApiParam(name = "pageSize", value = "每页大小", required = false)
                                    @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId))
            return IMOOCJSONResult.errorMsg(null);

        if (page == null)
            page = 1;
        if (pageSize == null)
            pageSize = COMMENT_PAGE_SIZE;

        PagedGridResult pagedGridResult = itemsService.queryPagedComments(itemId, level, page, pageSize);
        return IMOOCJSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "搜索商品", notes = "搜索商品", httpMethod = "GET")
    @GetMapping("search")
    public IMOOCJSONResult search(@ApiParam(name = "keywords", value = "商品ID", required = true)
                                    @RequestParam String keywords,
                                    @ApiParam(name = "sort", value = "分类", required = false)
                                    @RequestParam String sort,
                                    @ApiParam(name = "page", value = "第几页", required = false)
                                    @RequestParam Integer page,
                                    @ApiParam(name = "pageSize", value = "每页大小", required = false)
                                    @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(keywords))
            return IMOOCJSONResult.errorMsg(null);

        if (page == null)
            page = 1;
        if (pageSize == null)
            pageSize = COMMENT_PAGE_SIZE;

        PagedGridResult pagedGridResult = itemsService.queryItemsByKeywords(keywords, sort, page, pageSize);
        return IMOOCJSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "按分类搜索商品", notes = "按分类搜索商品", httpMethod = "GET")
    @GetMapping("catItems")
    public IMOOCJSONResult catItems(@ApiParam(name = "catId", value = "商品ID", required = true)
                                  @RequestParam String catId,
                                  @ApiParam(name = "sort", value = "分类", required = false)
                                  @RequestParam String sort,
                                  @ApiParam(name = "page", value = "第几页", required = false)
                                  @RequestParam Integer page,
                                  @ApiParam(name = "pageSize", value = "每页大小", required = false)
                                  @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(catId))
            return IMOOCJSONResult.errorMsg(null);

        if (page == null)
            page = 1;

        if (pageSize == null)
            pageSize = COMMENT_PAGE_SIZE;

        PagedGridResult pagedGridResult = itemsService.queryItemsByCatId(catId, sort, page, pageSize);
        return IMOOCJSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "查询购物车商品", notes = "查询购物车商品", httpMethod = "GET")
    @GetMapping("refresh")
    public IMOOCJSONResult refresh(@ApiParam(name = "itemSpecIds", value = "商品详情ID", required = true)
                                    @RequestParam String itemSpecIds
                                    ) {
        if (StringUtils.isBlank(itemSpecIds))
            return IMOOCJSONResult.errorMsg(null);

        return IMOOCJSONResult.ok(itemsService.queryItemsBySpecIds(itemSpecIds));
    }
}
