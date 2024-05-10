package com.localbinnotfound.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    PmsProductAttributeService productAttributeService;

    @RequestMapping(value="/list/{cid}", method = RequestMethod.GET)
    public CommonResult getList(@PathVariable Long cid,
                                @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                @RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
                                @RequestParam(value="type") Integer type) {
        Page page = productAttributeService.list(cid, pageNum, pageSize, type);

        return CommonResult.success(CommonPage.restPage(page));
    }
}

