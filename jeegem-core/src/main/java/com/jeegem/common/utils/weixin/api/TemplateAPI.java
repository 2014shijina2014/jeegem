package com.jeegem.common.utils.weixin.api;

/**
 * 模板消息接口
 * 
 * @author 
 * @since 2.0
 */
public interface TemplateAPI {

    /**
     * 设置所属行业地址
     */
    static String set_industry = "/template/api_set_industry?access_token=";

    /**
     * 获得模板ID
     */
    static String add_template = "/template/api_add_template?access_token=";

    /**
     * 设置所属行业
     * 
     * @param id1
     *            模板消息所属行业编号
     * @param id2
     *            模板消息所属行业编号
     * @return true 或 false
     */
    boolean setIndustry(int id1, int id2);

    /**
     * 获得模板ID
     * 
     * @param tmlShortId
     *            模板库中模板的编号,有"TM**"和"OPENTMTM**"等形式
     * @return 模板Id
     */
    String getTemplateId(String tmlShortId);
}
