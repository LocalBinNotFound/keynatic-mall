package com.localbinnotfound.mall.modules.pms.controller;


import com.localbinnotfound.mall.modules.pms.service.PmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品评价表 前端控制器
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-16
 */
@RestController
@RequestMapping("/pms/pmsComment")
public class PmsCommentController {

    @Autowired
    PmsCommentService commentService;
}

