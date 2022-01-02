<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_menu.jsp" %>
    <title>用户菜单</title>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin_douserselect" method="get">
                    <table class="search-tab">
                        <tr>
                            <th width="70">关键字:</th>
                            <td><input class="common-text" placeholder="关键字" name="keywords" value="${param.keywords}" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form action="/manage/admin_douserdelect" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="/manage/admin_useradd.jsp"><i class="icon-font"></i>新增用户</a>
                        <a id="batchDel" href="javascript:delmore('你确定删除这些用户吗？', 'myform')"><i class="icon-font"></i>批量删除</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" name="" onclick="selall(this)" type="checkbox"></th>
                
                            <th>ID</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>游戏账号</th>
                            <th>操作</th>
                        </tr>
                        
                        <c:forEach var="u" items="${userlist }">
                        
                        <tr>
                            <td class="tc"><input name="id[]" value="${u.user_id}" type="checkbox"></td>
                            <td> ${u.user_id }</td>
                            <td> ${u.user_name }</td>
                            <td> ${u.user_sex=='T'?'男':'女' }</td>
                            <c:if test="${u.user_gameid == 0 }">
                            <td>未填写</td>
                            </c:if>
                            <c:if test="${u.user_gameid != 0 }">
                                <td> ${u.user_gameid}</td>
                            </c:if>
                            <td>
                                <a class="link-update" href="/manage/admin_touserupdate?id=${u.user_id}&cpage=${cpage}">修改</a>
                                <c:if test="${u.user_status == 1 }">
                                <a class="link-del" href="javascript:Delete('你确定要删除用户【${u.user_name}】吗？','/manage/admin_douserdelect?id=${u.user_id}&cpage=${cpage }')">删除</a>
                                </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                    <div class="list-page"> </div>
                    共${tsum}条记录，当前${cpage}/${tpage}页
                     <a href="admin_douserselect?cp=1${keywordd}">首页</a>
                    <a href="admin_douserselect?cp=${cpage-1<1?1:cpage-1}${keywordd}">上一页</a>
                    <a href="admin_douserselect?cp=${cpage+1>tpage?tpage:cpage+1}${keywordd}">下一页</a>
                    <a href="admin_douserselect?cp=${tpage}${keywordd}">尾页</a>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
<script>
    function Delete(mess, url){
        if(confirm(mess)){
            location.href=url;
        }
    }

    function selall(o){
        var a =	document.getElementsByName('id[]');
        for(var i = 0;i<a.length; i++){
            a[i].checked = o.checked;
        }
    }

    function delmore(mess, formname){
        if(confirm(mess)){
            var form = document.getElementById(formname);
            //提交一个集合
            form.submit();
        }
    }
</script>


</table>
</body>
</html>