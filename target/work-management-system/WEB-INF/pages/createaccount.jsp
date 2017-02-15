<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h3>Create Account</h3>
        <form:form method="post" modelAttribute="newUser">
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
                <label class="radio-inline"><form:radiobutton path="role" value="freelancer"/>Freelancer</label>
                <label class="radio-inline"><form:radiobutton path="role" value="employer"/>Employer</label>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Create Account">
            </div>
        </div>
        </form:form>

        ${value}
        ${currentUser.username}
    </div>


</div>

<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>

</body>
</html>