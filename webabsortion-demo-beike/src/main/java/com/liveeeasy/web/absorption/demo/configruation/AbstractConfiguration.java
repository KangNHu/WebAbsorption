package com.liveeeasy.web.absorption.demo.configruation;import org.springframework.context.EnvironmentAware;import org.springframework.core.env.Environment;public class AbstractConfiguration implements EnvironmentAware{    private Environment environment;    public void setEnvironment(Environment environment) {        this.environment = environment;    }    public String getValue(String key){        return getValue(key , String.class);    }    public <T> T getValue(String key , Class<T> tClass){        return environment.getProperty(key , tClass);    }    public Environment getEnvironment(){        return this.environment;    }}