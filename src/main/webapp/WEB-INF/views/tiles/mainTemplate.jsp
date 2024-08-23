<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/css/fdfa608d464f1bb9.css" rel="stylesheet" type="text/css">
<link href="/resources/css/d9f4e75c2121ddb0.css" rel="stylesheet" type="text/css">
<link href="/resources/css/mf.min.css" rel="stylesheet" type="text/css">
<link href="/resources/css/7b65a34d28470be3.css" rel="stylesheet" type="text/css">
<link href="/resources/css/0c7ff060f4519b3e.css" rel="stylesheet" type="text/css">
<!--jQuery -->    
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper">

        <!-- Sidebar /// aside.jsp 시작 ///// -->
<%--         <tiles:insertAttribute name="aside" /> --%>
        <!-- End of Sidebar  /// aside.jsp 끝 /////-->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar ///// header.jsp 시작 /////// -->
                <tiles:insertAttribute name="header" />
                <!-- End of Topbar /////header.jsp 끝///// -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
					<tiles:insertAttribute name="body" />
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->

            <!-- Footer /// footer.jsp 시작 ///// -->
            <tiles:insertAttribute name="footer" />
            <!-- End of Footer /// footer.jsp 끝 /////-->

        </div>
        <!-- End of Content Wrapper -->

    </div>
</body>
</html>