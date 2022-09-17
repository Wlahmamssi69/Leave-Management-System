package com.pfa.LeaveManagementSystem.handler;
import com.pfa.LeaveManagementSystem.exception.ErrorCodes;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder

public class Error {

    private int httpCode;
    private ErrorCodes code;
    private String message;
    private List<String> errors = new ArrayList<String>();
}
