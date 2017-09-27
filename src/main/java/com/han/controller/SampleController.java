package com.han.controller;

import com.han.domain.SampleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/sample")
    public SampleVO makeSample() {
        SampleVO vo = new SampleVO();
        vo.setVal1("v1");
        vo.setVal1("v2");
        vo.setVal1("v3");

        System.out.println(vo);

        return vo;
    }

}
