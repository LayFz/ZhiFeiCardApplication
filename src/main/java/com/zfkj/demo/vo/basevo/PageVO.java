package com.zfkj.demo.vo.basevo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-06-13 17:10
 **/
public class PageVO implements Serializable {

    private static final long serialVersionUID = -2138486238199051448L;

    @ApiModelProperty(value = "请求页",example = "1")
    @Min(value = 1L,message = "用户直接接触的分页，第一页都为1")
    private int pageNum = 1;

    @ApiModelProperty(value = "一页显示多少条",example = "10")
    @Min(value = 1L,message = "分页参数:每页记录数,不传时默认值为10")
    private int pageSize = 10;

    public PageVO() {
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageVO)) {
            return false;
        } else {
            PageVO other = (PageVO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getPageNum() != other.getPageNum()) {
                return false;
            } else {
                return this.getPageSize() == other.getPageSize();
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageVO;
    }

}
