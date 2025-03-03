package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Category;
import org.springframework.stereotype.Service;


public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();
}
