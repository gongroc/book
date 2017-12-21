package org.book.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @RequestMapping(value = "/")
    public ModelAndView hello() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "你好 FreeMarker");
        map.put("time", new Date());
        return new ModelAndView("hello", map);
    }

}
