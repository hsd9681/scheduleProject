package org.sparta.scheduleproject.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorDto {
    String errormsg;
    String errorcode;

    public ErrorDto(String errormsg, String errorcode) {
        this.errormsg = errormsg;
        this.errorcode = errorcode;
    }

}
