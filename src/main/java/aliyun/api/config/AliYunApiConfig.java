package aliyun.api.config;

import org.apache.commons.lang3.StringUtils;

public class AliYunApiConfig {
    private String appKey;
    private String appSecret;
    private String host;
    private String level;
    private Integer connectionTimeOut;
    private Integer readTimeOut;
    private Integer writeTimeOut;

    public String getAppKey() {
        if (StringUtils.isBlank(appKey)){
            throw new IllegalArgumentException("appKey is blank, please set appKey");
        }
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        if (StringUtils.isBlank(appSecret)){
            throw new IllegalArgumentException("appSecret is blank, please set appSecret");
        }
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getHost() {
        if (StringUtils.isBlank(host)){
            throw new IllegalArgumentException("aliYun api host is blank, please set api host");
        }
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getConnectionTimeOut() {
        if (null == connectionTimeOut || connectionTimeOut <= 0){
            setConnectionTimeOut(10);
        }
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(Integer connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public Integer getReadTimeOut() {
        if (null == readTimeOut || readTimeOut <= 0){
            setReadTimeOut(10);
        }
        return readTimeOut;
    }

    public void setReadTimeOut(Integer readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public Integer getWriteTimeOut() {
        if (null == writeTimeOut || readTimeOut <= 0){
            setWriteTimeOut(20);
        }
        return writeTimeOut;
    }

    public void setWriteTimeOut(Integer writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
