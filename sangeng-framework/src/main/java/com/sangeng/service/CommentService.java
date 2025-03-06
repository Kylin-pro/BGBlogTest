package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Comment;

public interface CommentService extends IService<Comment> {
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}
