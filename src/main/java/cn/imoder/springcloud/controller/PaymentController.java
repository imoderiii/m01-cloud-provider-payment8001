package cn.imoder.springcloud.controller;

import cn.imoder.springcloud.entities.dto.CommonResult;
import cn.imoder.springcloud.entities.po.Payment;
import cn.imoder.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/provider")
@Slf4j
public class PaymentController {

    @Resource
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****PaymentController&create*****: " + result);
        return result > 0
                ? new CommonResult<>(CommonResult.SUCCESS, "创建成功! SERVERPORT: " + serverPort, payment)
                : new CommonResult<>(CommonResult.ERROR, "创建失败! SERVERPORT: " + serverPort, null);
    }

    @GetMapping("/getinfo/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****PaymentController&getPaymentById*****: " + payment);
        return payment == null
                ? new CommonResult<>(CommonResult.ERROR, "获取" + id + "的信息失败! SERVERPORT: " + serverPort, null)
                : new CommonResult<>(CommonResult.SUCCESS, "获取" + id + "的信息成功! SERVERPORT: " + serverPort, payment);
    }

    @GetMapping("/discovery")
    public DiscoveryClient discovery() {
        discoveryClient.getServices().forEach(sev -> {
            log.info("*****PaymentController&discovery*****: " + sev);
            discoveryClient.getInstances(sev).forEach(instance -> {
                log.info("*****PaymentController&discovery*****: "
                        + instance.getInstanceId() + "\t"
                        + instance.getHost() + "\t"
                        + instance.getServiceId() + "\t"
                        + instance.getUri() + "\t"
                        + instance.getPort()
                );
            });
        });
        return discoveryClient;
    }


}
