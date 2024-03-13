<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- 각 페이지에 입력할 내용(현재 보류)

<%
// 페이지마다 다르게 설정
boolean show_Login_Link = false;
boolean show_Search_IDLink = true;
boolean show_Search_PWLink = true;
boolean show_Register_Link = true;
%>

<jsp:include page="/Include/site_Link.jsp">
    <jsp:param name="show_Login_Link" value="<%= show_Login_Link %>" />
    <jsp:param name="show_Search_IDLink" value="<%= show_Search_IDLink %>" />
    <jsp:param name="show_Search_PWLink" value="<%= show_Search_PWLink %>" />
    <jsp:param name="show_Register_Link" value="<%= show_Register_Link %>" />
</jsp:include> 

--%>


<%
boolean show_Login_Link = Boolean.parseBoolean(request.getParameter("show_Login_Link"));
boolean show_Search_IDLink = Boolean.parseBoolean(request.getParameter("show_Search_IDLink"));
boolean show_Search_PWLink = Boolean.parseBoolean(request.getParameter("show_Search_PWLink"));
boolean show_Register_Link = Boolean.parseBoolean(request.getParameter("show_Register_Link"));
%>

<div id="sitesShortCut">

<% if (show_Login_Link){ %>
<a href="<%=request.getContextPath()%>/LoginForm_Active" class="links">로그인</a>
<% } %>

<% if (show_Search_IDLink){ %>
<a href="<%=request.getContextPath()%>/connect_to_search_ID_by_Name_SSN" class="links">아이디 찾기</a> 
<% } %>

<% if (show_Search_PWLink){ %>
<a href="<%=request.getContextPath()%>/connect_to_search_PW_by_ID_Name_SSN" class="links">비밀번호 찾기</a> 
<% } %>

<% if (show_Register_Link){ %>
<a href="<%=request.getContextPath()%>/connect_to_register_agree_terms" class="links">회원가입</a>
<% } %>

</div>