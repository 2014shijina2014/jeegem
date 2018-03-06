package com.gem.repository.system.log;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gem.common.mybatis.Page;
import com.gem.entity.system.log.LoginLog;
import com.gem.repository.base.GemBatis;

@GemBatis
public interface LoginLogDao {

	public List<LoginLog> findByPage(@Param("param")LoginLog o,Page<LoginLog> page);
	
	public void insert(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
