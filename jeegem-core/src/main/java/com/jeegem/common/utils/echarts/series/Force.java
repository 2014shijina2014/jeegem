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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jeegem.common.utils.echarts.code.Roam;
import com.jeegem.common.utils.echarts.code.SeriesType;
import com.jeegem.common.utils.echarts.code.Symbol;
import com.jeegem.common.utils.echarts.series.force.Category;
import com.jeegem.common.utils.echarts.series.force.Link;
import com.jeegem.common.utils.echarts.series.force.Node;

/**
 * Description: Force
 */
@SuppressWarnings("serial")
public class Force extends Series<Force> {
    /**
     * 力导向图中节点的分类
     */
    private List<Category> categories;
    /**
     * 力导向图的顶点数据
     */
    private List<Node> nodes;
    /**
     * 力导向图的边数据
     */
    private List<Link> links;
    /**
     * 布局中心，可以是绝对值或者相对百分比
     */
    private Object center;
    /**
     * 布局大小，可以是绝对值或者相对百分比
     */
    private Object size;
    /**
     * 防止节点和节点，节点和边之间的重叠
     */
    private Boolean preventOverlap;
    /**
     * 布局冷却因子，值越小结束时间越短，值越大时间越长但是结果也越收敛
     */
    private Object coolDown;
    /**
     * 是否根据屏幕比例拉伸
     */
    private Boolean ratioScaling;
    /**
     * 顶点数据映射成圆半径后的最小半径
     */
    private Integer minRadius;
    /**
     * 顶点数据映射成圆半径后的最大半径
     */
    private Integer maxRadius;
    /**
     * 力导向图的边两端图形样式，可指定为'arrow', 详见symbolList
     */
    private Object linkSymbol;
    /**
     * 力导向图的边两端图形大小
     */
    private Integer linkSymbolSize;
    /**
     * 布局缩放系数，并不完全精确, 效果跟布局大小类似
     */
    private Double scaling;
    /**
     * 向心力系数，系数越大则节点越往中心靠拢
     */
    private Double gravity;
    /**
     * 节点是否能被拖拽
     */
    private Boolean draggable;
    /**
     * 在 500+ 顶点的图上建议设置 large 为 true, 会使用 Barnes-Hut simulation, 同时开启 useWorker 并且把 steps 值调大
     */
    private Boolean large;
    /**
     * 是否在浏览器支持 web worker 的时候把布局计算放入 web worker 中
     */
    private Boolean useWorker;
    /**
     * 每一帧布局计算的迭代次数，因为每一帧绘制的时间经常会比布局时间长很多，所以在使用 web worker 的时候可以把 steps 调大来平衡两者的时间从而达到效率最优化
     */
    private Integer steps;
    /**
     * 是否开启滚轮缩放和拖拽漫游，默认为false（关闭），其他有效输入为true（开启），'scale'（仅开启滚轮缩放），'move'（仅开启拖拽漫游）
     */
    private Object roam;

    /**
     * 构造函数
     */
    public Force() {
        this.type(SeriesType.force);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public Force(String name) {
        super(name);
        this.type(SeriesType.force);
    }

    /**
     * 获取coolDown值
     */
    public Object coolDown() {
        return this.coolDown;
    }

    /**
     * 设置coolDown值
     *
     * @param coolDown
     */
    public Force coolDown(Object coolDown) {
        this.coolDown = coolDown;
        return this;
    }

    /**
     * 获取ratioScaling值
     */
    public Boolean ratioScaling() {
        return this.ratioScaling;
    }

    /**
     * 设置ratioScaling值
     *
     * @param ratioScaling
     */
    public Force ratioScaling(Boolean ratioScaling) {
        this.ratioScaling = ratioScaling;
        return this;
    }


    /**
     * 获取preventOverlap值
     */
    public Boolean preventOverlap() {
        return this.preventOverlap;
    }

    /**
     * 设置preventOverlap值
     *
     * @param preventOverlap
     */
    public Force preventOverlap(Boolean preventOverlap) {
        this.preventOverlap = preventOverlap;
        return this;
    }

    /**
     * 设置categories值
     *
     * @param categories
     */
    public Force categories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    /**
     * 设置nodes值
     *
     * @param nodes
     */
    public Force nodes(List<Node> nodes) {
        this.nodes = nodes;
        return this;
    }

    /**
     * 设置links值
     *
     * @param links
     */
    public Force links(List<Link> links) {
        this.links = links;
        return this;
    }

    /**
     * 力导向图中节点的分类
     */
    public List<Category> categories() {
        if (this.categories == null) {
            this.categories = new ArrayList<Category>();
        }
        return this.categories;
    }

    /**
     * 添加节点分类
     *
     * @param values
     * @return
     */
    public Force categories(Category... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.categories().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 添加节点分类，使用分类名
     *
     * @param names
     * @return
     */
    public Force categories(String... names) {
        if (names == null || names.length == 0) {
            return this;
        }
        for (String name : names) {
            this.categories().add(new Category(name));
        }
        return this;
    }

    /**
     * 添加节点分类，使用分类名
     *
     * @param values
     * @return
     */
    public Force categories(Object... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        for (Object value : values) {
            if (value instanceof String) {
                this.categories().add(new Category((String) value));
            } else if (value instanceof Category) {
                this.categories().add((Category) value);
            }
            //其他忽略
        }
        return this;
    }

    /**
     * 力导向图的顶点数据
     */
    public List<Node> nodes() {
        if (this.nodes == null) {
            this.nodes = new ArrayList<Node>();
        }
        return this.nodes;
    }

    /**
     * 添加力导向图的顶点数据
     *
     * @param values
     * @return
     */
    public Force nodes(Node... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.nodes().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 力导向图的边数据
     */
    public List<Link> links() {
        if (this.links == null) {
            this.links = new ArrayList<Link>();
        }
        return this.links;
    }

    /**
     * 添加力导向图的边数据
     *
     * @param values
     * @return
     */
    public Force links(Link... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.links().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 获取center值
     */
    public Object center() {
        return this.center;
    }

    /**
     * 设置center值
     *
     * @param center
     */
    public Force center(Object center) {
        this.center = center;
        return this;
    }

    /**
     * 获取size值
     */
    public Object size() {
        return this.size;
    }

    /**
     * 设置size值
     *
     * @param size
     */
    public Force size(Object size) {
        this.size = size;
        return this;
    }

    /**
     * 获取minRadius值
     */
    public Integer minRadius() {
        return this.minRadius;
    }

    /**
     * 设置minRadius值
     *
     * @param minRadius
     */
    public Force minRadius(Integer minRadius) {
        this.minRadius = minRadius;
        return this;
    }

    /**
     * 获取maxRadius值
     */
    public Integer maxRadius() {
        return this.maxRadius;
    }

    /**
     * 设置maxRadius值
     *
     * @param maxRadius
     */
    public Force maxRadius(Integer maxRadius) {
        this.maxRadius = maxRadius;
        return this;
    }

    /**
     * 获取linkSymbol值
     */
    public Object linkSymbol() {
        return this.linkSymbol;
    }

    /**
     * 设置linkSymbol值
     *
     * @param linkSymbol
     */
    public Force linkSymbol(Symbol linkSymbol) {
        this.linkSymbol = linkSymbol;
        return this;
    }

    /**
     * 设置linkSymbol值
     *
     * @param linkSymbol
     */
    public Force linkSymbol(String linkSymbol) {
        this.linkSymbol = linkSymbol;
        return this;
    }

    /**
     * 获取linkSymbolSize值
     */
    public Integer linkSymbolSize() {
        return this.linkSymbolSize;
    }

    /**
     * 设置linkSymbolSize值
     *
     * @param linkSymbolSize
     */
    public Force linkSymbolSize(Integer linkSymbolSize) {
        this.linkSymbolSize = linkSymbolSize;
        return this;
    }

    /**
     * 获取scaling值
     */
    public Double scaling() {
        return this.scaling;
    }

    /**
     * 设置scaling值
     *
     * @param scaling
     */
    public Force scaling(Double scaling) {
        this.scaling = scaling;
        return this;
    }

    /**
     * 获取gravity值
     */
    public Double gravity() {
        return this.gravity;
    }

    /**
     * 设置gravity值
     *
     * @param gravity
     */
    public Force gravity(Double gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 获取draggable值
     */
    public Boolean draggable() {
        return this.draggable;
    }

    /**
     * 设置draggable值
     *
     * @param draggable
     */
    public Force draggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    /**
     * 获取large值
     */
    public Boolean large() {
        return this.large;
    }

    /**
     * 设置large值
     *
     * @param large
     */
    public Force large(Boolean large) {
        this.large = large;
        return this;
    }

    /**
     * 获取useWorker值
     */
    public Boolean useWorker() {
        return this.useWorker;
    }

    /**
     * 设置useWorker值
     *
     * @param useWorker
     */
    public Force useWorker(Boolean useWorker) {
        this.useWorker = useWorker;
        return this;
    }

    /**
     * 获取steps值
     */
    public Integer steps() {
        return this.steps;
    }

    /**
     * 设置steps值
     *
     * @param steps
     */
    public Force steps(Integer steps) {
        this.steps = steps;
        return this;
    }

    /**
     * 获取roam值
     */
    public Object roam() {
        return this.roam;
    }

    /**
     * 设置roam值
     *
     * @param roam
     */
    public Force roam(Boolean roam) {
        this.roam = roam;
        return this;
    }

    /**
     * 设置roam值
     *
     * @param roam
     */
    public Force roam(Roam roam) {
        this.roam = roam;
        return this;
    }

    /**
     * 获取categories值
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * 设置categories值
     *
     * @param categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * 获取nodes值
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * 设置nodes值
     *
     * @param nodes
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * 获取links值
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * 设置links值
     *
     * @param links
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * 获取center值
     */
    public Object getCenter() {
        return center;
    }

    /**
     * 设置center值
     *
     * @param center
     */
    public void setCenter(Object center) {
        this.center = center;
    }

    /**
     * 获取size值
     */
    public Object getSize() {
        return size;
    }

    /**
     * 设置size值
     *
     * @param size
     */
    public void setSize(Object size) {
        this.size = size;
    }

    /**
     * 获取minRadius值
     */
    public Integer getMinRadius() {
        return minRadius;
    }

    /**
     * 设置minRadius值
     *
     * @param minRadius
     */
    public void setMinRadius(Integer minRadius) {
        this.minRadius = minRadius;
    }

    /**
     * 获取maxRadius值
     */
    public Integer getMaxRadius() {
        return maxRadius;
    }

    /**
     * 设置maxRadius值
     *
     * @param maxRadius
     */
    public void setMaxRadius(Integer maxRadius) {
        this.maxRadius = maxRadius;
    }

    /**
     * 获取linkSymbol值
     */
    public Object getLinkSymbol() {
        return linkSymbol;
    }

    /**
     * 设置linkSymbol值
     *
     * @param linkSymbol
     */
    public void setLinkSymbol(Object linkSymbol) {
        this.linkSymbol = linkSymbol;
    }

    /**
     * 获取linkSymbolSize值
     */
    public Integer getLinkSymbolSize() {
        return linkSymbolSize;
    }

    /**
     * 设置linkSymbolSize值
     *
     * @param linkSymbolSize
     */
    public void setLinkSymbolSize(Integer linkSymbolSize) {
        this.linkSymbolSize = linkSymbolSize;
    }

    /**
     * 获取scaling值
     */
    public Double getScaling() {
        return scaling;
    }

    /**
     * 设置scaling值
     *
     * @param scaling
     */
    public void setScaling(Double scaling) {
        this.scaling = scaling;
    }

    /**
     * 获取gravity值
     */
    public Double getGravity() {
        return gravity;
    }

    /**
     * 设置gravity值
     *
     * @param gravity
     */
    public void setGravity(Double gravity) {
        this.gravity = gravity;
    }

    /**
     * 获取draggable值
     */
    public Boolean getDraggable() {
        return draggable;
    }

    /**
     * 设置draggable值
     *
     * @param draggable
     */
    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    /**
     * 获取large值
     */
    public Boolean getLarge() {
        return large;
    }

    /**
     * 设置large值
     *
     * @param large
     */
    public void setLarge(Boolean large) {
        this.large = large;
    }

    /**
     * 获取useWorker值
     */
    public Boolean getUseWorker() {
        return useWorker;
    }

    /**
     * 设置useWorker值
     *
     * @param useWorker
     */
    public void setUseWorker(Boolean useWorker) {
        this.useWorker = useWorker;
    }

    /**
     * 获取steps值
     */
    public Integer getSteps() {
        return steps;
    }

    /**
     * 设置steps值
     *
     * @param steps
     */
    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    /**
     * 获取coolDown值
     */
    public Object getCoolDown() {
        return coolDown;
    }

    /**
     * 设置coolDown值
     *
     * @param coolDown
     */
    public void setCoolDown(Object coolDown) {
        this.coolDown = coolDown;
    }

    /**
     * 获取ratioScaling值
     */
    public Boolean getRatioScaling() {
        return ratioScaling;
    }

    /**
     * 设置ratioScaling值
     *
     * @param ratioScaling
     */
    public void setRatioScaling(Boolean ratioScaling) {
        this.ratioScaling = ratioScaling;
    }

    /**
     * 获取preventOverlap值
     */
    public Boolean getPreventOverlap() {
        return preventOverlap;
    }

    /**
     * 设置preventOverlap值
     *
     * @param preventOverlap
     */
    public void setPreventOverlap(Boolean preventOverlap) {
        this.preventOverlap = preventOverlap;
    }

    /**
     * 获取roam值
     */
    public Object getRoam() {
        return roam;
    }

    /**
     * 设置roam值
     *
     * @param roam
     */
    public void setRoam(Object roam) {
        this.roam = roam;
    }
}
