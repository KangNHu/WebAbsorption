package com.liveeeasy.web.absorption.demo.spider;import com.liveeasy.web.absorption.core.plugin.SimpleDownloaderPlugin;import com.liveeasy.web.absorption.core.schehule.DataResultItemsSchedule;import com.liveeasy.web.absorption.core.schehule.DataStreamSchedule;import com.liveeasy.web.absorption.core.schehule.RedisDataResultItemsSchedule;import com.liveeasy.web.absorption.core.utils.ProxyUtils;import org.apache.http.HttpResponse;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.data.redis.core.RedisTemplate;import redis.clients.jedis.Jedis;import redis.clients.jedis.JedisPool;import us.codecraft.webmagic.*;import us.codecraft.webmagic.proxy.Proxy;import java.util.List;import java.util.Random;public class ProxyDownloaderPlugin extends SimpleDownloaderPlugin{    private static Logger logger = LoggerFactory.getLogger(ProxyDownloaderPlugin.class);    private RedisTemplate<String , String> redisTemplate;    private HttpAPIService httpAPIService;    private int testTimeout;    private String order;    private ProxyInfo proxyInfo;    public void setOrder(String order){        this.order = order;    }    public void setTestTimeout(int testTimeout){        this.testTimeout = testTimeout;    }    public void setHttpAPIService(HttpAPIService httpAPIService){        this.httpAPIService = httpAPIService;    }    public void setRedisTemplate(RedisTemplate redisTemplate){        this.redisTemplate = redisTemplate;    }    /**     * 下载前置回调     * @param site     * @param userAgents     */    @Override    public void downloadPreposition(Site site, List<String> userAgents) {        Random random = new Random();        String userAgent = userAgents.get(random.nextInt(userAgents.size()));        site.setUserAgent(userAgent);    }    /**     * 下载后置处理回调     * @param request     */    @Override    public void downloadPostPosition(Request request) {    }    /**     * 下载错误回调     * @param request     */    @Override    public void downloadError(Request request) {        if(null !=  task){            redoQueue(request.getUrl());        }    }    @Override    public void responseCodeError(Request request, HttpResponse httpResponse) {        if(null != task){            redoQueue(request.getUrl());        }    }    /**     * 下载后置错误回调     * @param request     */    @Override    public void downloadPostError(Request request) {        if(null != task){            redoQueue(request.getUrl());        }    }    /**     * 图片下载错误回调     * @param url     * @param dataSchedule     */    @Override    public void picDownloaderError(String url, DataStreamSchedule dataSchedule) {    }    /**     * 异步管道处理错误回调     * @param resultItems     * @param dataSchedule     */    @Override    public void dataPersistenceError(List<ResultItems> resultItems, DataResultItemsSchedule dataSchedule) {        RedisDataResultItemsSchedule schedule = (RedisDataResultItemsSchedule) dataSchedule;        schedule.redoQueueAll(resultItems);    }    /**     * 动态ip代理回调     * @param task     * @return     */    @Override    public Proxy deployProxy(Task task) {       if(null != proxyInfo && proxyInfo.isInvalid()){            return proxyInfo.getProxy();        }        synchronized (this) {            if (null == proxyInfo) {                proxyInfo = getProxyInfo();            } else if (!proxyInfo.isInvalid()) {                proxyInfo = getProxyInfo();            }        }        return proxyInfo.getProxy();    }    /**     * 动态ip代理资源释放回调     * @param proxy     * @param page     * @param task     * @return     */    @Override    public Proxy returnProxy(Proxy proxy, Page page, Task task) {        return null;    }    /**     *重入队列     * @param url     */    private void redoQueue(String url){        redisTemplate.opsForSet().remove("set_" + task.getUUID(), url);        redisTemplate.opsForList().leftPush("queue_" + task.getUUID(), url);    }    private ProxyInfo getProxyInfo() {        ProxyInfo proxyInfo = null;        do {            try {                String respond = httpAPIService.doGet("http://dynamic.goubanjia.com/dynamic/get/" + order + ".html?sep=6&ttl=1");                if (null != respond) {                    String[] split = respond.split(";");                    for (int i = 0, length = split.length; i < length; i++) {                        String address = split[i];                        if (!"".equals(address)) {                            String[] split1 = address.split(",");                            String[] split2 = split1[0].split(":");                            long availability = Long.parseLong(split1[1]);                            Proxy proxy = new Proxy(split2[0], Integer.parseInt(split2[1]));                            if (ProxyUtils.isAvailability(proxy, testTimeout)) {                                proxyInfo = new ProxyInfo(System.currentTimeMillis(), availability, proxy);                            }                        }                    }                }            } catch (Exception e) {                logger.error("获取ip失败" , e);            }        }while (proxyInfo == null);        return proxyInfo;    }}