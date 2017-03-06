<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <spring:url value="/resources/css/workmanagementcss.css" var="mainCss"/>
    <spring:url value="/resources/images/" var="mainImages"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${mainCss}">
    <title>Work Management System</title>
</head>
<body>

<header>
    <nav class="navbar navbar-freelance">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <c:choose>
                    <c:when test="${adminUser != null}">
                        <a class="navbar-brand" href="adminhome">Work Management System</a>
                    </c:when>
                    <c:when test="${currentUser != null}">
                        <a class="navbar-brand" href="userhome">Work Management System</a>
                    </c:when>
                    <c:otherwise>
                        <a class="navbar-brand" href="home">Work Management System</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${currentUser != null}">
                            <li><a href="userhome"><span class="glyphicon glyphicon-log-in"></span>Home</a></li>
                            <li><a href="../logoutuser"></span>Log Out</a></li>
                        </c:when>
                        <c:when test="${adminUser != null}">
                            <li><a href="adminhome"><span class="glyphicon glyphicon-log-in"></span>Home</a></li>
                            <li><a href="../logoutuser"></span>Log Out</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="newaccount"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
                            <li><a href="newlogin"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</header>
