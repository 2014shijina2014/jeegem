package com.jeegem.controller.system.tool;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeegem.common.ajax.AjaxRes;
import com.jeegem.common.utils.base.Const;
import com.jeegem.common.utils.echarts.GsonOption;
import com.jeegem.common.utils.echarts.Option;
import com.jeegem.common.utils.echarts.Title;
import com.jeegem.common.utils.echarts.Toolbox;
import com.jeegem.common.utils.echarts.axis.AxisLabel;
import com.jeegem.common.utils.echarts.axis.CategoryAxis;
import com.jeegem.common.utils.echarts.axis.ValueAxis;
import com.jeegem.common.utils.echarts.code.AxisType;
import com.jeegem.common.utils.echarts.code.Magic;
import com.jeegem.common.utils.echarts.code.MarkType;
import com.jeegem.common.utils.echarts.code.SelectedMode;
import com.jeegem.common.utils.echarts.code.Tool;
import com.jeegem.common.utils.echarts.code.Trigger;
import com.jeegem.common.utils.echarts.code.X;
import com.jeegem.common.utils.echarts.code.Y;
import com.jeegem.common.utils.echarts.data.Data;
import com.jeegem.common.utils.echarts.data.LineData;
import com.jeegem.common.utils.echarts.data.PieData;
import com.jeegem.common.utils.echarts.data.PointData;
import com.jeegem.common.utils.echarts.feature.DataView;
import com.jeegem.common.utils.echarts.feature.MagicType;
import com.jeegem.common.utils.echarts.feature.Mark;
import com.jeegem.common.utils.echarts.feature.Restore;
import com.jeegem.common.utils.echarts.feature.SaveAsImage;
import com.jeegem.common.utils.echarts.series.Bar;
import com.jeegem.common.utils.echarts.series.Funnel;
import com.jeegem.common.utils.echarts.series.K;
import com.jeegem.common.utils.echarts.series.Line;
import com.jeegem.common.utils.echarts.series.MapLocation;
import com.jeegem.common.utils.echarts.series.MarkLine;
import com.jeegem.common.utils.echarts.series.MarkPoint;
import com.jeegem.common.utils.echarts.series.Pie;
import com.jeegem.controller.base.BaseController;

@Controller
@RequestMapping("/backstage/tool/echarts/")
public class EChartsController extends BaseController<Object>{

	
	@RequestMapping("index")	
	public String index(Model model){
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			return "/system/tool/echarts/list";
		}
		return Const.NO_AUTHORIZED_URL;	
	}
	
	@RequestMapping(value="/echart/{changeEchart}")
	@ResponseBody
	public AjaxRes test(@PathVariable("changeEchart") String changeEchart){
		AjaxRes ar=getAjaxRes();
		String echart="";
		if(StringUtils.equals("line", changeEchart)){
			echart=line();
		}else if(StringUtils.equals("bar", changeEchart)){
			echart=bar();
		}else if(StringUtils.equals("k", changeEchart)){
			echart=k();
		}else if(StringUtils.equals("pie", changeEchart)){
			echart=pie();
		}else if(StringUtils.equals("map", changeEchart)){
			echart=map();
		}
		if(StringUtils.isNotBlank(echart)){
			ar.setSucceed(echart);
		}
		return ar;	
	}
	
	//折线图 
	private String line(){
		GsonOption option =new GsonOption();
		option.title(new Title().text("未来一周气温变化").subtext("纯属虚构"));
		option.tooltip(Trigger.axis).calculable(true);;
		option.legend("最高气温","最低气温");//使用Object... values可一个个增加
		//x轴
		ValueAxis va=new ValueAxis();
		va.type(AxisType.category);
		for(int i=1;i<8;i++){
			va.data("星期"+i);//使用Object... values可一个个增加
		}
		option.xAxis(va);
		//工具箱
		Toolbox toolbox=new Toolbox();
		toolbox.show(true);
		toolbox.feature(new Mark().show(true));
		toolbox.feature(new DataView().show(true).readOnly(false));
		toolbox.feature(new MagicType(Magic.line,Magic.bar).show(true));
		toolbox.feature(new Restore().show(true));
		toolbox.feature(new SaveAsImage().show(true));
		option.toolbox(toolbox);	
		//y轴
		option.yAxis(new ValueAxis().type(AxisType.value).axisLabel(new AxisLabel().formatter("{value} °C")));
		//最高气温
		Line line= new Line();
		line.name("最高气温");		
		line.setMarkPoint(new MarkPoint().data(new PointData().type(MarkType.max).name("最大值")));
		line.setMarkLine(new MarkLine().data(new PointData().type(MarkType.average).name("平均值")));
		//最低气温
		Line line2= new Line();
		line2.name("最低气温");
		line2.setMarkPoint(new MarkPoint().data(new PointData().type(MarkType.min).name("最小值")));
		line2.setMarkLine(new MarkLine().data(new PointData().type(MarkType.average).name("平均值")));
	
		Random r=new Random();
		for(int i=1;i<8;i++){	
			line.data(r.nextInt(20)+10);
			line2.data(r.nextInt(20)-10);
		}
		option.series(line,line2); //使用Object... values可一个个增加 		
		return option.toString();
	};
	
	//柱状图 
	private String bar(){
		GsonOption option =new GsonOption();
		option.title(new Title().text("某地区蒸发量和降水量").subtext("纯属虚构"));
		option.tooltip(Trigger.axis).calculable(true);;
		option.legend("蒸发量","降水量");//使用Object... values可一个个增加
		//x轴
		ValueAxis va=new ValueAxis();
		va.type(AxisType.category);
		for(int i=1;i<13;i++){
			va.data(i+"月");//使用Object... values可一个个增加
		}
		option.xAxis(va);
		//工具箱
		Toolbox toolbox=new Toolbox();
		toolbox.show(true);
		toolbox.feature(new Mark().show(true));
		toolbox.feature(new DataView().show(true).readOnly(false));
		toolbox.feature(new MagicType(Magic.line,Magic.bar).show(true));
		toolbox.feature(new Restore().show(true));
		toolbox.feature(new SaveAsImage().show(true));
		option.toolbox(toolbox);	
		//y轴
		option.yAxis(new ValueAxis().type(AxisType.value));
		//最高气温
		Bar bar= new Bar();
		bar.name("蒸发量");		
		bar.setMarkPoint(new MarkPoint().data(new PointData().type(MarkType.max).name("最大值")));
		bar.setMarkLine(new MarkLine().data(new PointData().type(MarkType.average).name("平均值")));
		//最低气温
		Bar bar2= new Bar();
		bar2.name("降水量");
		bar2.setMarkPoint(new MarkPoint().data(new PointData().type(MarkType.min).name("最小值")));
		bar2.setMarkLine(new MarkLine().data(new PointData().type(MarkType.average).name("平均值")));
	
		Random r=new Random();
		for(int i=1;i<13;i++){	
			int rint=r.nextInt(200);
			bar.data(rint+10);
			bar2.data(rint-10);
		}
		option.series(bar,bar2); //使用Object... values可一个个增加 		
		return option.toString();
	};
	
	//k线图 
	private String k(){
		GsonOption option =new GsonOption();
		option.title().text("2013年上半年上证指数");
        option.legend().data("上证指数");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataZoom, Tool.dataView, new MagicType(Magic.line, Magic.bar),
                Tool.restore, Tool.saveAsImage);
        option.dataZoom().show(true).realtime(true).start(50).end(100);
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.boundaryGap(true);
        categoryAxis.axisTick().onGap(false);
        categoryAxis.splitLine().show(false);
        categoryAxis.data("2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
                "2013/1/31", "2013/2/1", "2013/2/4", "2013/2/5", "2013/2/6",
                "2013/2/7", "2013/2/8", "2013/2/18", "2013/2/19", "2013/2/20",
                "2013/2/21", "2013/2/22", "2013/2/25", "2013/2/26", "2013/2/27",
                "2013/2/28", "2013/3/1", "2013/3/4", "2013/3/5", "2013/3/6",
                "2013/3/7", "2013/3/8", "2013/3/11", "2013/3/12", "2013/3/13",
                "2013/3/14", "2013/3/15", "2013/3/18", "2013/3/19", "2013/3/20",
                "2013/3/21", "2013/3/22", "2013/3/25", "2013/3/26", "2013/3/27",
                "2013/3/28", "2013/3/29", "2013/4/1", "2013/4/2", "2013/4/3",
                "2013/4/8", "2013/4/9", "2013/4/10", "2013/4/11", "2013/4/12",
                "2013/4/15", "2013/4/16", "2013/4/17", "2013/4/18", "2013/4/19",
                "2013/4/22", "2013/4/23", "2013/4/24", "2013/4/25", "2013/4/26",
                "2013/5/2", "2013/5/3", "2013/5/6", "2013/5/7", "2013/5/8",
                "2013/5/9", "2013/5/10", "2013/5/13", "2013/5/14", "2013/5/15",
                "2013/5/16", "2013/5/17", "2013/5/20", "2013/5/21", "2013/5/22",
                "2013/5/23", "2013/5/24", "2013/5/27", "2013/5/28", "2013/5/29",
                "2013/5/30", "2013/5/31", "2013/6/3", "2013/6/4", "2013/6/5",
                "2013/6/6", "2013/6/7", "2013/6/13");
        option.xAxis(categoryAxis);

        option.yAxis(new ValueAxis().scale(true).precision(2).splitNumber(9).boundaryGap(0.01, 0.01));

        K k = new K("上证指数");
        k.data(new Object[]{2320.26, 2302.6, 2287.3, 2362.94},
                new Object[]{2300, 2291.3, 2288.26, 2308.38},
                new Object[]{2295.35, 2346.5, 2295.35, 2346.92},
                new Object[]{2347.22, 2358.98, 2337.35, 2363.8},
                new Object[]{2360.75, 2382.48, 2347.89, 2383.76},
                new Object[]{2383.43, 2385.42, 2371.23, 2391.82},
                new Object[]{2377.41, 2419.02, 2369.57, 2421.15},
                new Object[]{2425.92, 2428.15, 2417.58, 2440.38},
                new Object[]{2411, 2433.13, 2403.3, 2437.42},
                new Object[]{2432.68, 2434.48, 2427.7, 2441.73},
                new Object[]{2430.69, 2418.53, 2394.22, 2433.89},
                new Object[]{2416.62, 2432.4, 2414.4, 2443.03},
                new Object[]{2441.91, 2421.56, 2415.43, 2444.8},
                new Object[]{2420.26, 2382.91, 2373.53, 2427.07},
                new Object[]{2383.49, 2397.18, 2370.61, 2397.94},
                new Object[]{2378.82, 2325.95, 2309.17, 2378.82},
                new Object[]{2322.94, 2314.16, 2308.76, 2330.88},
                new Object[]{2320.62, 2325.82, 2315.01, 2338.78},
                new Object[]{2313.74, 2293.34, 2289.89, 2340.71},
                new Object[]{2297.77, 2313.22, 2292.03, 2324.63},
                new Object[]{2322.32, 2365.59, 2308.92, 2366.16},
                new Object[]{2364.54, 2359.51, 2330.86, 2369.65},
                new Object[]{2332.08, 2273.4, 2259.25, 2333.54},
                new Object[]{2274.81, 2326.31, 2270.1, 2328.14},
                new Object[]{2333.61, 2347.18, 2321.6, 2351.44},
                new Object[]{2340.44, 2324.29, 2304.27, 2352.02},
                new Object[]{2326.42, 2318.61, 2314.59, 2333.67},
                new Object[]{2314.68, 2310.59, 2296.58, 2320.96},
                new Object[]{2309.16, 2286.6, 2264.83, 2333.29},
                new Object[]{2282.17, 2263.97, 2253.25, 2286.33},
                new Object[]{2255.77, 2270.28, 2253.31, 2276.22},
                new Object[]{2269.31, 2278.4, 2250, 2312.08},
                new Object[]{2267.29, 2240.02, 2239.21, 2276.05},
                new Object[]{2244.26, 2257.43, 2232.02, 2261.31},
                new Object[]{2257.74, 2317.37, 2257.42, 2317.86},
                new Object[]{2318.21, 2324.24, 2311.6, 2330.81},
                new Object[]{2321.4, 2328.28, 2314.97, 2332},
                new Object[]{2334.74, 2326.72, 2319.91, 2344.89},
                new Object[]{2318.58, 2297.67, 2281.12, 2319.99},
                new Object[]{2299.38, 2301.26, 2289, 2323.48},
                new Object[]{2273.55, 2236.3, 2232.91, 2273.55},
                new Object[]{2238.49, 2236.62, 2228.81, 2246.87},
                new Object[]{2229.46, 2234.4, 2227.31, 2243.95},
                new Object[]{2234.9, 2227.74, 2220.44, 2253.42},
                new Object[]{2232.69, 2225.29, 2217.25, 2241.34},
                new Object[]{2196.24, 2211.59, 2180.67, 2212.59},
                new Object[]{2215.47, 2225.77, 2215.47, 2234.73},
                new Object[]{2224.93, 2226.13, 2212.56, 2233.04},
                new Object[]{2236.98, 2219.55, 2217.26, 2242.48},
                new Object[]{2218.09, 2206.78, 2204.44, 2226.26},
                new Object[]{2199.91, 2181.94, 2177.39, 2204.99},
                new Object[]{2169.63, 2194.85, 2165.78, 2196.43},
                new Object[]{2195.03, 2193.8, 2178.47, 2197.51},
                new Object[]{2181.82, 2197.6, 2175.44, 2206.03},
                new Object[]{2201.12, 2244.64, 2200.58, 2250.11},
                new Object[]{2236.4, 2242.17, 2232.26, 2245.12},
                new Object[]{2242.62, 2184.54, 2182.81, 2242.62},
                new Object[]{2187.35, 2218.32, 2184.11, 2226.12},
                new Object[]{2213.19, 2199.31, 2191.85, 2224.63},
                new Object[]{2203.89, 2177.91, 2173.86, 2210.58},
                new Object[]{2170.78, 2174.12, 2161.14, 2179.65},
                new Object[]{2179.05, 2205.5, 2179.05, 2222.81},
                new Object[]{2212.5, 2231.17, 2212.5, 2236.07},
                new Object[]{2227.86, 2235.57, 2219.44, 2240.26},
                new Object[]{2242.39, 2246.3, 2235.42, 2255.21},
                new Object[]{2246.96, 2232.97, 2221.38, 2247.86},
                new Object[]{2228.82, 2246.83, 2225.81, 2247.67},
                new Object[]{2247.68, 2241.92, 2231.36, 2250.85},
                new Object[]{2238.9, 2217.01, 2205.87, 2239.93},
                new Object[]{2217.09, 2224.8, 2213.58, 2225.19},
                new Object[]{2221.34, 2251.81, 2210.77, 2252.87},
                new Object[]{2249.81, 2282.87, 2248.41, 2288.09},
                new Object[]{2286.33, 2299.99, 2281.9, 2309.39},
                new Object[]{2297.11, 2305.11, 2290.12, 2305.3},
                new Object[]{2303.75, 2302.4, 2292.43, 2314.18},
                new Object[]{2293.81, 2275.67, 2274.1, 2304.95},
                new Object[]{2281.45, 2288.53, 2270.25, 2292.59},
                new Object[]{2286.66, 2293.08, 2283.94, 2301.7},
                new Object[]{2293.4, 2321.32, 2281.47, 2322.1},
                new Object[]{2323.54, 2324.02, 2321.17, 2334.33},
                new Object[]{2316.25, 2317.75, 2310.49, 2325.72},
                new Object[]{2320.74, 2300.59, 2299.37, 2325.53},
                new Object[]{2300.21, 2299.25, 2294.11, 2313.43},
                new Object[]{2297.1, 2272.42, 2264.76, 2297.1},
                new Object[]{2270.71, 2270.93, 2260.87, 2276.86},
                new Object[]{2264.43, 2242.11, 2240.07, 2266.69},
                new Object[]{2242.26, 2210.9, 2205.07, 2250.63},
                new Object[]{2190.1, 2148.35, 2126.22, 2190.1});
        option.series(k);
		return option.toString();
	}
	
	//k线图 
	private String pie(){
		GsonOption option =new GsonOption();
		 option.timeline().data("2013-01-01", "2013-02-01", "2013-03-01", "2013-04-01", "2013-05-01",
	                new LineData("2013-06-01", "emptyStart6", 8), "2013-07-01", "2013-08-01", "2013-09-01", "2013-10-01",
	                "2013-11-01", new LineData("2013-12-01", "star6", 8));
	      //  option.timeline().autoPlay(true).label().formatter("function(s){return s.slice(0,7);}");
	        //timeline变态的地方在于多个Option
	        Option basic = new Option();
	        basic.title().text("浏览器占比变化").subtext("纯属虚构");
	        basic.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
	        basic.legend().data("Chrome", "Firefox", "Safari", "IE9+", "IE8-");
	        basic.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage, new MagicType(Magic.pie, Magic.funnel)
	                .option(new MagicType.Option().funnel(
	                        new Funnel().x("25%").width("50%").funnelAlign(X.left).max(1548))));

	        int idx = 1;
	        basic.series(new Pie().name("浏览器（数据纯属虚构）").data(
	                new PieData("Chrome", idx * 128 + 80),
	                new PieData("Firefox", idx * 64 + 160),
	                new PieData("Safari", idx * 32 + 320),
	                new PieData("IE9+", idx * 16 + 640),
	                new PieData("IE8-", idx * 8 + 1280)).center("50%", "45%").radius("50%"));
	        //加入
	        option.options(basic);
	        //构造11个数据
	        Option[] os = new Option[11];
	        for (int i = 0; i < os.length; i++) {
	        	
	            os[i] = new Option().series(new Pie().name("浏览器（数据纯属虚构）").data(
	                    new PieData("Chrome", idx * 128 + 80),
	                    new PieData("Firefox", idx * 64 + 160),
	                    new PieData("Safari", idx * 32 + 320),
	                    new PieData("IE9+", idx * 16 + 640),
	                    new PieData("IE8-", idx * 8 + 1280)));
	        }
	        option.options(os);
		
		return option.toString();
	}
	
	private String map(){
		GsonOption option =new GsonOption();
		com.jeegem.common.utils.echarts.series.Map map = new com.jeegem.common.utils.echarts.series.Map("Map");
		map.mapLocation(new MapLocation(X.left, Y.top, 500));
		map.selectedMode(SelectedMode.multiple);
		map.itemStyle().normal().borderWidth(2).borderColor("lightgreen").color("orange").label().show(true);
		map.itemStyle().emphasis().borderWidth(2).borderColor("#fff").color("#32cd32").label().show(true).textStyle().color("#fff");
		Data data = new Data("广东");
		data.value(Math.round(Math.random() * 1000));
		data.itemStyle().normal().color("#32cd32").label().show(true).textStyle().color("#fff").fontSize(15);
		data.itemStyle().emphasis().borderColor("yellow").color("#cd5c5c").label().show(false).textStyle().color("blue");
		map.data(data);
		map.markPoint().itemStyle().normal().color("skyblue");
		map.markPoint().data(new Data("天津", 3), new Data("上海", 1));
		map.geoCoord("上海", "121.4648", "31.2891").geoCoord("天津", "117.4219", "39.4189");
		option.series(map);
		return option.toString();
	}
}
