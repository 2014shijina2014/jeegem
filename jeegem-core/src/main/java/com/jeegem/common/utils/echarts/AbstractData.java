
package com.jeegem.common.utils.echarts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Data接口 - 添加数据
 *
 * 
 */
public abstract class AbstractData<T> implements Data<T>, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 标线图形数据
     *
     * @see com.jeegem.common.utils.echarts.data.PointData
     */
    protected List<Object> data;
    /**
     * 是否可点击，默认开启
     */
    private Boolean clickable;
    /**
     * 非数值显示（如仅用于显示标注标线时），可以通过hoverable:false关闭区域悬浮高亮
     *
     * @since 2.2.0
     */
    private Boolean hoverable;

    /**
     * 获取data值
     */
    public List<Object> data() {
        if (this.data == null) {
            this.data = new ArrayList<Object>();
        }
        return this.data;
    }

    /**
     * 添加元素
     *
     * @param values
     * @return
     */
    public T data(Object... values) {
        if (values == null || values.length == 0) {
            return (T) this;
        }
        this.data().addAll(Arrays.asList(values));
        return (T) this;
    }


    /**
     * 获取clickable值
     */
    public Boolean clickable() {
        return this.clickable;
    }

    /**
     * 设置clickable值
     *
     * @param clickable
     */
    public T clickable(Boolean clickable) {
        this.clickable = clickable;
        return (T) this;
    }

    /**
     * 获取clickable值
     */
    public Boolean getClickable() {
        return clickable;
    }

    /**
     * 设置clickable值
     *
     * @param clickable
     */
    public void setClickable(Boolean clickable) {
        this.clickable = clickable;
    }

    /**
     * 获取hoverable值
     */
    public Boolean hoverable() {
        return this.hoverable;
    }

    /**
     * 设置hoverable值
     *
     * @param hoverable
     */
    public T hoverable(Boolean hoverable) {
        this.hoverable = hoverable;
        return (T) this;
    }

    /**
     * 获取hoverable值
     */
    public Boolean getHoverable() {
        return hoverable;
    }

    /**
     * 设置hoverable值
     *
     * @param hoverable
     */
    public void setHoverable(Boolean hoverable) {
        this.hoverable = hoverable;
    }

    /**
     * 获取data值
     */
    public List<Object> getData() {
        return data;
    }

    /**
     * 设置data值
     *
     * @param data
     */
    public void setData(List<Object> data) {
        this.data = data;
    }
}
