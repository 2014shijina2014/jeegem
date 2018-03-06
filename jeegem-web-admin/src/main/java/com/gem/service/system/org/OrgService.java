package com.gem.service.system.org;

import java.util.List;

import com.gem.common.utils.tree.entity.ZNodes;
import com.gem.entity.system.org.Org;
import com.gem.service.base.BaseService;

public interface OrgService extends BaseService<Org>{

	/**
	 * 获取机构树
	 * @return
	 */
	public List<ZNodes> getOrgTree();
	/**
     * 获取上级机构树
     * @return
     */
	public List<ZNodes> getPreOrgTree();
	 /**
     * 权限列表包含按钮
     * @param orgId 组织Id
     * @param layer 显示层级
     * @return
     */
	public List<ZNodes> listAuthorized(String orgId,String layer);
	 /**
     * 根据角色Id保存权限列表
     * @param orgId 组织Id
     * @param auss 权限数组
     * @param layer 显示层级
     * @return
     */
	public void saveAuthorized(String orgId,String auss,String layer);
	/**
	 * 删除机构
	 * @return
	 */
	public int delOrg(Org o);
}
