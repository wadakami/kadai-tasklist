<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
	<c:param name="content">
		<c:choose>
			<c:when test="${task.id != null }">
					<h2>id : ${task.id} のメッセージ編集ページ</h2>

					<form method="POST" action="${pageContext.request.contextPath }/update">
						<c:import url="_form.jsp"></c:import>
					</form>
					<p><a href="${pageContext.request.contextPath }/index">一覧に戻る</a></p>
					<p><a href="#" onclick="confirmDestroy();">このタスクを終了する</a></p>
					<form method="post" action="${pageContext.request.contextPath}/destroy">
						<input type="hidden" name="_token" value="${_token}" />
					</form>
					<script>
						function confirmDestroy() {
							if(confirm("本当に削除させてもよろしいですか？")) {
								document.forms[1].submit();
							}
						}
					</script>
			</c:when>
			<c:otherwise>
				<h2>お探しのデータは見つかりませんでした。</h2>
			</c:otherwise>
		</c:choose>

	</c:param>
</c:import>