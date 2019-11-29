package com.technology.share.exception;

import com.technology.share.enums.CodeMessage;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMessage codeMessage;


    public BizException(CodeMessage codeMessage){
        this.codeMessage = codeMessage;
    }


    public CodeMessage getCodeMessage() {
        return codeMessage;
    }

    public void setCodeMessage(CodeMessage codeMessage) {
        this.codeMessage = codeMessage;
    }
}
