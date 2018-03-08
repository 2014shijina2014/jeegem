/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.jeegem.common.utils.echarts.axis;

import com.jeegem.common.utils.echarts.style.AreaStyle;

/**
 * 分隔区域
 *
 * 
 */
public class SplitArea implements java.io.Serializable {

    private static final long serialVersionUID = -5528605504445280383L;

    /**
     * 默认不显示，属性show控制显示与否
     */
    private Boolean show;
    /**
     * 属性areaStyle（详见areaStyle）控制区域样式
     *
     * @see com.jeegem.common.utils.echarts.style.AreaStyle
     */
    private AreaStyle areaStyle;

    /**
     * 获取show值
     */
    public Boolean show() {
        return this.show;
    }

    /**
     * 设置show值
     *
     * @param show
     */
    public SplitArea show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 设置areaStyle值
     *
     * @param areaStyle
     */
    public SplitArea areaStyle(AreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return this;
    }

    /**
     * 属性areaStyle（详见areaStyle）控制区域样式
     *
     * @see com.jeegem.common.utils.echarts.style.AreaStyle
     */
    public AreaStyle areaStyle() {
        if (this.areaStyle == null) {
            this.areaStyle = new AreaStyle();
        }
        return this.areaStyle;
    }

    /**
     * 获取areaStyle值
     */
    public AreaStyle getAreaStyle() {
        return areaStyle;
    }

    /**
     * 设置areaStyle值
     *
     * @param areaStyle
     */
    public void setAreaStyle(AreaStyle areaStyle) {
        this.areaStyle = areaStyle;
    }

    /**
     * 获取show值
     */
    public Boolean getShow() {
        return show;
    }

    /**
     * 设置show值
     *
     * @param show
     */
    public void setShow(Boolean show) {
        this.show = show;
    }
}
