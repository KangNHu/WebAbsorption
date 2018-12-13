package com.liveeeasy.web.absorption.demo.spider.wuba;import com.alibaba.fastjson.JSON;import com.alibaba.fastjson.JSONArray;import com.alibaba.fastjson.JSONObject;import com.liveeasy.web.absorption.core.annotation.SPI;import com.liveeasy.web.absorption.core.pipeline.SimpleWebAbsorptionPipeline;import com.liveeeasy.web.absorption.demo.spider.wuba.service.IWuBaEstateService;import org.apache.commons.lang3.StringUtils;import us.codecraft.webmagic.ResultItems;import us.codecraft.webmagic.Task;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;@SPI("wuBaSpider")public class WuBaPipeline extends SimpleWebAbsorptionPipeline {    private IWuBaEstateService estateService;    public void setEstateService(IWuBaEstateService estateService){        this.estateService = estateService;    }    @Override    public void lastPipeline(ResultItems resultItem, Task task) {    }    @Override    public void pipeline(List<ResultItems> resultItems, Task task) {        List<Map<String ,Object>> baseMaps = new ArrayList<>();        List<Map<String ,Object>> picMaps = new ArrayList<>();        List<Map<String ,Object>> expertMaps = new ArrayList<>();        for (int i = 0 , length = resultItems.size() ; i < length ; i++){            ResultItems resultItem = resultItems.get(i);            Map<String, Object> all = resultItem.getAll();            String type = (String) all.get("type");            switch (type){                case "data_pic" :                    Object imagesJson = all.get("images");                    JSONObject jsonObject = JSON.parseObject(imagesJson.toString());                    JSONObject data = jsonObject.getJSONObject("data");                    if(StringUtils.isEmpty(jsonObject.getString("data"))){                        break;                    }                    JSONArray huxingtu = data.getJSONArray("huxingtu");                    StringBuffer images = new StringBuffer();                    if(null != huxingtu){                        for (int j = 0 , size = huxingtu.size() ; j < size ; j++){                            if(j > 0){                                images.append(",");                            }                            images.append(huxingtu.getJSONObject(j).getString("picurl"));                        }                    }                    JSONArray shijingtu = data.getJSONArray("shijingtu");                    if(null != shijingtu){                        for (int j = 0 , size = shijingtu.size() ; j < size ; j ++){                            images.append(",");                            images.append(shijingtu.getJSONObject(j).getString("picurl"));                        }                    }                    all.put("urls" , images.toString());                    picMaps.add(all);                    break;                case "data_base" :                    baseMaps.add(all);                    break;                case "data_expert":                    String expertJson = (String) all.get("expert_interpretation");                    JSONObject expert = JSON.parseObject(expertJson);                    JSONObject data1 = expert.getJSONObject("data");                    if (StringUtils.isEmpty(expert.getString("data"))){                        break;                    }                    JSONArray expertList = data1.getJSONArray("expertList");                    Map<String , Object> map = new HashMap();                    for (int j = 0 , size = expertList.size() ; j < size ; j ++){                        JSONObject jsonObject1 = expertList.getJSONObject(i);                        String expert1 = jsonObject1.getString("expert");                        map.put("expert_interpretation" , expert1);                        map.put("infoId" , all.get("infoId"));                        expertMaps.add(map);                    }                    break;                default:            }        }        estateService.BaseBatchInsert(baseMaps);        estateService.expertBatchInsert(expertMaps);        estateService.picBatchInsert(picMaps);    }}