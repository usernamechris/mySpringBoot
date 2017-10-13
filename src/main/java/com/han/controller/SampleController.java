package com.han.controller;

import com.han.domain.Member;
import com.han.domain.MemberVO;
import com.han.domain.SampleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
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

    @GetMapping("/sample1")
    public void sample1(Model model) {
//        model.addAttribute("greeting", "Hello World");
        model.addAttribute("greeting", "안녕하세요");
    }

    @GetMapping("/sample2")
    public void sample2(Model model) {
        MemberVO vo = new MemberVO(123, "u00", "p00","Hong gil dong",
                new Timestamp(System.currentTimeMillis()));
        model.addAttribute("vo", vo);
    }

    @GetMapping("/sample3")
    public void sample3(Model model) {
        List<MemberVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MemberVO(123, "u0" + i, "p0" + i, "Hong" + i,
            new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("list", list);
    }

    @GetMapping("/sample4")
    public void sample4(Model model) {
        List<MemberVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MemberVO(
                    i,
                    "u000" + i % 3,
                    "p0000" + i % 3,
                    "Hong" + i,
                    new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("list", list);
    }

    @GetMapping("/sample5")
    public void sample5(Model model) {
        String result = "SUCCESS";

        model.addAttribute("result", result);
    }

    @GetMapping("/sample6")
    public void sample6(Model model) {
        List<MemberVO> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new MemberVO(i, "u0" + i, "p0" + i, "Hong" + i,
                    new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("list", list);

        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    @GetMapping("/sample7")
    public void sample7(Model model) {
        model.addAttribute("now", new Date());
        model.addAttribute("price", 123456789);
        model.addAttribute("title", "This is a just sample");
        model.addAttribute("options", Arrays.asList("AAA", "BBB", "CCC", "DDD"));
    }

    @GetMapping("/sample8")
    public void sample8(Model model) {
    }

    @GetMapping("/sample/hello")
    public void hello() {

    }
}
