<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<nav class="navbar navbar-expand navbar-dark" style="background-color:transparent;">
        <div class="container-fluid">
            <a class="navbar-brand" href="main">문밤</a>
            <form class="d-flex" role="search">
                <input class="form-control me-1" type="search" placeholder="영화 정보 입력" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <ul class="navbar-nav">
			<% 
				MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
				if(dto != null){	//로그인 상태
			%>
                <li class="nav-item"><a href="Logout" class="nav-link">로그아웃</a></li>
				<!-- <li class="nav-item"><a href="#" class="nav-link">마이페이지</a></li> -->			
			<%
				} else {			//로그인 아닌 상태
			%>
				<li class="nav-item"><a href="Login" class="nav-link">로그인</a></li>
				<!-- <li class="nav-item"><a href="#" class="nav-link">회원가입</a></li> -->
			<%
				}
			%>                
                <!-- <li class="nav-item"><a href="#" class="nav-link">Link3</a></li> -->
            </ul>
        </div>
</nav>