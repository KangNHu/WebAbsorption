package com.liveeeasy.web.absorption.demo.spider.service;import com.liveeasy.web.absorption.core.WebAbsorption;import com.liveeasy.web.absorption.core.pipeline.WebAbsorptionPipeline;import com.liveeasy.web.absorption.core.schehule.RedisDataResultItemsSchedule;import com.liveeasy.web.absorption.core.schehule.RedisDataStreamSchedule;import com.liveeeasy.web.absorption.demo.spider.BeiKePipeline;import com.liveeeasy.web.absorption.demo.spider.HttpAPIService;import com.liveeeasy.web.absorption.demo.spider.ProxyDownloaderPlugin;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.redis.core.RedisTemplate;import org.springframework.stereotype.Service;import redis.clients.jedis.JedisPool;import us.codecraft.webmagic.scheduler.RedisScheduler;import java.util.Iterator;import java.util.List;import java.util.concurrent.LinkedBlockingQueue;@Servicepublic class SpiderService {        @Autowired        private JedisPool jedisPool;        @Autowired        private RedisTemplate<String , String> redisTemplate;        @Autowired        private HttpAPIService httpAPIService;        @Autowired        private IEstateService estateService;        public void start() throws IllegalAccessException {            ProxyDownloaderPlugin plugin = new ProxyDownloaderPlugin();            plugin.setJedisPool(jedisPool);            plugin.setHttpAPIService(httpAPIService);            plugin.setTestTimeout(1000);            plugin.setOrder("47fa8bed63f5f92185b6d7e000548749");            RedisDataResultItemsSchedule redisDataResultItemsSchedule = new RedisDataResultItemsSchedule(redisTemplate);            redisDataResultItemsSchedule.setInterval(60000l);            redisDataResultItemsSchedule.setBatchSize(500);            WebAbsorption webAbsorption = WebAbsorption                    .newInstance()                    .setThreadNum(3)                    .isAsyncPipeline(true)                    .setID("BeiKe_Estate")                    .setDataResultItemsSchedule(redisDataResultItemsSchedule)                    .setDataStreamSchedule(new RedisDataStreamSchedule(redisTemplate))                    .setScheduler(new RedisScheduler(jedisPool))                    .setDownloaderPlugin(plugin)                    .builder("beiKeSpider", "https://wh.ke.com/xiaoqu/");            for (Iterator<WebAbsorptionPipeline> iterator = webAbsorption.getWebAbsorptionPipelines().iterator() ; iterator.hasNext() ;){                WebAbsorptionPipeline webAbsorptionPipeline = iterator.next();                if(webAbsorptionPipeline instanceof BeiKePipeline){                    BeiKePipeline beiKePipeline = (BeiKePipeline) webAbsorptionPipeline;                    beiKePipeline.setEstateService(estateService);                    beiKePipeline.setRedisTemplate(redisTemplate);                }            }            webAbsorption.run();        }}