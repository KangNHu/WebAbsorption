package com.liveeasy.web.absorption.core.schehule;import com.liveeasy.web.absorption.core.pipeline.DataStreamPipeline;import com.liveeasy.web.absorption.core.pipeline.WebAbsorptionPipeline;import us.codecraft.webmagic.ResultItems;import us.codecraft.webmagic.Task;import java.util.Collection;import java.util.Iterator;import java.util.List;public abstract class AbstractDataResultItemsSchedule extends AbstractDataSchedule<ResultItems>{    protected List<WebAbsorptionPipeline> webAbsorptionPipelines;    public AbstractDataResultItemsSchedule(){        super();    }    @Override    protected void execute() {        ResultItems resultItems = getResultItems();        if(null != resultItems) {            try {                for (Iterator<WebAbsorptionPipeline> iterator = webAbsorptionPipelines.iterator(); iterator.hasNext(); ) {                    WebAbsorptionPipeline webAbsorptionPipeline = iterator.next();                    if (null != webAbsorptionPipeline) {                        webAbsorptionPipeline.pipeline(resultItems, task);                    }                }            } catch (Exception e) {                downloaderPlugin.dataPersistenceError(resultItems, this);            }        }    }    protected abstract ResultItems getResultItems();    @Override    public void setWebAbsorptionPipelines(List<WebAbsorptionPipeline> webAbsorptionPipelines) {        this.webAbsorptionPipelines = webAbsorptionPipelines;    }    public List<WebAbsorptionPipeline> getWebAbsorptionPipelines() {        return webAbsorptionPipelines;    }    @Override    public void setDataStreamPipeline(DataStreamPipeline dataStreamPipeline) {    }    @Override    public DataStreamPipeline getDataStreamPipeline() {        return null;    }    @Override    public void addElements(Collection<ResultItems> elements) {    }}