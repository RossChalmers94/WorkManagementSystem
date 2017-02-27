<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>Change Password</h1>
    </div>
    <form:form modelAttribute="admin" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>Change Password</label>
            <table class="table table-striped table-sm table-hover table-bordered">
                <thead class="thead">
                <tr>
                    <td>Current Password</td>
                    <td>New Password</td>
                    <td>Confirm Password</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><form:password path="password" class="form-control"/></td>
                    <td><form:password path="newPassword" class="form-control"/></td>
                    <td><form:password path="confirmPassword" class="form-control"/></td>
                </tr>
                </tbody>
            </table>
            </div>
            <c:if test="${error == true}">
            <div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>There has been an error.</label>
            </div>
            </c:if>
            <c:if test="${success == true}">
                <div class="alert alert-success col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <label>You have successfully updated your password!</label>
                </div>
            </c:if>
            <input type="submit" class="btn btn-default" value="Confirm Changes">
        </div>
    </form:form>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>