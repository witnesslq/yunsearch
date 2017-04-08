package com.github.gin.yunsearch.controller;

import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.service.els.YunDataEls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by GinPonson on 4/8/2017.
 */
@Controller
public class SearchController extends BaseController {

    @Autowired
    private YunDataEls yunDataEls;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String keyword, Integer pageindex) {
        List<YunData> yunDataList = yunDataEls.findByShareName(keyword, pageindex, 20);
        Long count = yunDataEls.countByShareName(keyword);

        model.addAttribute("keyword",keyword);
        model.addAttribute("yunDataList",yunDataList);
        model.addAttribute("pageindex",pageindex);
        model.addAttribute("count",count);
        return "index";
    }

}
