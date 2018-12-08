package com.liveeeasy.web.absorption.demo.spider;import com.liveeasy.web.absorption.core.annotation.SPI;import com.liveeasy.web.absorption.core.processor.AbstractWebAbsorptionProcessor;import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.LocalScoping;import org.apache.commons.lang3.StringUtils;import us.codecraft.webmagic.Page;import us.codecraft.webmagic.utils.UrlUtils;import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;@SPI("beiKeSpider")public class BeiKeProcessor extends AbstractWebAbsorptionProcessor {    public boolean process(Page page) {        if(page.getRequest().getUrl().equals("https://wh.ke.com/xiaoqu/")) {            List<String> all = page.getHtml().xpath("//div[@data-role='ershoufang']//a/@href").all();            if (null != all) {                final String domain = UrlUtils.getDomain(page.getRequest().getUrl());                page.addTargetRequests(all.stream().map(url -> "https://" + domain + url).collect(Collectors.toList()));            }        }        if(page.getRequest().getUrl().matches("https://wh.ke.com/xiaoqu/[a-z]+/") ||                page.getRequest().getUrl().matches("https://wh.ke.com/xiaoqu/[a-z]/pg[0-9]+/")){            if(page.getRequest().getUrl().matches("https://wh.ke.com/xiaoqu/[a-z]+/")){                String total = page.getHtml().xpath("//h2[@class='total fl']/span/text()").get();                if(!StringUtils.isEmpty(total) && !total.trim().equals("")){                    List<String> urls = new ArrayList<>();                    int totalNum= Integer.parseInt(total);                    int totalPage = (totalNum % 30 == 0 ? totalNum /30 : totalNum/30 +1);                    for (int i = 0 ; i < totalPage ; i ++){                        urls.add(page.getRequest().getUrl() + String.format("/pg%s/" , i));                    }                    page.addTargetRequests(urls);                }            }                page.addTargetRequests(page.getHtml().xpath("//div[@data-component='list']//li/a/@href").all());        }        if(page.getRequest().getUrl().matches("https://wh.ke.com/xiaoqu/[0-9]+/")){                    page.getHtml().xpath("//div[@class='title']/h1/@title").get();                    page.getHtml().xpath("//div[@class='xiaoquInfo']//div[5]/span[@class='xiaoquInfoContent']/text()");                    page.getHtml().xpath("//div[@class='fl l-txt']/a[3]/text()").replace("小区" , "").get();            String s = page.getHtml().xpath("//div[@class='fl l-txt']/a[4]/text()").get();        }        return true;    }}