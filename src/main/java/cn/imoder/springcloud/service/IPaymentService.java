package cn.imoder.springcloud.service;

import cn.imoder.springcloud.entities.po.Payment;

public interface IPaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
