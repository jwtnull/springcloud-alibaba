package com.atguigu.springcoud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName springcloud
 * @Package com.atguigu.springcoud.controller
 * @ClassName PaymentController
 * @Author ZCC
 * @Date 2022/03/01
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;


    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功,返回结果" + result + "\t 服务端口：" + serverPort, payment);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功" + "\t 服务端口：" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID: " + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    /**
     * @throws
     * @title discovery
     * @description 服务发现
     * @author zcc
     * @date 2022/6/12 14:18
     * @return: com.atguigu.springcloud.entities.CommonResult
     */
    @GetMapping(value = "/payment/discovery")
    public CommonResult discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
        }

        List<ServiceInstance> serviceInstance = discoveryClient.getInstances("");
        for (ServiceInstance instances : serviceInstance) {
            log.info(instances.getInstanceId() + "\t" + instances.getInstanceId() + "\t" + instances.getPort());
        }

        return new CommonResult(200, "查询成功！", "");
    }
}