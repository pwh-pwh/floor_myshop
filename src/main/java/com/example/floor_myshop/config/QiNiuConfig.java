package com.example.floor_myshop.config;

import com.qiniu.common.Zone;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author tong
 * @date 2021/12/24
 */
@Data
@Slf4j
public class QiNiuConfig {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private Zone zone;
    private String domainOfBucket;
    private long expireInSeconds;

    private static QiNiuConfig instance = new QiNiuConfig();

    private QiNiuConfig(){
        Properties prop = new Properties();
        try {
            prop.load(QiNiuConfig.class.getResourceAsStream("/qiniu.properties"));
            accessKey = prop.getProperty("qiniu.access-key");
            secretKey = prop.getProperty("qiniu.secret-key");
            bucket = prop.getProperty("qiniu.bucket");
            domainOfBucket = prop.getProperty("qiniu.domain-of-bucket");
            expireInSeconds = Long.parseLong(prop.getProperty("qiniu.expire-in-seconds"));
            String zoneName = prop.getProperty("qiniu.zone");
            if(zoneName.equals("zone0")){
                zone = Zone.zone0();
            }else if(zoneName.equals("zone1")){
                zone = Zone.zone1();
            }else if(zoneName.equals("zone2")){
                zone = Zone.zone2();
            }else{
                throw new Exception("Zone对象配置错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QiNiuConfig getInstance(){
        return instance;
    }
}
