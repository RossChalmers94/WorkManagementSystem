<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>Configure Application</h1>
    </div>
    <form:form modelAttribute="newApplication" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label class="text-left">Database Server:</label>
                <form:input path="admin.databaseServer" class="form-control"/>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label class="text-left">Industry Name:</label>
                <form:input path="admin.industryName" class="form-control"/>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>Skills:</label>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <c:forEach items="${newApplication.skills}" var="skill" varStatus="i">
                    <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                        <form:input path="skills[${i.index}].skillName" class="form-control" />
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>Location:</label>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <c:forEach items="${newApplication.locations}" var="location" varStatus="i">
                    <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                        <form:input path="locations[${i.index}].locationName" class="form-control" />
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                Salary (Minimum and Maximum Range)
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                Job Length:
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <h2 class="text-left">Change Admin Password:</h2>
                <label class="text-left">Old Password:</label>
                <input type="password" class="form-control" placeholder="Old Password">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <label class="text-left">New Password:</label>
                <input type="password" class="form-control" placeholder="New Password">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <label class="text-left">Confirm Password:</label>
                <input type="password" class="form-control" placeholder="Confirm Password">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <input type="submit" class="btn btn-default" style="margin-top: 10px" value="Confirm Password">
            </div>
        </div>
        <input type="submit" class="btn btn-default" value="Confirm Changes">
    </form:form>
</div>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>