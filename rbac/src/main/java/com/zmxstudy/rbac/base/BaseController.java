package com.zmxstudy.rbac.base;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zmxstudy.rbac.param.SearchAndOrder;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Controller 基础类，包含基本增删改查
 *
 * @author star
 */
public class BaseController<S extends IService<E>, E> {
    @Autowired
    protected S baseService;

    /**
     * 根据主键查询目标对象
     *
     * @param id 主键
     * @return /
     */
    @GetMapping("/get")
    public E get(@RequestHeader("Primary-key") @NotEmpty Long id) {
        return baseService.getById(id);
    }

    /**
     * 分页查询数据
     *
     * @param page 当前页
     * @param size 每页条数
     * @return /
     */
    @GetMapping("/page/{page}/{size}")
    public IPage<E> page(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody(required = false) SearchAndOrder<E> searchAndOrder) {
        if (ObjectUtil.isEmpty(searchAndOrder)) {
            return baseService.page(new Page<>(page, size));
        }
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        // 拼接查询条件，使用and相连的like模糊查询
        E search = searchAndOrder.getSearch();
        if (ObjectUtil.isNotEmpty(search)) {
            for (Method method : search.getClass().getDeclaredMethods()) {
                String methodName = method.getName();
                if (methodName.startsWith("get") || methodName.startsWith("is")) {
                    try {
                        Object value = method.invoke(search);
                        if (ObjectUtil.isNotEmpty(value)) {
                            String columnName = StrUtil.toUnderlineCase(methodName.replaceFirst("get|is", ""));
                            queryWrapper.or().like(columnName, value);
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        // 拼接排序字段，默认为升序排序
        if (ObjectUtil.isNotEmpty(searchAndOrder.getOrders())) {
            searchAndOrder.getOrders().forEach(order -> queryWrapper.orderBy(true, order.isAsc(), order.getColumn()));
        }

        return baseService.page(new Page<>(page, size), queryWrapper);
    }

    /**
     * 新增一条数据
     *
     * @param entity 新增的数据
     * @return 新增后的数据
     */
    @PostMapping("/plus")
    public E plus(@RequestBody @NotEmpty E entity) {
        if (baseService.save(entity)) {
            return entity;
        }
        return null;
    }

    /**
     * 根据主键修改数据
     *
     * @param entity 修改的数据
     * @return /
     */
    @PutMapping("/edit")
    public Boolean edit(@RequestBody @NotEmpty E entity) {
        if (baseService.updateById(entity)) {
            return true;
        }
        return null;
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     * @return /
     */
    @DeleteMapping("del")
    public Boolean del(@RequestHeader("Primary-key") @NotEmpty Long id) {
        if (baseService.removeById(id)) {
            return true;
        }
        return null;
    }

    /**
     * 根据主键批量删除数据
     *
     * @param deleteIds 主键
     * @return /
     */
    @DeleteMapping("/del/batch")
    public Boolean del(@RequestBody @NotEmpty List<Long> deleteIds) {
        if (baseService.removeBatchByIds(deleteIds)) {
            return true;
        }
        return null;
    }
}
