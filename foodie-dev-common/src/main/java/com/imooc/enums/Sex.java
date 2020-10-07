package com.imooc.enums;

/**
 *
 */
public enum Sex {
   woman(0, "nu"),
   man(1, "nan"),
   secret(2, "baomi");

   public final Integer type;
   public final String value;

   Sex(Integer type, String value){
       this.type = type;
       this.value = value;
   }
}
