<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>Application Settings</h1>
    </div>
    <form:form modelAttribute="passwordAdmin" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <label class="text-left">Current Password</label>
                <form:password path="password" class="form-control"/>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <label class="text-left">New Password</label>
                <form:password path="newPassword" class="form-control"/>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <label class="text-left">Current Password</label>
                <form:password path="confirmPassword" class="form-control"/>
            </div>

            <c:if test="${param.error == true}">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 alert alert-danger">
                    <label>There has been an error updating your password.</label>
                </div>
            </c:if>
            <c:if test="${param.success == true}">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 alert alert-success">
                    <label>You have successfully updated your password!</label>
                </div>
            </c:if>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <input type="submit" class="btn btn-default" value="Confirm Password" name="updatePassword">
            </div>
        </div>
    </form:form>
    <form:form modelAttribute="applicationAdmin" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label class="text-left">Industry Name:</label>
                <form:input path="industryName" class="form-control"/>
            </div>
            <c:if test="${param.applicationerror == true}">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 alert alert-danger">
                    <label><form:errors path="industryName"/></label>
                </div>
            </c:if>
            <c:if test="${param.applicationsuccess == true}">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 alert alert-success">
                    <label>You have successfully updated the Industry Name!</label>
                </div>
            </c:if>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <input type="submit" class="btn btn-default" value="Confirm Changes" name="newIndustry">
                <a class="contact btn btn-default" href="adminhome">Back</a>
            </div>
        </div>
    </form:form>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>