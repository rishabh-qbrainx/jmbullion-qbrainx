package com.jmbullion.demoapp.service;

import com.jmbullion.demoapp.dao.OrderItemRepository;
import com.jmbullion.demoapp.dao.PaymentDiscountRepository;
import com.jmbullion.demoapp.dao.QuantityDiscountRepository;
import com.jmbullion.demoapp.entity.OrderItem;
import com.jmbullion.demoapp.entity.PaymentDiscount;
import com.jmbullion.demoapp.entity.QuantityDiscount;
import com.jmbullion.demoapp.exceptions.ServiceException;
import com.jmbullion.demoapp.model.FinalOrder;
import com.jmbullion.demoapp.model.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jmbullion.demoapp.utils.DemoappErrorCodes.*;

@Service
@RequiredArgsConstructor
public class DemoappServiceImpl implements IDemoappService {

    private final QuantityDiscountRepository quantityDiscountRepository;

    private final OrderItemRepository orderItemRepository;

    private final PaymentDiscountRepository paymentDiscountRepository;

    Logger logger = LoggerFactory.getLogger(DemoappServiceImpl.class);


    @Override
    public String finalOrder(FinalOrder order) {
        double finalPrice = 0.0;
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            double discount = getDiscount(orderDetail.getItemId(), orderDetail.getQuantity());
            OrderItem orderItem = orderItemRepository.findByItemId(orderDetail.getItemId());
            if (orderItem != null) {
                finalPrice = finalPrice + (orderItem.getPrice() - discount) * orderDetail.getQuantity();
                logger.info("discount calculated for quantity discount for: " + orderDetail.getItemId());
            } else {
                logger.error(INVALID_ITEM_ID.getErrorDesc() + orderDetail.getItemId());
                throw new ServiceException(INVALID_ITEM_ID.getErrorCode(), INVALID_ITEM_ID.getErrorDesc() + orderDetail.getItemId());
            }
        }

        PaymentDiscount paymentDiscount = paymentDiscountRepository.findByPaymentName(order.getPaymentName());
        if (paymentDiscount != null) {
            finalPrice = finalPrice * (100 - paymentDiscount.getDiscount()) / 100;
            logger.info("Final discount calculated for payment discount for: " + order.getPaymentName());
        } else {
            logger.error(INVALID_PAYMENT_TYPE.getErrorDesc() + order.getPaymentName());
            throw new ServiceException(INVALID_PAYMENT_TYPE.getErrorCode(), INVALID_PAYMENT_TYPE.getErrorDesc() + order.getPaymentName());
        }
        return String.valueOf(finalPrice);
    }


    private double getDiscount(int itemId, int quantity) {
        List<QuantityDiscount> quantityDiscountList = quantityDiscountRepository.findByItemId(itemId);
        for (QuantityDiscount quantityDiscount : quantityDiscountList) {
            if (quantityDiscount.getMaxQty() >= quantity && quantityDiscount.getMinQty() <= quantity) {
                return quantityDiscount.getDiscount();
            }
        }
        throw new ServiceException(INVALID_QUANTITY.getErrorCode(), INVALID_QUANTITY.getErrorDesc() + itemId);
    }
}
