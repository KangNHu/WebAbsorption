package com.liveeasy.web.absorption.core.processor;import us.codecraft.webmagic.Site;public abstract class AbstractWebAbsorptionProcessor implements WebAbsorptionProcess {    @Override    public WebAbsorptionProcess createClone() throws CloneNotSupportedException {        return (WebAbsorptionProcess) super.clone();    }}