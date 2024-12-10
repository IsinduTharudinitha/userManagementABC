package com.Spring.UserManagementForABC.Exception;

public class SystemException extends RuntimeException {
    private ErrorCode errorCode;
    public SystemException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
