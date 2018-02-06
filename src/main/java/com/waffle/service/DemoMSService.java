package com.waffle.service;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhangzhen on 2016-7-15.
 */
@FeignClient(value = "demo-ms-service")
public interface DemoMSService {

    @RequestMapping(value = "/zalabs/demo/message", method = RequestMethod.GET)
    public String showMessage();

    @RequestMapping(value = "/zalabs/demo/archmes", method = RequestMethod.GET)
    public String showArchmes();
}
