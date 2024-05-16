package com.localbinnotfound.mall.modules.ums.controller;


import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.modules.ums.model.UmsMemberLevel;
import com.localbinnotfound.mall.modules.ums.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-16
 */
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    UmsMemberLevelService memberLevelService;

    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public CommonResult list(@RequestParam(value = "defaultStatus", defaultValue = "0") Integer defaultStatus) {
        List<UmsMemberLevel> list = memberLevelService.list(defaultStatus);

        return CommonResult.success(list);
    }
}

