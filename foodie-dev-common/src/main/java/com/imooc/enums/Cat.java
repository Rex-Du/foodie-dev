package com.imooc.enums;

/**
 *
 */
public enum Cat {
   one(1, "一级分类"),
   two(2, "二级分类"),
   three(3, "三级分类");

   public final Integer type;
   public final String value;

   Cat(Integer type, String value){
       this.type = type;
       this.value = value;
   }
}
