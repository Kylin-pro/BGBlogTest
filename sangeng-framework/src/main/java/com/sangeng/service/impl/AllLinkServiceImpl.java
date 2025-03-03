package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Link;
import com.sangeng.domain.vo.AllLinkVo;
import com.sangeng.mapper.AllLinkMapper;
import com.sangeng.service.AllLinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AllLinkServiceImpl extends ServiceImpl<AllLinkMapper, Link> implements AllLinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> linkList = list(queryWrapper);
        AllLinkVo allLinkVo = new AllLinkVo();

        List<AllLinkVo> allLinkVos = linkList.stream()
                .map(link -> {
                    BeanUtils.copyProperties(link, allLinkVo);
                    return allLinkVo;
                }).collect(Collectors.toList());

        return ResponseResult.okResult(allLinkVos);

    }
}
