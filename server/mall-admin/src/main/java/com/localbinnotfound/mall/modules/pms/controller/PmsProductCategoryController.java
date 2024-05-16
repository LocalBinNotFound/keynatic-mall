package com.localbinnotfound.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.localbinnotfound.mall.common.api.CommonPage;
import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.pms.model.PmsProductCategory;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductCategoryDTO;
import com.localbinnotfound.mall.modules.pms.model.dto.ProductCateChildrenDTO;
import com.localbinnotfound.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonResult getList(@PathVariable Long parentId,
                                @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        Page page = productCategoryService.list(parentId, pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     *      url:'/productCategory/update/showStatus',
     *      method:'post',
     *              data.append('ids',ids);
     *              data.append('showStatus',row.showStatus);
     **/
    @RequestMapping(value="/update/navStatus", method = RequestMethod.POST)
    public CommonResult updateNavStatus(@RequestParam(value="ids", defaultValue = "1") List<Long> ids,
                                        @RequestParam(value="navStatus") Integer navStatus) {
        boolean result = productCategoryService.updateNavStatus(ids, navStatus);

        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    /**
     *     url:'/productCategory/update/showStatus',
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value="/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus(@RequestParam(value="ids", defaultValue = "1") List<Long> ids,
                                         @RequestParam(value="showStatus") Integer showStatus) {
        boolean result = productCategoryService.updateShowStatus(ids, showStatus);

        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    /**
     *     url:'/productCategory/delete/'+id,
     *     method:'post'
     **/
    @RequestMapping(value="/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        boolean result = productCategoryService.removeById(id);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    /**
     *     url:'/productCategory/create',
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody ProductCategoryDTO productCategoryDTO) {
        boolean result = productCategoryService.saveAttrInfo(productCategoryDTO);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    /**
     *     url:'/productCategory/'+id,
     *     method:'get',
     */
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductCategory> getById(@PathVariable Long id) {
        PmsProductCategory productCategory = productCategoryService.getById(id);
        return CommonResult.success(productCategory);
    }

    /**
     *     url:'/productCategory/update/'+id,
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@RequestBody ProductCategoryDTO productCategoryDTO) {
        boolean result = productCategoryService.update(productCategoryDTO);
        if (result) return CommonResult.success(true);
        else return CommonResult.failed();
    }

    @RequestMapping(value="/list/withChildren")
    public CommonResult getWithChildren() {
        List<ProductCateChildrenDTO> list = productCategoryService.getWithChilredn();
        return CommonResult.success(list);
    }
}

