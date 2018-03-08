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

import com.jeegem.common.utils.echarts.code.AxisType;

/**
 * 类目轴
 *
 * 
 */
@SuppressWarnings({"serial"})
public class CategoryAxis extends Axis<CategoryAxis> {
    /**
     * [类目型]类目起始和结束两端空白策略，见下图，默认为true留空，false则顶头
     */
    private Boolean boundaryGap;

    /**
     * 构造函数
     */
    public CategoryAxis() {
        this.type(AxisType.category);
    }

    /**
     * 获取boundaryGap值
     */
    public Boolean boundaryGap() {
        return this.boundaryGap;
    }

    /**
     * 设置boundaryGap值
     *
     * @param boundaryGap
     */
    public CategoryAxis boundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    /**
     * 获取boundaryGap值
     */
    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    /**
     * 设置boundaryGap值
     *
     * @param boundaryGap
     */
    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }
}
