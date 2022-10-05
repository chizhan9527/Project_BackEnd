package com.backend.videoproject_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

    @Value("${tencentcloud.sms.secretId}")
    private String secretId;

    @Value("${tencentcloud.sms.secretKey}")
    private String secretKey;

    @Value("${tencentcloud.sms.appId}")
    private String appId;

    @Value("${tencentcloud.sms.appkey}")
    private String appkey;

    @Value("${tencentcloud.sms.smsSign}")
    private String smsSign;

    @Value("${tencentcloud.sms.templateId}")
    private String templateId;

    @Value("${tencentcloud.sms.expireTime}")
    private String expireTime;

    public SmsConfig() {
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSmsSign() {
        return smsSign;
    }

    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
