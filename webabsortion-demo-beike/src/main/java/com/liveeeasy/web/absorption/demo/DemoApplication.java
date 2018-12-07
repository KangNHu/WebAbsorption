package tech.liveeasy.spider.webmagic;

import com.alibaba.fastjson.JSON;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import tech.liveeasy.spider.spider.service.ISpiderService;
import tech.liveeasy.spider.webmagic.common.utils.SpringUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.Map;

@SpringBootApplication
@EnableScheduling
@ComponentScan(value = "tech.liveeasy" )
@MapperScan("tech.liveeasy.db")
public class RadiateApplication {






    /**
     *
     * @param args
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(RadiateApplication.class, args);
        run();
    }
    //@Bean
    //@Order(Ordered.HIGHEST_PRECEDENCE)
    public SpringUtils springUtils(){
        return new SpringUtils();
    }


    public static void run() throws CloneNotSupportedException {
        ISpiderService spiderService = SpringUtils.getBean(ISpiderService.class);
        Map map = JSON.parseObject("{\n" +
                "    \"spiderName\": \"propertySpider\",\n" +
                "    \"proxyAddresses\": \"\",\n" +
                "    \"thread\": 2,\n" +
                "    \"startUrl\": \"https://wh.58.com/ershoufang/0/\",\n" +
                "    \"rule\": {\n" +
                "        \"pageUrlRule\": \"https://wh.58.com/ershoufang/0/pn[0-9]+/.*?\",\n" +
                "        \"elementUrlRule\": \"https://wh.58.com/ershoufang/[0-9]+x.shtml?.*\",\n" +
                "        \"fields\": [\n" +
                "            {\n" +
                "                \"criteria\": [\n" +
                "                    {\n" +
                "                        \"ruleType\": \"xpath\",\n" +
                "                        \"expression\": \"//div[@class='house-chat-phone']//p[@class='phone-num']/text()\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"fieldName\": \"phone\",\n" +
                "                \"type\": \"string\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"criteria\": [\n" +
                "                    {\n" +
                "                        \"ruleType\": \"xpath\",\n" +
                "                        \"expression\": \"//div[@class='house-title']/h1/text()\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"fieldName\": \"title\",\n" +
                "                \"type\": \"string\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}", Map.class);
       //spiderService.startSpider(map);
        System.out.println("");
    }
}
