package com.x.mall.controller;

import com.x.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 品牌管理controller
 */
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService demoService;

    private static final Logger LOGGER= LoggerFactory.getLogger(PmsBrandController.class);

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public CommonResult createBrand(){

    }

}
