package cn.kiwipeach.blog.quartz;

public class AreaStatisticPercent {
    private String id;
    /**
     * 地区编码
     */
    private String areaCode;
    /**
     * 百分比
     */
    private String percent;
    /**
     * 当前业务办理量
     */
    private String todayCount;
    /**
     * 历史业务办理量
     */
    private String historyCount;

    public String getAreaCode() {
        return areaCode;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(String todayCount) {
        this.todayCount = todayCount;
    }

    public String getHistoryCount() {
        return historyCount;
    }

    public void setHistoryCount(String historyCount) {
        this.historyCount = historyCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
