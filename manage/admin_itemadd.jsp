<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_menu.jsp" %>
    <title>后台管理</title>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">商品管理</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin_user.jsp">新增图书</a><span class="crumb-step">&gt;</span><span>新增作品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="/manage/admin_doitemadd" method="post" enctype="multipart/form-data"  id="myform" name="myform">
                    <table class="insert-tab" width="100%">
                        <tbody>

                              <tr>
                                <th><i class="require-red">*</i>商品名称：</th>
                                <td>
                                    <input class="common-text required" id="title" name="name" size="50" value="" type="text">
                                </td>
                                </tr>


                            <tr>
                                <th><i class="require-red">*</i>商品分类：</th>
                                <td>
                                    <select class="common-text required" name="categoryId">
                                 <c:forEach var="c" items="${clist }">
                                    <option value="${c.id}">|-${c.name } </option>
                                  </c:forEach>
                                    </select>

                                </td>
                            </tr>

                                <tr>
                                <th><i class="require-red">*</i>商品图片：</th>
                                <td>
                                    <input class="common-text required" id="title" name="image" size="50" value="" type="file">
                                </td>
                                </tr>


                                <tr>
                                <th><i class="require-red">*</i>商品价格：</th>
                                <td>
                                    <input class="common-text required" id="title" name="price" size="50" value="" type="text">
                                </td>
                                </tr>


                                <tr>
                                <th><i class="require-red">*</i>商品介绍：</th>
                                <td>
                                    <input class="common-text required" id="title" name="itemdesc" size="50" value="" type="text">
                                </td>
                                </tr>

                                <tr>
                                <th><i class="require-red">*</i>商品库存：</th>
                                <td>
                                    <input class="common-text required" id="title" name="inventory" size="50" value="" type="text">
                                </td>
                                </tr>



                                <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
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