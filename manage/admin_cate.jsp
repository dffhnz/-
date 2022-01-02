<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_menu.jsp" %>
    <title>用户菜单</title>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">分类管理</span></div>
        </div>
           
        <div class="result-wrap">
                <div class="result-title">
                    <div class="result-list">
                        <a href="/manage/admin_cateadd.jsp"><i class="icon-font"></i>新增分类(不建议超过十个)</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>分类名称</th>
                            <th>操作</th>
                            
                        </tr>
                        
                        <c:forEach var="c" items="${categorylist}">
                        <tr>
                        <td>${c.id }</td>
                        <td>${c.name }</td>
                        <td><a href="/manage/admin_tocategoryupdate?id=${c.id}">修改&nbsp&nbsp</a><a href="javascript:catedel('${c.id }','${c.name}')">删除</a></td>
                        </tr>
                        </c:forEach>
                        <script>
                        function catedel(id,name){
                        	if(confirm("你确定要删除【"+name+"】分类吗？")){
                        		location.href="admin_docategorydel?id="+id;
                        	}
                        }
                        
                     </script>
                    </table>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>