package com.localbinnotfound.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttributeCategory;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    PmsProductAttributeCategoryService attributeCategoryService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                                                         @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        Page page = attributeCategoryService.list(pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(page));
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public CommonResult create(PmsProductAttributeCategory productAttributeCategory) {
        boolean result = attributeCategoryService.add(productAttributeCategory);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    public CommonResult update(PmsProductAttributeCategory productAttributeCategory) {
        boolean result = attributeCategoryService.updateById(productAttributeCategory);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id) {
        boolean result = attributeCategoryService.removeById(id);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }
}

