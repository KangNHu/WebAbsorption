package com.liveeasy.web.absorption.core.schehule;import com.liveeasy.web.absorption.core.pipeline.WebAbsorptionPipeline;import us.codecraft.webmagic.ResultItems;import java.util.Iterator;import java.util.concurrent.BlockingQueue;import java.util.concurrent.LinkedBlockingQueue;public class QueueDataResultItemsSchedule extends AbstractDataResultItemsSchedule{    private BlockingQueue<ResultItems> blockingQueue = new LinkedBlockingQueue();    public QueueDataResultItemsSchedule(){        super();    }    public BlockingQueue getBlockingQueue(){        return blockingQueue;    }    @Override    protected ResultItems getResultItems() {        return blockingQueue.poll();    }    @Override    public void addElement(ResultItems element) {            blockingQueue.add(element);    }}