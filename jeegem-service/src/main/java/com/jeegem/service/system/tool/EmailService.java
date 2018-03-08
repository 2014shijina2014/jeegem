package com.jeegem.service.system.tool;

import com.jeegem.entity.system.tool.Email;
import com.jeegem.service.base.BaseService;

public interface EmailService extends BaseService<Email>{
	
	/**发送邮件（简单版）
     * @param o
     * @return
     */
	public boolean sentEmailSimple(Email o);

}
