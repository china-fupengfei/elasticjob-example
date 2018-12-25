package com.kaka.elastic.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "zookeeper")
@PropertySource("classpath:zk.properties")
public class RegistryCenterConfig {
    /**
     * 连接Zookeeper服务器的列表 包括IP地址和端口号 多个地址用逗号分隔 host1:2181,host2:2181
     */
    private String serverList;
    /**
     * Zookeeper的命名空间
     */
    private String namespace;
    /**
     * 等待重试的间隔时间的初始值 单位：毫秒 默认 1000
     */
    private int baseSleepTimeMilliseconds;
    /**
     * 等待重试的间隔时间的最大值 单位：毫秒 默认 1000
     */
    private int maxSleepTimeMilliseconds;
    /**
     * 最大重试次数 默认 3
     */
    private int maxRetries;

    /**
     * sessionTimeoutMilliseconds 会话超时时间 单位：毫秒 默认：60000
     * connectionTimeoutMilliseconds 连接超时时间 单位：毫秒 默认：15000
     * digest 连接Zookeeper的权限令牌 缺省为不需要权限验证
     */

    private static final Logger logger = LoggerFactory.getLogger(RegistryCenterConfig.class);

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverList, namespace);
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(baseSleepTimeMilliseconds);
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(maxSleepTimeMilliseconds);
        zookeeperConfiguration.setMaxRetries(maxRetries);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

    public String getServerList() {
        return serverList;
    }

    public void setServerList(String serverList) {
        this.serverList = serverList;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int getBaseSleepTimeMilliseconds() {
        return baseSleepTimeMilliseconds;
    }

    public void setBaseSleepTimeMilliseconds(int baseSleepTimeMilliseconds) {
        this.baseSleepTimeMilliseconds = baseSleepTimeMilliseconds;
    }

    public int getMaxSleepTimeMilliseconds() {
        return maxSleepTimeMilliseconds;
    }

    public void setMaxSleepTimeMilliseconds(int maxSleepTimeMilliseconds) {
        this.maxSleepTimeMilliseconds = maxSleepTimeMilliseconds;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }
}
