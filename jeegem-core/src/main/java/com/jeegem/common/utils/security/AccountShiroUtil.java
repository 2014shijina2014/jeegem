package com.jeegem.common.utils.security;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jeegem.common.utils.base.Const;
import com.jeegem.entity.system.account.Account;

/**
 * 封装shiro用对象获取
 * 
 */
public class AccountShiroUtil {
	/**
	 * 获取当前对象的拷贝
	 * 
	 * @return
	 */
	public static Account getCurrentUser() {
		Account customer = null;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if (null != session) {
			Object obj = session.getAttribute(Const.SESSION_USER);
			if (null != obj && obj instanceof Account) {
				try {
					/**
					 * 复制一份对象，防止被错误操作
					 */
					customer = (Account) BeanUtils.cloneBean((Account) obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return customer;
	}

	/**
	 * 获取当前真实的对象，可以进行操作实体
	 * 
	 * @return
	 */
	public static Account getRealCurrentUser() {
		Account customer = null;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if (null != session) {
			Object obj = session.getAttribute(Const.SESSION_USER);
			if (null != obj && obj instanceof Account) {
				try {
					/**
					 * 不复制一份对象，防止被错误操作
					 */
					customer = (Account) obj;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return customer;
	}
}
