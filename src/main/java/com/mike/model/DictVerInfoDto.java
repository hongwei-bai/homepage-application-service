package com.mike.model;

import java.util.List;

public class DictVerInfoDto {
    private long version;

    // 0-None
    // 1-Files
    // 2-Items
    private int upgradeType;

    private List<String> filelist;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public int getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(int upgradeType) {
        this.upgradeType = upgradeType;
    }

    public List<String> getFilelist() {
        return filelist;
    }

    public void setFilelist(List<String> filelist) {
        this.filelist = filelist;
    }

    @Override
    public String toString() {
        return "DictVerInfoDto{" +
                "version=" + version +
                ", upgradeType=" + upgradeType +
                ", filelist=" + filelist +
                '}';
    }
}
