package com.liveeeasy.web.absorption.demo.spider;import com.liveeasy.web.absorption.core.plugin.SimpleDownloaderPlugin;import us.codecraft.webmagic.Page;import us.codecraft.webmagic.Request;import us.codecraft.webmagic.Site;import us.codecraft.webmagic.Task;import us.codecraft.webmagic.proxy.Proxy;import java.util.List;import java.util.Random;public class ProxyDownloaderPlugin extends SimpleDownloaderPlugin{    public void downloadPreposition(Site site, List<String> userAgents) {        Random random = new Random();        String userAgent = userAgents.get(random.nextInt(userAgents.size()));        site.setUserAgent(userAgent);    }    public void downloadPostPosition(Request request) {    }    public void downloadError(Request request, Task task) {    }    public void processError(Request request) {    }    public Proxy deployProxy(Task task) {        return null;    }    public Proxy returnProxy(Proxy proxy, Page page, Task task) {        return null;    }}