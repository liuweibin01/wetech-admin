package tech.wetech.admin.model;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * 基础传输对象
 *
 * @author cjbi
 */
public class BaseDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 排序字段
     */
    private String sort;
    /**
     * 升序、降序
     */
    private String order;

    /**
     * 数据库排序
     */
    private String orderBy;
    /**
     * 搜索
     */
    
    private String search;
    /**
     * 偏移量
     */
    private int offset = 0;
    /**
     * 条数
     */
    private int limit = 10;

    /**
     * 创建一个Class的对象来获取泛型的class
     */
    private Class<?> clz;

    public Class<?> getClz() {
        if(clz==null) {
            //获取泛型的Class对象
            clz = ((Class<?>)
                    (((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
        if (sort != null && sort.length() > 0 && this.order != null) {
            EntityColumn entityColumn = EntityHelper.getColumns(getClz()).stream().filter(column -> column.getProperty().equals(sort)).findFirst().orElse(null);
            if (entityColumn != null) {
                this.orderBy = entityColumn.getColumn() + " " + this.order;
            }
        }
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
