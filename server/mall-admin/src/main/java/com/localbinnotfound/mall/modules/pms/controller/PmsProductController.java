package com.localbinnotfound.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsProduct;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttributeCategory;
import com.localbinnotfound.mall.modules.pms.service.PmsBrandService;
import com.localbinnotfound.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public CommonResult getList(@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        Page page = productService.list(pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(page));
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsProduct product) {
        boolean result = productService.save(product);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }
}

