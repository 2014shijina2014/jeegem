package com.jeegem.service.system.dict;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeegem.entity.system.dict.DataDict;
import com.jeegem.entity.system.dict.DataDictItem;
import com.jeegem.repository.system.dict.DataDictDao;
import com.jeegem.service.base.BaseServiceImp;
@Service("DataDictionaryService")
public class DataDictServiceImp extends BaseServiceImp<DataDict> implements DataDictService {

	@Autowired
	private DataDictDao dao;
	
	@Override
	public Map<String,DataDict> findDatas(String ids,String keys){
		List<String> list=new ArrayList<String>();
		Map<String, DataDict> idmap= new HashMap<String, DataDict>();
		if(StringUtils.isNoneBlank(ids)&&StringUtils.isNoneBlank(keys)){
			String[] idss=ids.split(",");
			String[] keyss=keys.split(",");
			if(idss.length==keyss.length){
				for(String key:keyss){
					list.add(key.trim());
				}
				List<DataDict> data=((DataDictDao) baseDao).findDatas(list);
				for(int i=0;i<keyss.length;i++){
					for(DataDict d:data){
						String datakey=d.getDataKey();
						if(StringUtils.equals(keyss[i], datakey)){
							idmap.put(idss[i],d);
						}
					}
				}
			}
		}
		return idmap;
	}

	@Override@Transactional
	public int insertDataDict(DataDict dataDict) {
		//进行事务，先添加字典，再删除其下的字典字段，再添加字典字段
		int res=0;
		String dataKey=dataDict.getDataKey();
		if(StringUtils.isNotBlank(dataKey)&&(dao.count(dataDict)==0)){
			String id=dataDict.getId();
			dataDict.setCreateTime(new Date());
			dao.insert(dataDict);
			dao.deleteItems(id);
			List<DataDictItem> items=dataDict.getItems();
			if(items!=null&&items.size()>0){
				for(DataDictItem ddi:items){
					ddi.setDictId(id);
				}
				dao.insertItems(items);
			}
			res=1;
		}
		return res;
	}
	
	@Override@Transactional
	public int updateDataDict(DataDict dataDict) {
		//进行事务，先更新字典，再删除其下的字典字段，再添加字典字段
		int res=0;
		String id=dataDict.getId();
		dataDict.setCreateTime(new Date());
		dao.update(dataDict);
		dao.deleteItems(id);
		List<DataDictItem> items=dataDict.getItems();
		if(items!=null&&items.size()>0){
			for(DataDictItem ddi:items){
					ddi.setDictId(id);
			}
			dao.insertItems(items);
		}
		res=1;
		return res;
	}

	@Override
	@Transactional
	public void delete(DataDict o) {	
		//进行事务删除
		dao.delete(o);
		dao.deleteItems(o.getId());
	}

	@Override
	@Transactional
	public void deleteBatch(List<DataDict> os) {
		//进行事务删除
		dao.deleteBatch(os);
		dao.deleteBatchItems(os);
	}

}
