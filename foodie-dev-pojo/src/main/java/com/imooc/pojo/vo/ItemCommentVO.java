package com.imooc.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ItemCommentVO {
    //    select ic.comment_level as commentLevel,
//    ic.content as content,
//    ic.sepc_name as sepcName,
//    ic.created_time as createdTime,
//    u.face as userFace,
//    u.nickname as nickname
    private Integer commentLevel;
    private String content;
    private String sepcName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
