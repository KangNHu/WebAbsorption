package com.liveeasy.web.absorption.core.schehule;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import java.io.DataInputStream;import java.io.IOException;import java.net.URL;public class ByteData {    private Logger logger = LoggerFactory.getLogger(ByteData.class);    private URL url;    private DataInputStream dataInputStream;    public URL getUrl() {        return url;    }    public void setUrl(URL url) {        this.url = url;    }    public DataInputStream getDataInputStream() {        return dataInputStream;    }    public void setDataInputStream(DataInputStream dataInputStream) {        this.dataInputStream = dataInputStream;    }    public void close(){        if(null != dataInputStream){            try {                dataInputStream.close();            } catch (IOException e) {                logger.error("关闭流失败");            }        }    }}