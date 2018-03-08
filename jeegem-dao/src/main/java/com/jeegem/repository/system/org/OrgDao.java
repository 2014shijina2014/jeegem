package com.jeegem.repository.system.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.entity.system.org.Org;
import com.jeegem.entity.system.resources.OrgResources;
import com.jeegem.entity.system.resources.RoleResources;
import com.jeegem.repository.base.BaseDao;
import com.jeegem.repository.base.JeeGemBatis;
@JeeGemBatis
public interface OrgDao extends BaseDao<Org>{
	/**
     * 通过ID获取机构
     * @return
     */
	public Org getOrg(@Param("id")String id);
	/**
     * 通过父ID子机构
     * @return
     */
	public List<Org> findAndChild(@Param("id")String id);
	/**
     * 通过父ID找出所有子机构和自己
     * @return
     */
	public List<Org> findAllOrg(@Param("id")String id);

	/**
     * 获得新的不是权限的Id
     * @return
     */
	public List<String> getNotAuthoByOrg(@Param("orgId")String orgId,@Param("resIds")List<String> resIds,@Param("layer")String layer);
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
     * @return
     */
	public List<ZNodes> listAuthorized(@Param("orgId")String orgId,@Param("layer")String layer);	
	/**
     * 权限列表包含按钮（带门限）
     * @param pId   父Id
     * @param orgId 组织Id
     * @return
     */
	public List<ZNodes> listAuthorizedByTh(@Param("pId")String pId,@Param("orgId")String orgId,@Param("layer")String layer);
	 /**
     * 根据组织Id删除所有权限关系
     * @param orgId 组织Id
     * @return
     */
	public void delAuthorizedByOrgId(@Param("orgId")String orgId);
	 /**
     * 根据组织Id和显示层级删除权限关系
     * @param orgId 组织Id
     * @param layer 显示层级
     * @return
     */
	public void delAuthorizedByOrgIdAndLayer(@Param("orgId")String orgId,@Param("layer")String layer);
	 /**
     * 根据组织Id删除所有权限关系(批量)
     * @param os 组织Id集合
     * @return
     */
	public void deleteBatchAuthorizedByOrgId(List<Org> os);
	/**
     * 通过组织资源对象列表建立权限关系(批量插入)
     * @param  list 角色资源对象列表
     * @return
     */
	public void insertAuthorizedByOrgId(List<OrgResources> list);
	 /**
     * 根据组织删除权限
     * @param os 组织id和资源id集合
     * @return
     */
	public void delBatchAuthByOrg(List<OrgResources> or);
	 /**
     * 根据角色删除权限
     * @param os 角色id和资源id集合
     * @return
     */
	public void delBatchAuthByRole(List<RoleResources> rr);
	
}
