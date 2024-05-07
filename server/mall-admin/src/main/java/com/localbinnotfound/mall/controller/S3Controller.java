package com.localbinnotfound.mall.controller;

import com.localbinnotfound.mall.common.api.CommonResult;
import com.localbinnotfound.mall.dto.S3PolicyResult;
import com.localbinnotfound.mall.service.impl.S3ServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags="S3Controller")
@RequestMapping("/amazon/s3")
public class S3Controller {
    @Autowired
    private S3ServiceImpl s3Service;

    @RequestMapping(value="/policy", method= RequestMethod.GET)
    public CommonResult<S3PolicyResult> policy() {

        S3PolicyResult policy = s3Service.policy();
        log.info("Policy" + policy);
        return CommonResult.success(policy);
    }
}
