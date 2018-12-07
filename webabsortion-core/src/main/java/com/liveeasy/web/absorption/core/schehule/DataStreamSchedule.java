package com.liveeasy.web.absorption.core.schehule;import com.liveeasy.web.absorption.core.pipeline.DataStreamPipeline;import java.util.Collection;public interface DataStreamSchedule {    void stop();    void start();    void addDataStreamUrl(String suffix , String url);    void addDataStreamUrl(String suffix , Collection<String> urls);    boolean isClose();    void setDataStreamPipeline(DataStreamPipeline dataStreamPipeline);    DataStreamPipeline getDataStreamPipeline();}