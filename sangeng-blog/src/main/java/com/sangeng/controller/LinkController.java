package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.AllLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private AllLinkService allLinkService;

    @GetMapping("/getAllLink")
    public ResponseResult getLink() {
        return allLinkService.getAllLink();
    }

}
