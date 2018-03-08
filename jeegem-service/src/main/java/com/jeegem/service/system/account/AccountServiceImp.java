package com.jeegem.service.system.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.common.utils.security.AccountShiroUtil;
import com.jeegem.common.utils.security.CipherUtil;
import com.jeegem.common.utils.tree.entity.ZNodes;
import com.jeegem.entity.system.account.Account;
import com.jeegem.repository.system.account.AccountDao;
import com.jeegem.repository.system.org.PositionDao;
import com.jeegem.service.base.BaseServiceImp;

@Service("AccountService")
public class AccountServiceImp extends BaseServiceImp<Account> implements AccountService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private PositionDao positionDao;
	
	@Override
    public Account findFormatByLoginName(String loginName){
		Account a = null;
		try {
			a= accountDao.findFormatByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	@Transactional
	public void setSetting(String skin){
		Account currentAccount=AccountShiroUtil.getCurrentUser();	
		currentAccount.setSkin(skin);
		accountDao.setSetting(currentAccount);
		AccountShiroUtil.getRealCurrentUser().setSkin(skin);
	}
 
	public Account getPerData(){
		Account pd=accountDao.getPerData(AccountShiroUtil.getCurrentUser().getAccountId());
		return pd;	 
	}
	 
	public void setPerData(Account account){
		 Account cu =AccountShiroUtil.getRealCurrentUser();
		 account.setAccountId(cu.getAccountId());
		 account.setUpdateTime((new Date()));
		 accountDao.setPerData(account);
		 cu.setName(account.getName());
		 cu.setEmail(account.getEmail());
	}
	
	public void setHeadpic (Account account){
		 Account cu =AccountShiroUtil.getRealCurrentUser();
		 account.setAccountId(cu.getAccountId());
		 account.setUpdateTime((new Date()));
		 accountDao.setHeadpic(account);
		 cu.setPicUrl(account.getPicUrl());
	}

	@Override
	public int insertAccount(Account o) throws Exception {
		int res=0;
		String loginName=o.getLoginName();
		//查询数据库是否已经存在用户名
		if(StringUtils.isNotBlank(loginName)&&(accountDao.findCountByLoginName(loginName)==0)){
			String pwrs=CipherUtil.createRandomString(6);//随机密码,以后发邮箱
			o.setDescription(pwrs);//用户随机密码暂时保存在描述里
			String pwrsMD5=CipherUtil.generatePassword(pwrs);//第一次加密md5，
			o.setSkin("skin-0");//默认皮肤
			String salt=CipherUtil.createSalt();
			o.setPassword(CipherUtil.createPwdEncrypt(loginName,pwrsMD5,salt));	
			o.setSalt(salt);
			o.setCreateTime(new Date());
			accountDao.insert(o);	
			res=1;
		}
		return res;
	}

	@Override
	public List<ZNodes> getRoles() {
		return accountDao.getRoles();
	}

	@Override
	public int sysResetPwd(Account o) {
		int res=0;
		String pwd=o.getPassword();
		o.setUpdateTime(new Date());
		String accountId=o.getAccountId();
		if(StringUtils.isNotBlank(pwd)&&StringUtils.isNotBlank(accountId)){
			Account odata=accountDao.find(o).get(0);
			String loginName=odata.getLoginName();
			//随机密码,以后发邮箱
			String salt=CipherUtil.createSalt();
			String pwrsMD5=CipherUtil.generatePassword(pwd);		
			o.setPassword(CipherUtil.createPwdEncrypt(loginName,pwrsMD5,salt));	
			o.setSalt(salt);
			accountDao.resetPwd(o);
			res=1;
		}else{
			res=2;
		}
		return res;
	}

	@Override
	public int preResetPwd(String opwd,String npwd,String qpwd) {
		int res=0;
		String accountId= AccountShiroUtil.getRealCurrentUser().getAccountId();
		String loginName= AccountShiroUtil.getRealCurrentUser().getLoginName();
		if(StringUtils.isNotBlank(opwd)&&StringUtils.isNotBlank(npwd)){	
			if(StringUtils.equals(npwd, qpwd)){
				Account o=new Account();
				o.setAccountId(accountId);
				Account odata=accountDao.findFormatByLoginName(loginName);
				String oPwdEncrypt =CipherUtil.createPwdEncrypt(loginName,opwd.toUpperCase(),odata.getSalt());	
				String odataPwdEncrypt=odata.getPassword();			
				if(StringUtils.equals(oPwdEncrypt, odataPwdEncrypt)){
					String salt=CipherUtil.createSalt();
					String pwrsMD5=npwd.toUpperCase();		
					o.setPassword(CipherUtil.createPwdEncrypt(loginName,pwrsMD5,salt));	
					o.setSalt(salt);
					accountDao.resetPwd(o);
					res=1;
				}else{
					res=2;//密码不正确
				}
			}else{
				res=3;//两次密码不一致
			}
		}
		return res;
	}

	@Override
	@Transactional
	public void deleteAccount(Account o) {
		//事务删除
		accountDao.delete(o);
		//删除人员职务关系表
		positionDao.deleteAccPosByAccId(o.getAccountId());	
	}

	@Override	
	@Transactional
	public void deleteBatchAccount(String chks) {
		//事务删除	
		if(StringUtils.isNotBlank(chks)){
			String[] chk =chks.split(",");
			List<Account> list=new ArrayList<Account>();
			for(String s:chk){
				Account sd=new Account();
				sd.setAccountId(s);
				list.add(sd);
			}
			accountDao.deleteBatch(list);
			positionDao.deleteBatchAccPosByAccId(list);
		}
	
	}
	
}
