package com.jeegem.common.utils.weixin.vo.api;

/**
 * 用户分组
 * 
 * @author 
 * @since 2.0
 */
public class Groups {

    /**
     * 分组id,由微信分配
     */
    private int id;
    /**
     * 分组名字,UTF8编码
     */
    private String name;
    /**
     * 分组内用户数量
     */
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Groups [id=" + id + ", name=" + name + ", count=" + count + "]";
    }
}
