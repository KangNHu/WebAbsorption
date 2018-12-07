package tech.liveeasy.spider.webmagic.config;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import redis.clients.jedis.JedisPool;import redis.clients.jedis.JedisPoolConfig;@Configurationpublic class RedisConfiguration extends AbstractConfiguration {    private Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);    private static final String  HOST= "spring.redis.host";    private static final String  PORT= "spring.redis.port";    private static final String  TIMEOUT= "spring.redis.timeout";    private static final String  PASSWORD= "spring.redis.password";    private static final String  DATABASE="spring.redis.database";    private static final String  MAX_WAIT= "redis.pool.max-wait";    private static final String  MAX_IDLE= "redis.pool.max-idle";    private static final String  MIN_IDLE= "redis.pool.min-idle";    @Bean    public JedisPool redisPoolFactory() {        logger.info("JedisPool注入成功！！");        logger.info("redis地址：" + getValue(HOST) + ":" + getValue(PORT));        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();        jedisPoolConfig.setMaxIdle(getValue(MAX_IDLE , Integer.class));        jedisPoolConfig.setMaxWaitMillis(getValue(MAX_WAIT , Long.class));        jedisPoolConfig.setMinIdle(getValue(MIN_IDLE , Integer.class));        JedisPool jedisPool = new JedisPool(jedisPoolConfig, getValue(HOST), getValue(PORT , Integer.class), getValue(TIMEOUT , Integer.class), getValue(PASSWORD),getValue(DATABASE,Integer.class));        return jedisPool;    }}