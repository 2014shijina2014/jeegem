package com.jeegem.service.system.log;

import java.util.List;

import com.jeegem.common.mybatis.Page;
import com.jeegem.entity.system.log.LoginLog;

public interface LoginLogService {

	public Page<LoginLog> findByPage(LoginLog o,Page<LoginLog> page);	
	
	public void saveLoginLog(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
