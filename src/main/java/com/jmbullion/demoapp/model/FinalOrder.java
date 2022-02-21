package com.jmbullion.demoapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class FinalOrder {

    @NotEmpty(message = "Order details cannot be empty.")
    private List<OrderDetail> orderDetails;
    @NotBlank(message = "Please provide address.")
    private String address;
    @NotBlank(message = "Please provide the Payment Name.")
    private String paymentName;
}
