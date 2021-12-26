package com.example.floor_myshop.vo;


import lombok.Data;

/**
 * @author tong
 * @date 2021/12/24
 */
@Data
public class ChatMessageVo {

    private String headImageLink;
    private String content;
    private Integer msgType;
    private Integer aId;
    private Integer bId;
    private String nickName;
    private Integer isText;

}
