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
                <form:password path="password" id="password" class="form-control" placeholder="Password"/>
            </div>
            <div class="form-group">
                <label class="radio-inline"><form:radiobutton path="role" value="Freelancer"/>Freelancer</label>
                <label class="radio-inline"><form:radiobutton path="role" value="Employer"/>Employer</label>
            </div>
            <c:if test="${error == true}">
                <div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <label><form:errors path="username"/><br>
                    <form:errors path="password"/><br>
                    <form:errors path="role"/></label>
                </div>
            </c:if>
            <c:if test="${exists == true}">
                <div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <label>An account already exists with the username you have provided.</label>
                </div>
            </c:if>
            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Create Account">
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