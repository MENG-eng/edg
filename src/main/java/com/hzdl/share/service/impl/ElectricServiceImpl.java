package com.hzdl.share.service.impl;

import com.github.pagehelper.PageHelper;
import com.hzdl.common.core.page.PageDomain;
import com.hzdl.common.core.page.TableSupport;
import com.hzdl.common.core.redis.RedisCache;
import com.hzdl.common.utils.StringUtils;
import com.hzdl.common.utils.sql.SqlUtil;
import com.hzdl.share.domain.Electric;
import com.hzdl.share.mapper.ElectricMapper;
import com.hzdl.share.service.IElectricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 电量数据Service业务层处理
 *
 * @author hzdl
 * @date 2022-02-14
 */
@Service
public class ElectricServiceImpl implements IElectricService {
    @Autowired
    private ElectricMapper electricMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<Electric> selectElectricList(Electric electric) {
        startPage();
        return electricMapper.selectElectricList(electric);
    }

    private void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
}
