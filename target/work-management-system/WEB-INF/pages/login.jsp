<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h3>Log In</h3>
        <form:form modelAttribute="loginUser" method="post">
        <div class="col-xs-8 col-sm-6 col-md-4 col-lg-4 col-sm-offset-3 col-md-offset-4 col-xs-offset-2">
            <div class="form-group">
                <label class="text-left">Username:</label>
                <form:input path="username" id="username" class="form-control" placeholder="Username"/>
            </div>
            <div class="form-group">
                <label class="text-left">Password:</label>
                <form:input path="password" id="password" class="form-control" placeholder="Password"/>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Log In">
            </div>
        </div>
        </form:form>
    </div>


</div>

<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>

</body>
</html>