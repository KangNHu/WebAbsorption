package com.liveeasy.web.absorption.core.pipeline;import com.liveeasy.web.absorption.core.WebAbsorption;import com.liveeasy.web.absorption.core.factory.Product;import com.liveeasy.web.absorption.core.schehule.AbstractDataResultItemsSchedule;import com.liveeasy.web.absorption.core.schehule.DataResultItemsSchedule;import com.liveeasy.web.absorption.core.schehule.DataSchedule;import com.liveeasy.web.absorption.core.schehule.DataStreamSchedule;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import us.codecraft.webmagic.ResultItems;import us.codecraft.webmagic.Task;import us.codecraft.webmagic.pipeline.Pipeline;import java.util.Iterator;import java.util.List;/** * WebAbsorption 中央管道类 */public  class CentralPipeline implements Pipeline , Product {    private Logger logger = LoggerFactory.getLogger(CentralPipeline.class);    private DataStreamSchedule<String> dataStreamSchedule;    private DataResultItemsSchedule<ResultItems> dataResultItemsSchedule;    private boolean isAsync;    public CentralPipeline setAsync(boolean isAsync){        this.isAsync = isAsync;        return this;    }    public CentralPipeline setDataStreamSchedule(DataStreamSchedule dataStreamSchedule){        this.dataStreamSchedule = dataStreamSchedule;        return this;    }    public CentralPipeline setDataResultItemsSchedule(DataResultItemsSchedule dataResultItemsSchedule){        this.dataResultItemsSchedule = dataResultItemsSchedule;        return this;    }    @Override    public void process(ResultItems resultItems, Task task) {        DataStreamPipeline dataStreamPipeline = dataStreamSchedule.getDataStreamPipeline();        List<WebAbsorptionPipeline> webAbsorptionPipelines = dataResultItemsSchedule.getWebAbsorptionPipelines();        if(null != dataStreamPipeline){            dataStreamSchedule.setTask(task);            List<String> dataStreamUrls = dataStreamPipeline.getDataStreamUrl(resultItems, task);            dataStreamSchedule.addElements(dataStreamUrls);        }        if(isAsync){            dataResultItemsSchedule.setTask(task);            if(!resultItems.getAll().isEmpty()) {                dataResultItemsSchedule.addElement(resultItems);            }        }else {            for (int i = 0 , length = webAbsorptionPipelines.size() ; i < length ; i++){                WebAbsorptionPipeline webAbsorptionPipeline = webAbsorptionPipelines.get(i);                if (null != webAbsorptionPipeline) {                    webAbsorptionPipeline.lastPipeline(resultItems, task);                }            }        }    }    @Override    public Product createClone() {        try {            return (Product) super.clone();        } catch (CloneNotSupportedException e) {            logger.error("createClone :" , e);        }        return null;    }}