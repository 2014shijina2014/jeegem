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

package com.jeegem.common.utils.echarts.series;

import java.util.Arrays;

import com.jeegem.common.utils.echarts.code.SeriesType;

/**
 * Description: K
 *
 * 
 */
public class K extends Series<K> {
    /**
     * 柱条（K线蜡烛）宽度，不设时自适应
     */
    private Integer barWidth;
    /**
     * 柱条（K线蜡烛）最大宽度，不设时自适应
     */
    private Integer barMaxWidth;

    /**
     * 构造函数
     */
    public K() {
        this.type(SeriesType.k);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public K(String name) {
        super(name);
        this.type(SeriesType.k);
    }

    /**
     * 设置open,close,min,max值
     *
     * @param open
     * @param close
     * @param min
     * @param max
     */
    public K data(Double open, Double close, Double min, Double max) {
        Object[] kData = new Object[]{open, close, min, max};
        super.data().add(kData);
        return this;
    }

    /**
     * 获取barWidth值
     */
    public Integer barWidth() {
        return this.barWidth;
    }

    /**
     * 设置barWidth值
     *
     * @param barWidth
     */
    public K barWidth(Integer barWidth) {
        this.barWidth = barWidth;
        return this;
    }

    /**
     * 获取barMaxWidth值
     */
    public Integer barMaxWidth() {
        return this.barMaxWidth;
    }

    /**
     * 设置barMaxWidth值
     *
     * @param barMaxWidth
     */
    public K barMaxWidth(Integer barMaxWidth) {
        this.barMaxWidth = barMaxWidth;
        return this;
    }

    /**
     * 获取barWidth
     */
    public Integer getBarWidth() {
        return barWidth;
    }

    /**
     * 设置barWidth
     *
     * @param barWidth
     */
    public void setBarWidth(Integer barWidth) {
        this.barWidth = barWidth;
    }

    /**
     * 获取barMaxWidth
     */
    public Integer getBarMaxWidth() {
        return barMaxWidth;
    }

    /**
     * 设置barMaxWidth
     *
     * @param barMaxWidth
     */
    public void setBarMaxWidth(Integer barMaxWidth) {
        this.barMaxWidth = barMaxWidth;
    }
}
