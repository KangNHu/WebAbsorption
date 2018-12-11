package com.liveeasy.web.absorption.core.pipeline;import us.codecraft.webmagic.ResultItems;import us.codecraft.webmagic.Task;import java.io.InputStream;import java.util.List;/** * WebAbsorption 数据流管道接口 */public interface DataStreamPipeline extends WebAbsorptionPipeline{    /**     * 数据流管道入口     * @param inputStream 数据流     */    void streamPipeline(InputStream inputStream);    /**     * 获取数据流Url集合     * @param resultItems 结果集     * @param task 任务     * @return     */    List<String> getDataStreamUrl(ResultItems resultItems, Task task);}