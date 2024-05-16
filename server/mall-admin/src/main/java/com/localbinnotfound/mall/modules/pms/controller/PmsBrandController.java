package com.localbinnotfound.mall.modules.pms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsBrand;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
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
    public CommonResult getList(@RequestParam(value="keyword", defaultValue = "") String keyword,
                                @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        Page page = brandService.list(keyword, pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(page));
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsBrand brand) {
        boolean result = brandService.save(brand);
        return CommonResult.success(result);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@RequestBody PmsBrand brand) {
        boolean result = brandService.updateById(brand);
        return CommonResult.success(result);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id) {
        boolean result = brandService.removeById(id);
        if (result) return CommonResult.success(true);
        return CommonResult.failed();
    }

    @RequestMapping(value="/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus(@RequestParam(value="ids", defaultValue = "1") List<Long> ids,
                                        @RequestParam(value="showStatus") Integer showStatus) {
        boolean result = brandService.updateShowStatus(ids, showStatus);

        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    @RequestMapping(value="/update/factoryStatus", method = RequestMethod.POST)
    public CommonResult updateFactoryStatus(@RequestParam(value="ids", defaultValue = "1") List<Long> ids,
                                         @RequestParam(value="factoryStatus") Integer factoryStatus) {
        boolean result = brandService.updateFactoryStatus(ids, factoryStatus);

        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public CommonResult getById(@PathVariable Long id) {
        PmsBrand brand = brandService.getById(id);
        return CommonResult.success(brand);
    }
}

