<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_menu.jsp" %>
    <title>后台管理</title>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin_user.jsp">分类管理</a><span class="crumb-step">&gt;</span><span>修改分类</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="/manage/admin_docategoryupdate" method="POST" id="myform" name="myform">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th><i class="require-red">*</i>分类ID：</th>
                                <td><input class="common-text required" id="title" name="categoryid" size="50" value="${clist.id }" type="text"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>分类NAME：</th>
                                <td>
                                    <input class="common-text required" id="title" name="categoryname" size="50" value="${clist.name }" type="text">
                                </td>
                                <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="修改" type="submit">
                                    <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                           
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>