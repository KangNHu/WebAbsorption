package com.liveeasy.web.absorption.core.schehule;import com.liveeasy.web.absorption.core.pipeline.WebAbsorptionPipeline;import com.liveeasy.web.absorption.core.plugin.DownloaderPlugin;import us.codecraft.webmagic.Task;import java.util.Collection;import java.util.List;/** * 数据处理接口 * @param <T> */public interface DataSchedule<T> {    /**     * 关闭执行器     */    void stop();    /**     * 启动执行器     */    void start();    /**     * 添加元素     * @param element     */    void addElement(T element);    /**     * 添加元素集     * @param elements     */    void addElements( List<T> elements);    /**     * 设置下载插件     * @param downloaderPlugin     */    void setDownloaderPlugin(DownloaderPlugin downloaderPlugin);    /**     * 设置执行器的间隔事件     * @param millisecond     */    void setInterval(Long millisecond);    /**     * 判断执行器是否关闭     * @return     */    boolean isClose();    /**     * 设置全局爬虫对象     * @param task     */    void setTask(Task task);}