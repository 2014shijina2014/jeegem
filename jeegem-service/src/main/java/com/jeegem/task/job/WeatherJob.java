package com.jeegem.task.job;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.jeegem.common.utils.IOUtil;
import com.jeegem.common.utils.SpringWebContextUtil;
import com.jeegem.entity.system.dict.SysDict;
import com.jeegem.entity.task.base.ScheduleJob;
import com.jeegem.service.system.dict.SysDictService;
import com.jeegem.task.utils.ScheduleUtils;
import com.jeegem.task.utils.TaskLogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherJob implements Job{
	
	   /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(WeatherJob.class);
    
    
/*    @Autowired
	public SysDictService service;*/
    
	@Override
	public void execute(JobExecutionContext context) {
        ScheduleJob scheduleJob= (ScheduleJob)context.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY); 
		String jobName=scheduleJob.getJobName();
		String jobGroup=scheduleJob.getJobGroup();
		String jobClass=scheduleJob.getJobClass();
		LOG.info("任务[" + jobName + "]成功运行");
		try {
			String httpUrl = "http://apis.baidu.com/heweather/weather/free";
			String httpArg = "city=guangzhou";
			String jsonResult = request(httpUrl, httpArg, "baf2da2ebc7cd034e9b2e657b89c2623");
			JSONObject testg = JSONObject.fromObject(jsonResult);
			JSONArray list = testg.optJSONArray("HeWeather data service 3.0");
			JSONObject heWeather = list.getJSONObject(0);
			String status = heWeather.optString("status");
			if ("ok".equals(status)) {
				System.out.println(jsonResult);
				// JSONObject basic=heWeather.optJSONObject("basic");
				// JSONObject update=basic.optJSONObject("basic");
				// String loc=update.optString("loc");
				// 调用地址：http://apistore.baidu.com/apiworks/servicedetail/478.html
				/*
				 * "now": { //实况天气 "cond": { //天气状况 "code": "100", //天气状况代码 "txt": "晴" //天气状况描述 }, "fl": "30", //体感温度 "hum":
				 * "20%", //相对湿度（%） "pcpn": "0.0", //降水量（mm） "pres": "1001", //气压 "tmp": "32", //温度 "vis": "10", //能见度（km） "wind":
				 * { //风力风向 "deg": "10", //风向（360度） "dir": "北风", //风向 "sc": "3级", //风力 "spd": "15" //风速（kmph） } },
				 */
				JSONObject now = heWeather.optJSONObject("now");
				String txt = now.optJSONObject("cond").optString("txt");
				SysDict o = new SysDict();
				o.setParamKey("nowWeather");

				ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
				SysDictService service = (SysDictService) ac.getBean("SysDictService");
				List<SysDict> os = service.find(o);
				SysDict sys = os.get(0);
				sys.setParamValue(txt);
				sys.setDescription(now.toString());
				sys.setUpdateTime(new Date());
				service.update(sys);

				// 保存日志
				TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogUtil.NORMAL, "获取天气任务正常运行");
			}
		} catch (Exception e) {
			LOG.error("任务[" + jobName + "]异常",e);
			// 保存异常日志
			TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogUtil.EXCEPTION,e.toString());
		}			
	}

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg,String apikey) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;
		InputStream is =null;
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey",apikey);
			connection.connect();
			is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			is.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtil.closeQuietly(is);
			IOUtil.closeQuietly(reader);
		}
		return result;
	}
}
