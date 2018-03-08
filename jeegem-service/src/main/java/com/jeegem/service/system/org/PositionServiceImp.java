package com.jeegem.service.system.org;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.entity.system.account.Account;
import com.jeegem.entity.system.org.AccountPosition;
import com.jeegem.entity.system.org.Org;
import com.jeegem.entity.system.org.Position;
import com.jeegem.repository.system.org.OrgDao;
import com.jeegem.repository.system.org.PositionDao;
import com.jeegem.service.base.BaseServiceImp;

@Service("PositionService")
public class PositionServiceImp extends BaseServiceImp<Position> implements PositionService{

	@Autowired
	private PositionDao positionDao; 
	
	@Autowired
	private OrgDao orgDao; 
	
	@Override
	public List<ZNodes> getOrgAndPositionTree() {	
		return positionDao.getOrgAndPositionTree();
	}

	@Override
	public List<ZNodes> getPreOrgTree() {
		return positionDao.getPreOrgTree();
	}

	@Override
	public void saveAccountPosition(String posId,String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] chk =ids.split(",");
			List<AccountPosition> aps=new ArrayList<AccountPosition>();
			for(String id:chk){
				AccountPosition ap=new AccountPosition(id,posId);
				aps.add(ap);
			}				
			positionDao.insertAccountPosition(aps);
		}
	}

	@Override
	public void deleteAccPosByAccId(String accountId) {
		if(StringUtils.isNotBlank(accountId)){
			positionDao.deleteAccPosByAccId(accountId);	
		}
	}

	@Override
	public Page<Account> findArrangeAccByPage(Position o,Page<Account> page) {
		page.setResults(positionDao.findArrangeAccByPage(o,page));
		return page;
	}

	@Override
	public Page<Account> findPosByPage(Position o, Page<Account> page) {
		page.setResults(positionDao.findPosByPage(o,page));
		return page;
	}

	@Override@Transactional
	public void deletePos(Position o) {
		positionDao.delete(o);
		positionDao.deleteAccPosByPosId(o.getId());
	}

	@Override
	public Page<Position> findAllPosByPage(Position o, Page<Position> page) {
		String orgId=o.getOrgId();
		StringBuffer orgIds=new StringBuffer();
		List<Org> orgs=orgDao.findAllOrg(orgId);
		//设置父和子组织id
		orgIds.append("'"+orgId+"'"+",");
		dealOrg(orgs,orgIds);
		orgIds.deleteCharAt(orgIds.length()-1);
		o.setAllOrgIds(orgIds.toString());
		List<Position> poss=positionDao.findAllPosByPage(o, page);
		page.setResults(poss);
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
