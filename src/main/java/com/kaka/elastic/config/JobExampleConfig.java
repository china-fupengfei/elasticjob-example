package com.kaka.elastic.config;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.kaka.elastic.util.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;

@Configuration
@ConfigurationProperties(prefix = "job")
@PropertySource("classpath:job.properties")
public class JobExampleConfig {

    private static final Logger logger = LoggerFactory.getLogger(JobExampleConfig.class);

    @Resource
    private ZookeeperRegistryCenter regCenter;

    // 默认配置都用同一份

    private int defaultSettingsShardingTotalCount;
    private String defaultSettingsShardingItemParameters;
    private boolean defaultSettingsMonitorExecution;
    private boolean defaultSettingsFailover;
    private boolean defaultSettingsOverwrite;
    private boolean defaultSettingsMisfire;
    private int defaultSettingsMonitorPort;
    private String defaultSettingsClass;

    //example-doSomeThing配置

    private String exampleDayIdOne;
    private String exampleDayCronOne;
    private String exampleOneDescription;
    private String exampleDayOneJobParameter;
    private boolean exampleOneDayDisabled;


    //example-doAnotherThing配置

    private String exampleDayIdTwo;
    private String exampleDayCronTwo;
    private String exampleTwoDescription;
    private String exampleDayTwoJobParameter;
    private boolean exampleTwoDayDisabled;


    @Bean(initMethod = "init")
    public JobScheduler exampleOneJobScheduler() throws Exception {
        ElasticJob elasticJob = (ElasticJob) ApplicationContextHolder.getBean(Class.forName(defaultSettingsClass));
        return new SpringJobScheduler(
            elasticJob, regCenter,
            LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(
                    JobCoreConfiguration.newBuilder(exampleDayIdOne, exampleDayCronOne, defaultSettingsShardingTotalCount)
                        .description(exampleOneDescription)
                        .failover(defaultSettingsFailover)
                        .misfire(defaultSettingsMisfire)
                        .shardingItemParameters(defaultSettingsShardingItemParameters)
                        .jobParameter(exampleDayOneJobParameter).build(), defaultSettingsClass
                )
            )
            .monitorExecution(defaultSettingsMonitorExecution)
            .overwrite(defaultSettingsOverwrite)
            .disabled(exampleOneDayDisabled)
            .monitorPort(defaultSettingsMonitorPort)
            .build()
        );
    }

    @Bean(initMethod = "init")
    public JobScheduler exampleTwoJobScheduler() throws Exception {
        ElasticJob elasticJob = (ElasticJob) ApplicationContextHolder.getBean(Class.forName(defaultSettingsClass));
        return new SpringJobScheduler(elasticJob, regCenter, LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(exampleDayIdTwo, exampleDayCronTwo, defaultSettingsShardingTotalCount)
                        .description(exampleTwoDescription)
                        .failover(defaultSettingsFailover)
                        .misfire(defaultSettingsMisfire)
                        .shardingItemParameters(defaultSettingsShardingItemParameters)
                        .jobParameter(exampleDayTwoJobParameter).build(), defaultSettingsClass))
                .monitorExecution(defaultSettingsMonitorExecution)
                .overwrite(defaultSettingsOverwrite)
                .disabled(exampleTwoDayDisabled)
                .monitorPort(defaultSettingsMonitorPort)
                .build());
    }

    public ZookeeperRegistryCenter getRegCenter() {
        return regCenter;
    }

    public void setRegCenter(ZookeeperRegistryCenter regCenter) {
        this.regCenter = regCenter;
    }

    public int getDefaultSettingsShardingTotalCount() {
        return defaultSettingsShardingTotalCount;
    }

    public void setDefaultSettingsShardingTotalCount(int defaultSettingsShardingTotalCount) {
        this.defaultSettingsShardingTotalCount = defaultSettingsShardingTotalCount;
    }

    public String getDefaultSettingsShardingItemParameters() {
        return defaultSettingsShardingItemParameters;
    }

    public void setDefaultSettingsShardingItemParameters(String defaultSettingsShardingItemParameters) {
        this.defaultSettingsShardingItemParameters = defaultSettingsShardingItemParameters;
    }

    public boolean isDefaultSettingsMonitorExecution() {
        return defaultSettingsMonitorExecution;
    }

    public void setDefaultSettingsMonitorExecution(boolean defaultSettingsMonitorExecution) {
        this.defaultSettingsMonitorExecution = defaultSettingsMonitorExecution;
    }

    public boolean isDefaultSettingsFailover() {
        return defaultSettingsFailover;
    }

    public void setDefaultSettingsFailover(boolean defaultSettingsFailover) {
        this.defaultSettingsFailover = defaultSettingsFailover;
    }

    public boolean isDefaultSettingsOverwrite() {
        return defaultSettingsOverwrite;
    }

    public void setDefaultSettingsOverwrite(boolean defaultSettingsOverwrite) {
        this.defaultSettingsOverwrite = defaultSettingsOverwrite;
    }

    public boolean isDefaultSettingsMisfire() {
        return defaultSettingsMisfire;
    }

    public void setDefaultSettingsMisfire(boolean defaultSettingsMisfire) {
        this.defaultSettingsMisfire = defaultSettingsMisfire;
    }

    public int getDefaultSettingsMonitorPort() {
        return defaultSettingsMonitorPort;
    }

    public void setDefaultSettingsMonitorPort(int defaultSettingsMonitorPort) {
        this.defaultSettingsMonitorPort = defaultSettingsMonitorPort;
    }

    public String getDefaultSettingsClass() {
        return defaultSettingsClass;
    }

    public void setDefaultSettingsClass(String defaultSettingsClass) {
        this.defaultSettingsClass = defaultSettingsClass;
    }

    public String getExampleDayIdOne() {
        return exampleDayIdOne;
    }

    public void setExampleDayIdOne(String exampleDayIdOne) {
        this.exampleDayIdOne = exampleDayIdOne;
    }

    public String getExampleDayCronOne() {
        return exampleDayCronOne;
    }

    public void setExampleDayCronOne(String exampleDayCronOne) {
        this.exampleDayCronOne = exampleDayCronOne;
    }

    public String getExampleOneDescription() {
        return exampleOneDescription;
    }

    public void setExampleOneDescription(String exampleOneDescription) {
        this.exampleOneDescription = exampleOneDescription;
    }

    public String getExampleDayOneJobParameter() {
        return exampleDayOneJobParameter;
    }

    public void setExampleDayOneJobParameter(String exampleDayOneJobParameter) {
        this.exampleDayOneJobParameter = exampleDayOneJobParameter;
    }

    public boolean isExampleOneDayDisabled() {
        return exampleOneDayDisabled;
    }

    public void setExampleOneDayDisabled(boolean exampleOneDayDisabled) {
        this.exampleOneDayDisabled = exampleOneDayDisabled;
    }

    public String getExampleDayIdTwo() {
        return exampleDayIdTwo;
    }

    public void setExampleDayIdTwo(String exampleDayIdTwo) {
        this.exampleDayIdTwo = exampleDayIdTwo;
    }

    public String getExampleDayCronTwo() {
        return exampleDayCronTwo;
    }

    public void setExampleDayCronTwo(String exampleDayCronTwo) {
        this.exampleDayCronTwo = exampleDayCronTwo;
    }

    public String getExampleTwoDescription() {
        return exampleTwoDescription;
    }

    public void setExampleTwoDescription(String exampleTwoDescription) {
        this.exampleTwoDescription = exampleTwoDescription;
    }

    public String getExampleDayTwoJobParameter() {
        return exampleDayTwoJobParameter;
    }

    public void setExampleDayTwoJobParameter(String exampleDayTwoJobParameter) {
        this.exampleDayTwoJobParameter = exampleDayTwoJobParameter;
    }

    public boolean isExampleTwoDayDisabled() {
        return exampleTwoDayDisabled;
    }

    public void setExampleTwoDayDisabled(boolean exampleTwoDayDisabled) {
        this.exampleTwoDayDisabled = exampleTwoDayDisabled;
    }
}
