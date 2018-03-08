package com.jeegem.common.utils.base;

/**
 * 全局静态资源：
 * 
*/
public class Const {
	
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";            
	public static final String SESSION_MENULIST = "menuList";			//当前菜单
	/**
	 *邮箱配置文件位置
	 */
	public static final String EMAIL_CONFIG="/mail.properties";
	/**
	 *微信配置文件位置
	 */
	public static final String WEIXIN_CONFIG="/weixin/mp.properties";
	/**
	 *上传配置文件位置
	 */
	public static final String UPLOAD_CONFIG="upload.properties";
	/**
	 *没有权限返回的URL
	 */
	public static final String NO_AUTHORIZED_URL="/system/noAuthorized";//没有权限返回的URL
	/**
	 *没有权限返回中文说明
	 */
	public static final String NO_AUTHORIZED_MSG="当前角色没有权限";//
	/**
	 *返回值 没有权限 100
	 */
	public static final int NO_AUTHORIZED=100;
	/**
	 *返回值 成功(1)
	 */
	public static final int SUCCEED = 1;
	/**
	 *返回值 失败(0)
	 */
	public static final int FAIL = 0;
	/**
	 *菜单类型 (1)
	 */
	public static final String RESOURCES_TYPE_MENU = "1";
	/**
	 *功能类型(2)
	 */
	public static final String RESOURCES_TYPE_FUNCTION = "2";
	/**
	 *按钮类型(3)
	 */
	public static final String RESOURCES_TYPE_BUTTON = "3";
	/**
	 *保存成功
	 */
	public static final String SAVE_SUCCEED = "保存成功";
	/**
	 *保存失败
	 */
	public static final String SAVE_FAIL = "保存失败";
	/**
	 *删除成功
	 */
	public static final String DEL_SUCCEED = "删除成功";
	/**
	 *删除失败
	 */
	public static final String DEL_FAIL = "删除失败";
	/**
	 *修改成功
	 */
	public static final String UPDATE_SUCCEED = "修改成功";
	/**
	 *修改失败
	 */
	public static final String UPDATE_FAIL = "修改失败";
	/**
	 *数据获取成功
	 */
	public static final String DATA_SUCCEED = "数据获取成功";
	/**
	 *数据获取失败
	 */
	public static final String DATA_FAIL = "数据获取失败";

}
