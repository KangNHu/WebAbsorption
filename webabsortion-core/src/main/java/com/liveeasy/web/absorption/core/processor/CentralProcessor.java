package com.liveeasy.web.absorption.core.processor;import com.liveeasy.web.absorption.core.factory.Product;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import us.codecraft.webmagic.Page;import us.codecraft.webmagic.Site;import us.codecraft.webmagic.processor.PageProcessor;import java.util.Iterator;import java.util.List;public  class CentralProcessor implements PageProcessor , Product{    private List<WebAbsorptionProcess> webAbsorptionProcesses;    private Site site;    @Override    public void process(Page page) {        for (Iterator<WebAbsorptionProcess> iterator = webAbsorptionProcesses.iterator() ; iterator.hasNext() ;){            WebAbsorptionProcess webAbsorptionProcess = iterator.next();            if(null != webAbsorptionProcess){                if(webAbsorptionProcess.process(page)){                    break;                }            }        }    }    @Override    public Site getSite() {        return this.site;    }    public CentralProcessor setWebAbsorptionProcess(List<WebAbsorptionProcess> webAbsorptionProcesses){        this.webAbsorptionProcesses = webAbsorptionProcesses;        return this;    }    public CentralProcessor setSite(Site site){        this.site = site;        return this;    }    @Override    public Product createClone() throws CloneNotSupportedException {            return (Product) super.clone();    }}