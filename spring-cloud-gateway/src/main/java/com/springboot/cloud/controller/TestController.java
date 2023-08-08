package com.springboot.cloud.controller;

import com.springboot.cloud.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("name")
    private String name;

    @GetMapping("/value")
    public Result<?> value() {
        return Result.success(name);
    }

    @GetMapping("/tank")
    public Result<?> tank() {
        return new Result<>();
    }

}
