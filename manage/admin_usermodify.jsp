<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="admin_menu.jsp" %>
    <title>后台管理</title>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/manger/admin_index.jsp">首页</a>
            <span class="crumb-step">&gt;</span><a class="crumb-name" href="/manage/admin_douserselect">用户管理</a>
            <span class="crumb-step">&gt;</span><span>修改用户</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="/manage/admin_douserupdate" method="get" id="myform" name="myform">

                  <input type="hidden" name="userStatus" value="${user.user_status }">
                  <input type="hidden" name="cpage" value="${cpage }">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th><i class="require-red">*</i>用户名：</th>
                                <td>
                                    <input class="common-text required" id="title" name="userid" size="50" value="${user.user_id }" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>用户姓名：</th>
                                <td>
                                    <input class="common-text required" id="title" name="username" size="50" value="${user.user_name }" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>登录密码：</th>
                                <td>
                                    <input class="common-text required" id="title" name="userpassword" size="50" value="${user.user_password }" type="text">
                                </td>
                            </tr>
                            <tr>
                            <th>性别：
                            <td>
                            <input type="radio" name="sex" value="T" ${user.user_sex=='T'?"checked":"" }>男
                            <input type="radio" name="sex" value="F" ${user.user_sex=='F'?"checked":"" }>女
                            </td>
                            </th>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>游戏账号：</th>
                                <td>
                                    <input class="common-text required" id="title" name="usergameid" size="50" value="${user.user_gameid }" type="text">
                                </td>
                            </tr>
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