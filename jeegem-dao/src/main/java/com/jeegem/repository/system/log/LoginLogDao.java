package com.jeegem.repository.system.log;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeegem.common.mybatis.Page;
import com.jeegem.entity.system.log.LoginLog;
import com.jeegem.repository.base.JeeGemBatis;

@JeeGemBatis
public interface LoginLogDao {

	public List<LoginLog> findByPage(@Param("param")LoginLog o,Page<LoginLog> page);
	
	public void insert(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
