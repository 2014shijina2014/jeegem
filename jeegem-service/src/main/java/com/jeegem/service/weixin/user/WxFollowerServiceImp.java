package com.jeegem.service.weixin.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.common.utils.DateUtils;
import com.jeegem.common.utils.weixin.vo.api.FollowList;
import com.jeegem.common.utils.weixin.vo.api.Follower;
import com.jeegem.common.utils.weixin.vo.api.Follower2;
import com.jeegem.entity.weixin.user.WxFollower;
import com.jeegem.repository.weixin.user.WxFollowerDao;
import com.jeegem.service.weixin.base.WxBaseServiceImp;
@Service("WxFollowerService")
public class WxFollowerServiceImp extends WxBaseServiceImp<WxFollower> implements WxFollowerService {

	@Override@Transactional
	public void syncFollower() {	
		FollowList fl=ua.getFollowerList(null);
		int flTotal=fl.getTotal();
		if(flTotal>0){
			List<String> opendIds=fl.getOpenIds();
			if(opendIds!=null){
				int opendIds_size=opendIds.size();
				if(opendIds_size>0){
					int subibeg=0;
					int subiend=100;
					List<WxFollower> list=new ArrayList<WxFollower>();
					while(subibeg<=opendIds_size){
						subiend=(subiend>opendIds_size)?opendIds_size:subiend;
						List<String> sub_opendIds=opendIds.subList(subibeg,subiend);
						List<Follower2> fl2=new ArrayList<Follower2>();
						for(String suboid:sub_opendIds){
							Follower2 follower2=new Follower2();
							follower2.setOpenid(suboid);
							follower2.setLang("zh_CN");
							fl2.add(follower2);
						}
						List<Follower> subList=ua.getFollowers(fl2);
						for(Follower f:subList){
							WxFollower wx=new WxFollower(f.getOpenid(),f.getSubscribe(),f.getNickname(),f.getSex(),f.getCity(),f.getCountry(),
									f.getProvince(),f.getLanguage(),f.getHeadimgurl(),DateUtils.formatTime(f.getSubscribeTime()),f.getUnionid(),f.getRemark(),
								    String.valueOf(f.getGroupid()));
							list.add(wx);
						}
						subibeg+=100;
						subiend+=100;
					}
					WxFollowerDao dao=(WxFollowerDao)baseDao;
					//先清空
					dao.clearFollower();
					//再批量插入
					dao.insertFollowers(list);
				}			
			}
		}
	}
}
