package com.jd.jr.console.domain.compile;

import com.jd.jr.console.domain.Decision;
import com.jd.jr.console.domain.DecisionTreeActionItem;
import com.jd.jr.console.domain.DecisionTreeLink;
import com.jd.jr.console.domain.DecisionTreeNode;

import java.util.List;

/**
 * Created by zhangguoli on 2018/7/6.
 */
public class DecisionTree {

    private Decision decision;
    private Long entityId;
    private List<DecisionTreeNode> treeNodeList;
    private List<DecisionTreeLink> treeLinkList;
    private List<DecisionTreeActionItem> actionItemList;



    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public List<DecisionTreeNode> getTreeNodeList() {
        return treeNodeList;
    }

    public void setTreeNodeList(List<DecisionTreeNode> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }

    public List<DecisionTreeLink> getTreeLinkList() {
        return treeLinkList;
    }

    public void setTreeLinkList(List<DecisionTreeLink> treeLinkList) {
        this.treeLinkList = treeLinkList;
    }

    public List<DecisionTreeActionItem> getActionItemList() {
        return actionItemList;
    }

    public void setActionItemList(List<DecisionTreeActionItem> actionItemList) {
        this.actionItemList = actionItemList;
    }
}
