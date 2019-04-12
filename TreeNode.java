package com.jd.jr.console.domain.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jd.jr.console.domain.DecisionTreeNode;

import java.util.List;

/**
 * Created by zhangguoli on 2018/7/9.
 */
public class TreeNode {
    private DecisionTreeNode node;
    private List<TreeNodeLink> linkList;

    public DecisionTreeNode getNode() {
        return node;
    }

    public void setNode(DecisionTreeNode node) {
        this.node = node;
    }

    public List<TreeNodeLink> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<TreeNodeLink> linkList) {
        this.linkList = linkList;
    }

    @JsonIgnore
    public boolean isLeaf() {
        return linkList==null || linkList.size() == 0;
    }
}
