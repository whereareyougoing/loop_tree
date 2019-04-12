package com.jd.jr.console.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by zhangguoli on 2018/6/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DecisionTreeLink {
    private Long id;
    private Long decisionId;
    private String linkFrom;
    private String linkTo;
    private String linkText;
    private byte operationType;
    private String operationExpression;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(Long decisionId) {
        this.decisionId = decisionId;
    }

    public String getLinkFrom() {
        return linkFrom;
    }

    public void setLinkFrom(String linkFrom) {
        this.linkFrom = linkFrom;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public byte getOperationType() {
        return operationType;
    }

    public void setOperationType(byte operationType) {
        this.operationType = operationType;
    }

    public String getOperationExpression() {
        return operationExpression;
    }

    public void setOperationExpression(String operationExpression) {
        this.operationExpression = operationExpression;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
