package com.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.UserBorrowDetail;
import com.test.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class BorrowController {
    @Resource
    BorrowService service;
    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        return service.getUserBorrowDetailByUid(uid);
    }

    @RequestMapping("/blocked")
    JSONObject blocked(){
        JSONObject object = new JSONObject(); object.put("code", 403);
        object.put("success", false); object.put("massage", "Please try later!"); return object;
    }

    @RequestMapping("/borrow/take/{uid}/{bid}")
    JSONObject borrow(@PathVariable("uid") int uid,
                      @PathVariable("bid") int bid){
        service.doBorrow(uid, bid);
        JSONObject object = new JSONObject(); object.put("code", "200"); object.put("success", false); object.put("message", "借阅成功!"); return object;
    }

    @RequestMapping("/test")
    @SentinelResource(value = "test",
            fallback = "except", //fallback指定出现异常时的替代方案
            exceptionsToIgnore = IOException.class) //忽略那些异常，也就是说这些异常出现时不使
    String test(){
        throw new RuntimeException("Error!");
    }


    String except(Throwable t){
        return t.getMessage();
    }
}



