package com.jeegem.service.system.org;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.entity.system.org.Org;
import com.jeegem.entity.system.org.Role;
import com.jeegem.entity.system.resources.RoleResources;
import com.jeegem.repository.system.org.OrgDao;
import com.jeegem.repository.system.org.RoleDao;
import com.jeegem.service.base.BaseServiceImp;

@Service("RoleService")
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService{
	
	@Autowired
	private RoleDao roleDao; 
	
	@Autowired
	private OrgDao orgDao; 
	
	@Override
	public List<ZNodes> listAuthorized(String roleId,String layer) {
		return roleDao.listAuthorized(roleId,layer);
	}

	@Override
	@Transactional
	public void saveAuthorized(String roleId, String aus,String layer) {
		List<RoleResources> roleAuth=new ArrayList<RoleResources>();
		String[] auss=aus.split(",");
		for(String s:auss){
			if(StringUtils.isNotBlank(s))
				roleAuth.add(new RoleResources(roleId,s));
		}
		roleDao.delAuthorizedByRoleIdAndLayer(roleId, layer);
		if(roleAuth.size()>0)roleDao.insertAuthorizedByRoleId(roleAuth);
	}

	@Override
	@Transactional
	public void delete(Role o) {	
		//进行事务删除，删除角色还删除角色资源关系表
		roleDao.delete(o);
		roleDao.delAuthorizedByRoleId(o.getId());
	}

	@Override
	@Transactional
	public void deleteBatch(List<Role> os){
		//进行事务删除，删除角色还删除角色资源关系表
		roleDao.deleteBatch(os);
		roleDao.deleteBatchAuthorizedByRoleId(os);
	}

	@Override
	public Page<Role> findAllRoleByPage(Role o, Page<Role> page) {
		String orgId=o.getOrgId();
		StringBuffer orgIds=new StringBuffer();
		List<Org> orgs=orgDao.findAllOrg(orgId);
		//设置父和子组织id
		orgIds.append("'"+orgId+"'"+",");
		dealOrg(orgs,orgIds);
		orgIds.deleteCharAt(orgIds.length()-1);
		o.setAllOrgIds(orgIds.toString());
		
		List<Role> roles=roleDao.findAllRoleByPage(o,page);
		page.setResults(roles);
		return page;
	}

	private void dealOrg(List<Org> orgChilds,StringBuffer orgIds){
		//递归处理所有机构
		for(Org orgChild:orgChilds){
			List<Org> orgs=orgChild.getOrgs();			
			for(Org o:orgs){orgIds.append("'"+o.getId()+"'"+",");}		
			//递归
			dealOrg(orgs,orgIds);
		}		
	}	
}
