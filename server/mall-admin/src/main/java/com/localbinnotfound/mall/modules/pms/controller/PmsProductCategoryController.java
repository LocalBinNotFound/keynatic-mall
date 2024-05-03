package com.localbinnotfound.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.localbinnotfound.mall.modules.pms.service.PmsProductCategoryService;
import com.localbinnotfound.mall.modules.pms.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    PmsProductCategoryService productCategoryService;

    @RequestMapping(value="/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                                                @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        Page page = productCategoryService.list(parentId, pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(page));
    }
}

