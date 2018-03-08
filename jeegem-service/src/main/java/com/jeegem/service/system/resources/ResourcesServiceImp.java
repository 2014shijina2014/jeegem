package com.jeegem.service.system.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.entity.system.resources.Resources;
import com.jeegem.repository.system.resources.ResourcesDao;
import com.jeegem.service.base.BaseServiceImp;


@Service("ResourcesService")
public class ResourcesServiceImp extends BaseServiceImp<Resources> implements ResourcesService {


	@Override
	public List<Resources> findMenuTree(String userId,String layer) {			
		return ((ResourcesDao) baseDao).findMenuTree(userId,layer);
	}
	@Override
	public List<Resources> findBtn(String type,String menuId,String userId) {			
		return ((ResourcesDao) baseDao).findBtn(type,menuId,userId);
	}
	@Override
	public List<ZNodes> listResources(Resources r) {
		return ((ResourcesDao) baseDao).listResources(r);
	}
	
	@Override
	@Transactional
	public int tranDelete(Resources o) {
		int res=0;
		ResourcesDao dao=(ResourcesDao) baseDao;
		String resId=o.getId();
		int childCount=dao.childCount(resId);
		if(childCount==0){
			dao.delete(o);
			dao.delOrgAuthByResId(resId);//删除组织权限
			dao.delRoleAuthByResId(resId);//删除角色权限
			res=1;
		}
		return res;
	}
	
	@Override
	@Transactional
	public int tranDeleteBatch(List<Resources> os) {
		int res=0;
		ResourcesDao dao=(ResourcesDao) baseDao;
		int childCount=dao.childBatchCount(os);
		if(childCount==0){
			dao.deleteBatch(os);
			dao.delBatchOrgAuthByResId(os);//删除组织权限
			dao.delBatchRoleAuthByResId(os);//删除角色权限
			res=1;
		}
		return res;
	}
	
	@Override
	public List<Resources> resAuthorized(String userId, String type) {
		return ((ResourcesDao) baseDao).resAuthorized(userId, type);
	}
	
	@Override
	@Transactional
	public synchronized int updateMenu(Resources o) {
		int res=0;
		if(!StringUtils.equals(o.getId(), o.getParentId())){
			ResourcesDao dao=((ResourcesDao) baseDao);
			o.setUpdateTime(new Date());
			o.setResUrl(StringUtils.trim(o.getResUrl()));
			// 只有父级为0的菜单才能修改层级
			Resources r = dao.findAndson(o);
			// 层级发生改变才修改层级，优化速度
			if (o.getLayer() != r.getLayer()) {
				List<String> rs = new ArrayList<String>();
				setAllSonRes(rs, r);
				dao.updateBatchLayer(rs, String.valueOf(o.getLayer()));
			}
			dao.update(o);	
			res=1;
		}else{
			res=2;
		}	
		return res;
	}
	
	private void setAllSonRes(List<String> list,Resources res){
		list.add(res.getId());
		List<Resources> nodes =res.getNodes();		
		if(nodes!=null&&nodes.size()>0){
			for(Resources r:nodes){
				setAllSonRes(list,r);
			}
		}
	}

}
