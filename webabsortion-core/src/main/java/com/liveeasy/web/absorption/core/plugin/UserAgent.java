package com.liveeasy.web.absorption.core.plugin;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import java.io.*;import java.util.ArrayList;import java.util.List;import java.util.Random;import java.util.concurrent.locks.ReentrantReadWriteLock;/** * user-agent文件加载器 */public class UserAgent {    private static Logger logger = LoggerFactory.getLogger(UserAgent.class);    private static final String AGENT_FILE_PATH= "user-agent/User-Agents.txt";    private static UserAgent userAgent;    private static List<String> agents;    private  UserAgent(){}    public static UserAgent newInstance(){            if(null != userAgent){                return userAgent;            }            synchronized (logger){                if(null == userAgent){                    userAgent = new UserAgent();                }            }            return userAgent;    }    static {        agents = new ArrayList<>();        InputStream resourceAsStream = null;        InputStreamReader inputStreamReader = null;        BufferedReader bufferedReader = null;        try {            resourceAsStream = UserAgent.class.getClassLoader().getResourceAsStream(AGENT_FILE_PATH);            inputStreamReader = new InputStreamReader(resourceAsStream , "utf-8");            bufferedReader = new BufferedReader(inputStreamReader);            String len = null;            while ((len = bufferedReader.readLine()) != null){                if(!len.matches("^#.*")){                    agents.add(len.trim());                }            }            logger.info("user-agent file Loaded successfully");        }catch (Exception e){            logger.error("Failed to load user-agent file" , e);        }finally {            if(null != bufferedReader){                try {                    bufferedReader.close();                } catch (IOException e) {                    logger.error("Failed to close bufferedReader" , e);                }            }            if(null != inputStreamReader){                try {                    inputStreamReader.close();                } catch (IOException e) {                    logger.error("Failed to close inputStreamReader" , e);                }            }            if(null != resourceAsStream){                try {                    resourceAsStream.close();                } catch (IOException e) {                    logger.error("Failed to close resourceAsStream" , e);                }            }        }    }    public  List<String> getAgents() {        return this.agents;    }}