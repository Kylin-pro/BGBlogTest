package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService  {
    ResponseResult UploadImage(MultipartFile img);
}
