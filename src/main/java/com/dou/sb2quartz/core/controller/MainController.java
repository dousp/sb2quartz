package com.dou.sb2quartz.core.controller;

import org.beetl.core.GroupTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  使用@RestController 时： return "redirect:/index.htm"不生效，默认字符串处理
 */
@RestController
@RequestMapping("/")
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private GroupTemplate groupTemplate;

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("redirect:/index.htm");
        return view;
    }

    @RequestMapping("/index.htm")
    public ModelAndView home() {
        logger.info("【【【go to index.html】】】");
        ModelAndView view = new ModelAndView("index.html");
        List<String> list = new ArrayList<>();
        list.add("zhang");
        list.add("wang");
        list.add("li");
        view.addObject("list", list);
        return view;
    }

    /**
     * 全局异常测试  number = 0
     * @param number
     * @return
     */
    @RequestMapping(value = "/index/{number}")
    public String index(@PathVariable int number){
        System.out.println(20 / number);
        return "get index page successfully.";
    }

    @RequestMapping(value = "/test")
    public Object test(){
        Map<String,String> map = new HashMap<>();
        map.put("ccc",null);
        map.put("bbb","");
        map.put("aaa","aaa");
        Map<String,Object> map1 = new HashMap<>();
        map1.put("list",new ArrayList());
        map1.put("mapp",map);
        return map1;
}

}
