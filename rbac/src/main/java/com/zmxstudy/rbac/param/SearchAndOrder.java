package com.zmxstudy.rbac.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 搜索和排序条件
 *
 * @author star
 */
@Data
public class SearchAndOrder<E> {
    /**
     * 搜索条件
     */
    private E search;
    /**
     * 排序条件
     */
    private List<Order> orders;

    /**
     * 排序对象
     */
    @Data
    public static class Order {
        /**
         * 排序字段
         */
        private String column;
        /**
         * 排序类型：是否升序排序，默认升序
         */
        @JsonProperty("isAsc")
        private boolean isAsc = true;
    }
}
