package com.jeegem.service.article.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.jeegem.service.article.ArticleService;
/**
 * 
 * <p>
 * Title: ArticleServiceImpl.java
 * </p>
 * 
 * <p>
 * Description: 
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * <p>
 * Company: www.jeegem.com
 * </p>
 * 
 * @author JeeGem
 * 
 * @date 2018年3月9日 上午5:15:28
 * 
 * @version JeeGem V3.0
 */
@Component
@Service(interfaceClass=ArticleService.class)
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

	@Override
	public String addArticle(String name) {
		String ad = "获取到的参数为:"+name;
		System.out.println("ad="+ad);
		return ad;
	}

}
