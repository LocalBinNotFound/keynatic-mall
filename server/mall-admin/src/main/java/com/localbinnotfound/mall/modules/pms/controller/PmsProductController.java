package com.localbinnotfound.mall.modules.pms.controller;


import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsProduct;
import com.localbinnotfound.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    PmsProductService productService;

    @ApiOperation("Product List")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public CommonResult<List<PmsProduct>> list() {
        List<PmsProduct> list = productService.list();
        return CommonResult.success(list);
    }
}

