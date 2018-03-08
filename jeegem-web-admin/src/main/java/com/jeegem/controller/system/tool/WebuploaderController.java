package com.jeegem.controller.system.tool;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jeegem.common.utils.DateUtils;
import com.jeegem.common.utils.PropertyUtil;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.base.UuidUtil;
import com.jeegem.controller.base.BaseController;
/**
 * 上传工具
 */
@Controller
@RequestMapping("/backstage/tool/webuploader/")
public class WebuploaderController extends BaseController<Object>{

	/**
	 * 测试页
	 * @param change
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/test/{change}")	
	public String index(@PathVariable("change") String change) throws UnsupportedEncodingException{
			if(StringUtils.equals("img",change)){
				return "/system/tool/upload/img";
			}else if(StringUtils.equals("file", change)){
				return "/system/tool/upload/file";
			}else{
				return "/system/tool/upload/moreImg";
			}
	}
	
	@RequestMapping(value = "uploadPic")  
    public void uploadPic(@RequestParam(value = "file", required = false) MultipartFile file,
    		HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		PrintWriter out = response.getWriter();
		logger.info("上传图片开始");
		try {  
			Map<String,String> uploadMap=PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
			String picAllowSuffix=uploadMap.get("picAllowSuffix");//允许图片文件规格
			String picAllowSize=uploadMap.get("picAllowSize");//允许图片文件大小
			String picFilePath=uploadMap.get("picFilePath");//允许图片文件大小		
			//获取文件后缀名
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			if(StringUtils.isNotBlank(picAllowSuffix)){
		    	int length = picAllowSuffix.indexOf(suffix.toLowerCase());	    	
		        if(length == -1){
		        	json.put("res", Const.FAIL);
					json.put("resMsg", "请上传允许格式的图片");
					out.print(json.toString());
					return;
		        }	
			}	
			long size = file.getSize();
			if(StringUtils.isNotBlank(picAllowSize)){
				 long allowsize=Long.parseLong(picAllowSize);
			        if(size > allowsize){
			        	json.put("res", Const.FAIL);
						json.put("resMsg", "超过上传图片大小限制");
						out.print(json.toString());
						return;
			        }	
			}          
	        String realPath=request.getSession().getServletContext().getRealPath("/");
	        String path=realPath+picFilePath;
	        String fileName =DateUtils.getDate("yyyyMMdd")+ UuidUtil.get32UUID()+"."+suffix;  
	        File baseFile = new File(path);
			File targetFile = new File(baseFile, fileName);
			if(!baseFile.exists())baseFile.mkdirs();  
	        //保存  
            file.transferTo(targetFile);        	
        	json.put("res", Const.SUCCEED);
        	json.put("resMsg", "上传成功");
			json.put("saveUrl","/"+picFilePath+fileName);
			json.put("size", size);
			out.print(json.toString());
			logger.info("上传图片结束，位置："+path);
        } catch (Exception e) {
			e.printStackTrace();
			logger.error("上传图片出错",e);
		}		
    }  
	
	@RequestMapping(value = "uploadFile")  
    public void uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
    		HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		PrintWriter out = response.getWriter();
		logger.info("上传文件开始");
		try {  
			Map<String,String> uploadMap=PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
			String fileAllowSuffix=uploadMap.get("fileAllowSuffix");//允许文件规格
			String fileAllowSize=uploadMap.get("fileAllowSize");//允许文件大小
			String fileFilePath=uploadMap.get("fileFilePath");//允许文件大小
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			if(StringUtils.isNotBlank(fileAllowSuffix)){
				//获取文件后缀名	
		    	int length = fileAllowSuffix.indexOf(suffix.toLowerCase());
		        if(length == -1){
		        	json.put("res", Const.FAIL);
					json.put("resMsg", "请上传允许格式的文件");
					out.print(json.toString());
					return;
		        }
			}	    	
			long size = file.getSize();
			if(StringUtils.isNotBlank(fileAllowSize)){
				 long allowsize=Long.parseLong(fileAllowSize);
			        if(size > allowsize){
			        	json.put("res", Const.FAIL);
						json.put("resMsg", "超过上传文件大小限制");
						out.print(json.toString());
						return;
			        }	
			}   
	        String realPath=request.getSession().getServletContext().getRealPath("/");
	        String path=realPath+fileFilePath;
	        String fileName =DateUtils.getDate("yyyyMMdd")+ UuidUtil.get32UUID()+"."+suffix;  
	        File baseFile = new File(path);
			File targetFile = new File(baseFile, fileName);
			if(!baseFile.exists())baseFile.mkdirs();  
	        //保存  
            file.transferTo(targetFile);        	
        	json.put("res", Const.SUCCEED);
			json.put("saveUrl","/"+fileFilePath+fileName);
			json.put("resMsg", "上传成功");
			json.put("size", size);
			out.print(json.toString());
			logger.info("上传文件结束，位置："+path);
        } catch (Exception e) {
			e.printStackTrace();
			logger.error("上传文件出错",e);
		}		
    }  
}
