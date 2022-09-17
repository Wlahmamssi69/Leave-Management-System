package com.pfa.LeaveManagementSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public enum ErrorCodes {

 EMPLOYE_NOT_FOUND(1000),
 EMPLOYE_NOT_VALID(1001),
 EMPLOYE_NOT_MODIFIABLE(1002),
 CONGE_NOT_FOUND(2000),
 CONGE_NOT_VALID(2001),
 CONGE_NOT_MODIFIABLE(2002),
 INVALID_DATA(3000),
 USER_NOT_FOUND(4000);


 private int code;

}
