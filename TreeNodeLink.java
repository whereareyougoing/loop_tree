package com.jd.jr.console.domain.tree;


import com.jd.jr.console.domain.DecisionTreeLink;

/**
 * Created by zhangguoli on 2018/7/9.
 */
public class TreeNodeLink {

    private TreeNode treeNode;
    private DecisionTreeLink link;

    public DecisionTreeLink getLink() {
        return link;
    }

    public void setLink(DecisionTreeLink link) {
        this.link = link;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }
}
