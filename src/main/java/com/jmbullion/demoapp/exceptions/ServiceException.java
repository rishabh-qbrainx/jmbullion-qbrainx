package com.jmbullion.demoapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorDesc;

}
