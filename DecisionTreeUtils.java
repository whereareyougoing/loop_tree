package com.jd.jr.console.common;

import com.jd.jr.console.domain.DataLabel;
import com.jd.jr.console.domain.JRuleEntityField;
import com.jd.jr.console.domain.compile.CompileContext;
import com.jd.jr.console.domain.compile.DecisionTree;
import com.jd.jr.console.domain.DecisionTreeLink;
import com.jd.jr.console.domain.DecisionTreeNode;
import com.jd.jr.console.domain.tree.TreeNode;
import com.jd.jr.console.domain.tree.TreeNodeLink;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

import static com.jd.jr.console.common.Constant.DECISION_TREE_NODE_TYPE_ACTION;
import static com.jd.jr.console.common.Constant.DECISION_TREE_START_NODE_KEY;
import static com.jd.jr.console.common.ObjectUtils.deepClone;

/**
 * Created by zhangguoli on 2018/7/9.
 */
public class DecisionTreeUtils {

    /**
     * 构建树
     * 将节点集合、连线结合 构建为一颗树。
     * @param decisionTree
     * @return
     * @throws RuleCompileException
     */
    public static TreeNode buildTree(DecisionTree decisionTree)throws RuleCompileException{

        //找到开始节点
        Optional<DecisionTreeNode> optional = decisionTree.getTreeNodeList().stream().filter(node -> isStartNode(node)).findFirst();

        if(!optional.isPresent())
            throw new RuleCompileException("未找到开始节点.");

        DecisionTreeNode startNode = optional.get();

        TreeNode rootNode = new TreeNode();
        rootNode.setNode(startNode);

        buildTree(rootNode,decisionTree);

        return rootNode;
    }



    private static void buildTree( TreeNode rootNode,DecisionTree decisionTree){
        List<DecisionTreeLink> links = decisionTree.getTreeLinkList();
        List<DecisionTreeLink> childLinks = links.stream().filter((link)->rootNode.getNode().getNodeKey().equals(link.getLinkFrom())).collect(Collectors.toList());

        List<TreeNodeLink> treeNodeLinks = new ArrayList<>();
        rootNode.setLinkList(treeNodeLinks);

        for(DecisionTreeLink decisionTreeLink : childLinks){
            TreeNodeLink treeNodeLink = new TreeNodeLink();

            treeNodeLink.setLink(decisionTreeLink);
            String toNodeKey = decisionTreeLink.getLinkTo();
            List<DecisionTreeNode> decisionTreeNodes = decisionTree.getTreeNodeList();
            Optional<DecisionTreeNode> optional = decisionTreeNodes.stream().filter((node)->node.getNodeKey().equals(toNodeKey)).findFirst();
            if(optional.isPresent()) {
                TreeNode treeNode = new TreeNode();
                treeNode.setNode(optional.get());
                treeNodeLink.setTreeNode(treeNode);
                buildTree(treeNode,decisionTree);
            }

            treeNodeLinks.add(treeNodeLink);
        }
    }

    /**
     * 拆分树
     * 遍历树的每条线，拆分为线数组
     * @param rootNode
     * @return
     */
    public static List<List<TreeNode>> splitTree(TreeNode rootNode){
        List<List<TreeNode>> list = new ArrayList();

        Stack<TreeNode> stack = new Stack<>();
        traversal(rootNode,stack,list);

        return pruning(list);
    }
    private static List<List<TreeNode>> pruning(List<List<TreeNode>> list){

        for(List<TreeNode> treeChain : list){

            for(int i=0;i<treeChain.size();i++){

                TreeNode node = treeChain.get(i);
                List<TreeNodeLink> linkList = node.getLinkList();
                if(linkList != null && linkList.size()>=2){
                    List<TreeNodeLink> newLinkList = new ArrayList<>();

                    int nextNodeIndex = i+1;
                    if(nextNodeIndex < treeChain.size()){//判断是不是数组最后一个节点。
                        TreeNode nextNode = treeChain.get(nextNodeIndex);
                        for(TreeNodeLink currentLink : linkList){
                            String linkTo = currentLink.getLink().getLinkTo();
                            String nextNodeKey = nextNode.getNode().getNodeKey();

                            if(linkTo.equals(nextNodeKey)){
                                newLinkList.add(currentLink);
                            }
                        }
                    }

                    node.setLinkList(newLinkList);
                }

            }
        }

        return list;
    }

    private static void traversal(TreeNode root, Stack<TreeNode> stack, List<List<TreeNode>> result) {

        stack.push(root);

        if(root.isLeaf()){
            ArrayList<TreeNode> list = new ArrayList<>();
            stack.forEach((item)->list.add(deepClone(item)));
            result.add(list);

            stack.pop();
            return;
        } else {
            List<TreeNodeLink> childLinks = root.getLinkList();
            for(TreeNodeLink link : childLinks){
                traversal(link.getTreeNode(),stack,result);
            }
            stack.pop();
        }

    }

    public static boolean isStartNode(DecisionTreeNode node){
        if(node == null)
            return false;

        return StringUtils.equals(DECISION_TREE_START_NODE_KEY,node.getNodeKey());
    }
    public static boolean isStartNode(String nodeKey){
        return StringUtils.equals(DECISION_TREE_START_NODE_KEY,nodeKey);
    }
    public static boolean isStartNodeByName(String nodeName){
        if(nodeName == null)
            return false;

        return "开始".equals(nodeName);
    }
    public static boolean isOperatorNode(DecisionTreeNode node){

        if(node.getNodeType() == null)
            return false;

        return node.getNodeType() == DECISION_TREE_NODE_TYPE_ACTION;
    }

    public static boolean isCrowdNode(DecisionTreeNode node,CompileContext context){

        String fieldKey = node.getFieldKey();

        return isCrowdNode(fieldKey,context);

    }

    public static boolean isCrowdNode(String fieldKey,CompileContext context){
        List<DataLabel> labelList = context.getLabelList();

        Optional<DataLabel> optional = labelList.stream().filter((label)->label.getLabelKey().equals(fieldKey)).findFirst();

        if(optional.isPresent()){
            DataLabel label = optional.get();
            if(label.getLabelType() == LabelTypeEnum.INTER_JUDGE.getValue())//人群ID标签
                return true;
            else
                return false;
        } else {
            return false;
        }
    }
}
