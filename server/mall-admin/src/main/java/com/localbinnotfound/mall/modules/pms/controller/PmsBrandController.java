package com.localbinnotfound.mall.modules.pms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsBrand;
import com.localbinnotfound.mall.modules.pms.service.PmsBrandService;
import com.localbinnotfound.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.localbinnotfound.mall.modules.pms.service.PmsProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    PmsBrandService brandService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public CommonResult getList(@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        Page page = brandService.list(pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(page));
    }
}

