package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有一级分类
   * @return
     */
    public List<Category> queryRootCats();

    /*

     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);
}
