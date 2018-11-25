<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${errors != null }">
	<div id="flush_error">
		入力内容にエラーがあります。<br />
		<c:forEach var="error" items="${errors }">
			・<c:out value="${error }" /><br />
		</c:forEach>
	</div>
</c:if>
<label for="title">タイトル</label><br />
<input type="text" name="title" value="${task.title }" />
<br /><br />

<label for="content">内容</label><br />
<input type="text" name="content" value="${task.content }" />
<br /><br />

<label for="deadline">期日</label><br />
<input type="date" name="deadline" value="<fmt:formatDate value='${task.deadline }' pattern='yyyy-MM-dd'/>" >
<br /><br />


<input type="radio" name="done" value="0" checked />継続
<input type="radio" name="done" value="1" />済み

<input type="hidden" name="_token" value="${_token}" /><br /><br />

<button type="submit">作成</button>
