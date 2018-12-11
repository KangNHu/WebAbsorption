package com.liveeasy.web.absorption.core.plugin;import com.liveeasy.web.absorption.core.schehule.DataResultItemsSchedule;import com.liveeasy.web.absorption.core.schehule.DataSchedule;import com.liveeasy.web.absorption.core.schehule.DataStreamSchedule;import us.codecraft.webmagic.*;import us.codecraft.webmagic.proxy.Proxy;import java.util.List;/** * 下载插件接口 */public interface DownloaderPlugin extends SpiderListener {    /**     * 下载前置操作回调     * @param site     * @param userAgents user-agent集合     */    void downloadPreposition(Site site , List<String> userAgents);    /**     * 下载后置操作即下载成功回调     * @param request     */    void downloadPostPosition(Request request);    /**     * 下载错误回调     * @param request     */    void downloadError(Request request);    /**     * 下载后置执行错误回调     * @param request     */    void downloadPostError(Request request);    /**     * 图片下载错误回调     * @param url 图片url     * @param dataSchedule     */    void picDownloaderError(String url, DataStreamSchedule dataSchedule);    /**     * 异步管道，数据持久化错误回调     * @param resultItems     * @param dataSchedule     */    void dataPersistenceError(List<ResultItems> resultItems , DataResultItemsSchedule dataSchedule);    /**     * ip代理回调     * @param task     * @return     */    Proxy deployProxy(Task task);    /**     * ip代理资源释放回调     * @param proxy     * @param page     * @param task     * @return     */    Proxy returnProxy(Proxy proxy, Page page, Task task);    /**     * 设置当前Spider     * @param task     */    void setTask(Task task);}