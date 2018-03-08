package com.jeegem.service.system.log;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeegem.common.mybatis.Page;
import com.jeegem.common.utils.base.UuidUtil;
import com.jeegem.entity.system.log.LoginLog;
import com.jeegem.repository.system.log.LoginLogDao;
@Service("LoginLogService")
public class LoginLogServiceImp implements LoginLogService{

	@Autowired
	private LoginLogDao dao;
	
	@Override
	public Page<LoginLog> findByPage(LoginLog o, Page<LoginLog> page) {
		page.setResults(dao.findByPage(o, page));
		return page;
	}

	@Override
	public void saveLoginLog(LoginLog o) {
		o.setId(UuidUtil.get32UUID());
		o.setLoginTime(new Date());
		dao.insert(o);
	}

	@Override
	public void deleteBatch(List<LoginLog> os) {
		dao.deleteBatch(os);
	}

}
