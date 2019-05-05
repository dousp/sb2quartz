package com.dou.sb2quartz.core.enums;

public enum PageSize {
    //
    xs(10),
    //
    s(20),
    //
    m(50),
    //
    l(100),
    //
    xl(200),
    //
    xxl(500),
    //
    xxxl(1000);
    private int pageSize;

    PageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
}
