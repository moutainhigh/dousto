var zTree1;
var setting;

setting = {
    isSimpleData: true,
    treeNodeKey: "id",
    treeNodeParentKey: "pId",
    keepLeaf: true,
    keepParent: true,
    checkable: false,
    expandSpeed: false,
    callback: {
        beforeClick: zTreeBeforeClick,
        click: zTreeOnClick,
        change: zTreeOnChange
    }
};
function initSrcNode(){
    $("#renameTxt").attr("value", "");
    $("#curNodePath").html(getNodePath(null));
    showCopy(null);
    showPaste(null);
}

function zTreeOnClick(event, treeId, treeNode){
    $("#curNodePath").html(getNodePath(treeNode));
    $("#renameTxt").attr("value", treeNode.name);
    showCopy(treeNode);
    showPaste(treeNode);
}

function zTreeBeforeClick(treeId, treeNode){
    var canClick = (treeNode != zTree1.getSelectedNode());
    if (!canClick) {
        zTree1.cancelSelectedNode();
        initSrcNode();
    }
    return canClick;
}

function zTreeOnChange(event, treeId, treeNode){
    getNodes();
}

function expandAll(expandSign){
    zTree1.expandAll(expandSign);
}

function expandNode(expandSign){
    var srcNode = zTree1.getSelectedNode();
    if (srcNode) {
        zTree1.expandNode(srcNode, expandSign, $("#sonChk").attr("checked"));
    }
}

var addCount = 1;
function addNode(type){
    setOperatePool();
    var srcNode = zTree1.getSelectedNode();
    var newNode = [{
        name: "增加" + (addCount++)
    }];
    if (type == "p") 
        newNode[0].isParent = true;
    zTree1.addNodes(srcNode, newNode);
    getNodes();
}

function removeTreeNode(){
    var srcNode = zTree1.getSelectedNode();
    if (srcNode) {
        if (srcNode.nodes && srcNode.nodes.length > 0) {
            var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
            if (confirm(msg) == true) {
                setOperatePool();
                zTree1.removeNode(srcNode);
                initSrcNode();
            }
        }
        else {
            setOperatePool();
            zTree1.removeNode(srcNode);
            initSrcNode();
        }
    }
    getNodes();
}

function getPreTreeNode(treeNode){
    if (treeNode.isFirstNode) 
        return null;
    var nodes = treeNode.parentNode ? treeNode.parentNode.nodes : zTree1.getNodes();
    var preNode;
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i] == treeNode) {
            return preNode;
        }
        preNode = nodes[i];
    }
}

function getNextTreeNode(treeNode){
    if (treeNode.isLastNode) 
        return null;
    var nodes = treeNode.parentNode ? treeNode.parentNode.nodes : zTree1.getNodes();
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i] == treeNode) {
            return nodes[i + 1];
        }
    }
}

function moveTreeNode(move){
    var srcNode = zTree1.getSelectedNode();
    if (!srcNode) {
        alert("请先选中一个节点");
        return;
    }
    var moveType = "inner";
    var targetNode = "";
    if (move == "up") {
        moveType = "before";
        targetNode = getPreTreeNode(srcNode);
    }
    else 
        if (move == "down") {
            moveType = "after";
            targetNode = getNextTreeNode(srcNode);
        }
        else 
            if (move == "left") {
                moveType = "after";
                targetNode = srcNode.parentNode;
            }
            else 
                if (move == "right") {
                    moveType = "inner";
                    targetNode = getPreTreeNode(srcNode);
                }
    if (srcNode && targetNode) {
        setOperatePool();
        zTree1.moveNode(targetNode, srcNode, moveType);
    }
    getNodes();
    zTree1.selectNode(srcNode);
}

function getNodes(){
    var a = zTree1.transformToArray(zTree1.getNodes());
    $("#allNum").html(a.length);
    a = zTree1.getCheckedNodes();
    $("#allCheckNum").html(a.length);
    
}

function makeListName(node){
    var space = "";
    for (var s = 0; s < node.level; s++) {
        space = space + "--";
    }
    return space + node.name + "(" + node.tId + ")";
}

function renameTreeNode(txtObj){
    var srcNode = zTree1.getSelectedNode();
    if (!srcNode) {
        alert("请先选中一个节点");
        $("#renameTxt").attr("value", "");
        return;
    }
    if (srcNode.name != txtObj.value) {
        setOperatePool();
        srcNode.name = txtObj.value;
        zTree1.updateNode(srcNode);
        zTree1.selectNode(srcNode);
    }
    
}

function checkTreeNode(checked){
    var srcNode = zTree1.getSelectedNode();
    if (!srcNode) {
        alert("请先选中一个节点");
        return;
    }
    var oldNodes = clone(zTree1.getNodes());
    var oldcheckNum = zTree1.getCheckedNodes(checked).length;
    var connFlag = $("#connTrue").attr("checked");
    srcNode.checked = checked;
    zTree1.updateNode(srcNode, connFlag);
    var newcheckNum = zTree1.getCheckedNodes(checked).length;
    if (newcheckNum != oldcheckNum) 
        setOperatePool(oldNodes);
}

function checkAllTreeNode(checked){
    var oldNodes = clone(zTree1.getNodes());
    var oldcheckNum = zTree1.getCheckedNodes(checked).length;
    zTree1.checkAllNodes(checked);
    var newcheckNum = zTree1.getCheckedNodes(checked).length;
    if (newcheckNum != oldcheckNum) 
        setOperatePool(oldNodes);
}

function getNodeByParam(){
    var key = $("#searchKey").get(0).value;
    if (key.length > 0) {
        var treeNode = zTree1.getNodeByParam("name", key);
        if (treeNode) {
            alert("找到节点信息: \ntId=" + treeNode.tId + ", name=" + treeNode.name + ", level=" + treeNode.level);
        }
        else {
            alert("没有找到匹配的节点，请更换搜索条件");
        }
    }
}

function getNodesByParamFuzzy(){
    var key = $("#searchKeyFuzzy").get(0).value;
    if (key.length > 0) {
        var treeNodes = zTree1.getNodesByParamFuzzy("name", key);
        if (treeNodes && treeNodes.length > 0) {
            var msg = "找到节点信息: ";
            for (var i = 0; i < treeNodes.length; i++) {
                msg += ("\n(" + (i + 1) + ") tId=" + treeNodes[i].tId + ", name=" + treeNodes[i].name + ", level=" + treeNodes[i].level);
            }
            alert(msg);
        }
        else {
            alert("没有找到匹配的节点，请更换搜索条件");
        }
    }
}

function getNodesByParam(){
    var key = $("#searchKey2").get(0).value;
    if (key.length == 0 || isNaN(key)) {
        alert('请输入数字！')
        return;
    }
    key = parseInt(key);
    var treeNodes = zTree1.getNodesByParam("level", key);
    if (treeNodes && treeNodes.length > 0) {
        var msg = "找到节点信息: ";
        for (var i = 0; i < treeNodes.length; i++) {
            msg += ("\n(" + (i + 1) + ") tId=" + treeNodes[i].tId + ", name=" + treeNodes[i].name + ", level=" + treeNodes[i].level);
        }
        alert(msg);
    }
    else {
        alert("没有找到匹配的节点，请更换搜索条件");
    }
}

function reloadTree(node){
    zTree1 = $("#treeDemo").zTree(setting, node ? node : clone(zNodes));
    $("#renameTxt").attr("value", "");
    $("#curNodePath").html("[Root]");
    getNodes();
    initCopy();
    refreshOperatePool();
}

function getNodePath(treeNode){
    if (!treeNode) 
        return "[Root]";
    var p = "[" + treeNode.name + "]";
    var pNode = treeNode.parentNode;
    while (pNode != null) {
        p = "[" + pNode.name + "] -- " + p;
        pNode = pNode.parentNode;
    }
    p = "[Root] -- " + p;
    return p;
}

function initCopyEvent(){
    $("#jCopy").bind("dblclick", noSelection);
    $("#jCut").bind("dblclick", noSelection);
    $("#jPaste").bind("dblclick", noSelection);
    $("#jRollback").bind("dblclick", noSelection);
    $("#jRedo").bind("dblclick", noSelection);
}

function noSelection(event){
    event.target.blur();
    window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
    return false;
}

function initCopy(){
    $("#jCopy").attr("class", "disabled");
    $("#jCut").attr("class", "disabled");
    $("#jPaste").attr("class", "disabled");
}

function showCopy(treeNode){
    var canPaste = true;
    if (!treeNode) 
        canPaste = false;
    if (canPaste) {
        $("#jCopy").attr("class", "able");
        $("#jCut").attr("class", "able");
    }
    else {
        $("#jCopy").attr("class", "disabled");
        $("#jCut").attr("class", "disabled");
    }
    
}

function showPaste(targetNode){
    var canPaste = true;
    
    if (!clipboard.srcNode || targetNode == clipboard.srcNode) {
        canPaste = false;
    }
    else 
        if (clipboard.type) {
            if (targetNode == clipboard.srcNode.parentNode && clipboard.type == "cut") {
                canPaste = false;
            }
            else 
                if (targetNode) {
                    var tmpParent = targetNode.parentNode;
                    while (tmpParent && tmpParent != clipboard.srcNode) {
                        tmpParent = tmpParent.parentNode;
                    }
                    if (tmpParent) {
                        canPaste = false;
                    }
                }
        }
    
    if (canPaste) {
        $("#jPaste").attr("class", "able");
    }
    else {
        $("#jPaste").attr("class", "disabled");
    }
}

var clipboard = {};
function zCopy(type){
    var treeNode = zTree1.getSelectedNode();
    if ($("#jCopy").attr("class") == "disabled" || !treeNode) {
        alert("请先选择需要" + (type == "copy" ? "复制" : "剪切") + "节点..");
    }
    else 
        if (treeNode) {
            clipboard.type = type;
            clipboard.srcNode = treeNode;
            $("#jClip").text("......[" + treeNode.name + "] 被" + (type == "copy" ? "复制" : "剪切"));
        }
}

function zPaste(){
    var treeNode = zTree1.getSelectedNode();
    if (!clipboard.srcNode) {
        alert("请先 复制 或 剪切 节点..");
    }
    else 
        if ($("#jPaste").attr("class") == "disabled") {
            alert("无法粘贴..");
        }
        else {
            if (clipboard.type == "copy") {
                setOperatePool();
                var cSrcNode = clone(clipboard.srcNode);
                zTree1.addNodes(treeNode, cSrcNode, false);
                zTree1.selectNode(cSrcNode);
            }
            else 
                if (clipboard.type == "cut") {
                    setOperatePool();
                    zTree1.removeNode(clipboard.srcNode);
                    zTree1.addNodes(treeNode, clipboard.srcNode, false);
                    zTree1.selectNode(clipboard.srcNode);
                    showCopy(clipboard.srcNode);
                    clipboard.type == null;
                    clipboard.srcNode = null;
                    showPaste(clipboard.srcNode);
                    $("#jClip").text("");
                }
        }
}

var opreatePool = [];
var lastNode = null;
var poolCursor = -1;
var poolMax = 20;
function setOperatePool(nodes){

    var n = nodes ? nodes : clone(zTree1.getNodes());
    opreatePool.splice(poolCursor + 1, (opreatePool.length - poolCursor - 1), n);
    if (opreatePool.length > poolMax) {
        opreatePool.splice(0, 1);
    }
    poolCursor = opreatePool.length - 1;
    refreshOperatePool();
    lastNode = null;
}

function operateRollback(){
    if (poolCursor >= 0) {
        if (!lastNode) {
            lastNode = zTree1.getNodes();
        }
        var n = clone(opreatePool[poolCursor]);
        reloadTree(n);
        poolCursor--;
    }
    refreshOperatePool();
}

function operateRedo(){
    if (poolCursor < (opreatePool.length - 2)) {
        poolCursor++;
        var n = clone(opreatePool[poolCursor + 1]);
        reloadTree(n);
    }
    else 
        if (lastNode) {
            poolCursor++;
            reloadTree(lastNode);
            lastNode = null;
        }
    refreshOperatePool();
}

function refreshOperatePool(){
    if (poolCursor >= 0) {
        $("#jRollback").attr("class", "able");
    }
    else {
        $("#jRollback").attr("class", "disabled");
    }
    if (poolCursor < (opreatePool.length - 1)) {
        $("#jRedo").attr("class", "able");
    }
    else {
        $("#jRedo").attr("class", "disabled");
    }
    $("#jOperatePool").html("操作记录: " + opreatePool.length + ", 游标位置: " + (poolCursor + 1));
}
