<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_menu.jsp" %>
<title>用户菜单</title>
<!--/sidebar-->
<div class="main-wrap">
    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">订单管理</span></div>
    </div>
    <div class="result-wrap">
        <form action="/manage/admin_doorderupdate" id="myform" method="post">
            <div class="result-title">
                <div class="result-list">
                    <a href="javascript:delmore('myform')"><i class="icon-font"></i>批量处理</a>
                </div>
            </div>
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <tr>
                        <th class="tc" width="5%"><input class="allChoose" name="" onclick="selall(this)" type="checkbox"></th>

                        <th>订单号</th>
                        <th>类型</th>
                        <th>商品id</th>
                        <th>用户id</th>
                        <th>数量</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach var="o" items="${orderlist}">

                        <tr>
                            <td class="tc"><input name="id[]" value="${o.id}" type="checkbox" ></td>
                            <td> ${o.id }</td>
                            <td> ${o.category }</td>
                            <td> ${o.iid}</td>
                            <td> ${o.uid}</td>
                            <td> ${o.num}</td>
                            <td>
                                <a class="link-del" href="javascript:Delete('/manage/admin_doorderupdate?id=${o.id}')">处理</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="list-page"> </div>
                共${tsum}条记录，当前${cpage}/${tpage}页
                <a href="admin_doorderupdate?cp=1${keywordd}">首页</a>
                <a href="admin_doorderupdate?cp=${cpage-1<1?1:cpage-1}${keywordd}">上一页</a>
                <a href="admin_doorderupdate?cp=${cpage+1>tpage?tpage:cpage+1}${keywordd}">下一页</a>
                <a href="admin_doorderupdate?cp=${tpage}${keywordd}">尾页</a>
            </div>
        </form>
    </div>
</div>
<!--/main-->
</div>
<script>

    function selall(o){
        var a =	document.getElementsByName('id[]');
        for(var i = 0;i<a.length; i++){
            a[i].checked = o.checked;
        }
    }
    function delmore( formname){
            var form = document.getElementById(formname);
            //提交一个集合
            form.submit();
    }
    function Delete( url){
            location.href=url;
    }
</script>


</table>
</body>
</html>
