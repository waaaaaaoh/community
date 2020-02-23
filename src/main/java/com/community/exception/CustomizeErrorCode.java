package com.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，要不要换一个试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"请登录后重试"),
    SYS_ERROR(2004,"页面不存在或已删除"),
    TYPE_PARAM_NOT_FOUND(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"您回复的评论不存在"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空");

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;
    CustomizeErrorCode(Integer code,String message){
        this.message=message;
        this.code=code;
    }

}
