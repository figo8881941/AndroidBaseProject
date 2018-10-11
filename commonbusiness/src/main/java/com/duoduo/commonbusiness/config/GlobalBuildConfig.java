package com.duoduo.commonbusiness.config;

/**
 * 全局配置
 */
public class GlobalBuildConfig {

    /**
     * 是否debug模式
     */
    private boolean isDebugMode;

    /**
     * 协议版本
     */
    private int PVersion;

    /**
     * 默认渠道
     */
    private int defaultChannel;

    /**
     * 产品id
     */
    private String prdid;

    /**
     * 正式服务器
     */
    private String normalServerHost;

    /**
     * 测试服务器
     */
    private String testServerHost;

    /**
     * SD卡应用文件夹名称
     */
    private String SDCardFolderName;



    private static GlobalBuildConfig instance;

    private GlobalBuildConfig() {
    }

    public static synchronized GlobalBuildConfig getInstance() {
        if (instance == null) {
            instance = new GlobalBuildConfig();
        }
        return instance;
    }

    public boolean isDebugMode() {
        return isDebugMode;
    }

    public void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }

    public int getPVersion() {
        return PVersion;
    }

    public void setPVersion(int PVersion) {
        this.PVersion = PVersion;
    }

    public int getDefaultChannel() {
        return defaultChannel;
    }

    public void setDefaultChannel(int defaultChannel) {
        this.defaultChannel = defaultChannel;
    }

    public String getPrdid() {
        return prdid;
    }

    public void setPrdid(String prdid) {
        this.prdid = prdid;
    }

    public String getNormalServerHost() {
        return normalServerHost;
    }

    public void setNormalServerHost(String normalServerHost) {
        this.normalServerHost = normalServerHost;
    }

    public String getTestServerHost() {
        return testServerHost;
    }

    public void setTestServerHost(String testServerHost) {
        this.testServerHost = testServerHost;
    }

    public String getSDCardFolderName() {
        return SDCardFolderName;
    }

    public void setSDCardFolderName(String SDCardFolderName) {
        this.SDCardFolderName = SDCardFolderName;
    }
}