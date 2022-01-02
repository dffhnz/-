<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_menu.jsp" %>
    <title>用户菜单</title>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">商品管理</span></div>
        </div>
           
        <div class="result-wrap">
            <form action="/manage/admin_doitemdel" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="/manage/admin_tocategoryadd"><i class="icon-font"></i>新增商品</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>商品库存</th>
                            <th>操作</th>
                            
                        </tr>
                        
                          <c:forEach var="i" items="${itemlist}">
                        	<tr>
                        		<td>${i.id }</td>
                        		<td>${i.name }</td>
                                <td>${i.price}</td>
                                <td>${i.inventory}</td>
                        		<td>
                        			<a href="admin_toitemupdate?id=${i.id }&cpage=${cpage }">修改</a>
                        			
                        			 <a href="javascript:Delete('你确定要删除用户【${i.name}】吗？','/manage/admin_doitemdel?id=${i.id}&cpage=${cpage }')">删除</a>
                        		</td>
                        	</tr>
                        	
	                    </c:forEach>
                       
                       
                       
                        <script>
                        function Delete(mess, url){
                        	if(confirm(mess)){
                        		location.href=url;
                        	}
                        }
                        
                     </script>
                    </table>
                    <div class="list-page"> </div>
                    共${tsum}条记录，当前${cpage}/${tpage}页
                    <a href="/manage/admin_doitemselect?cp=1${keywordd}">首页</a>
                    <a href="/manage/admin_doitemselect?cp=${cpage-1<1?1:cpage-1}${keywordd}">上一页</a>
                    <a href="/manage/admin_doitemselect?cp=${cpage+1>tpage?tpage:cpage+1}${keywordd}">下一页</a>
                    <a href="/manage/admin_doitemselect?cp=${tpage}${keywordd}">尾页</a>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>>