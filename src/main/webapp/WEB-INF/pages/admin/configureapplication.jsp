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
            <c:if test="${param.error == true}">
                <div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <c:if test="${param.preferences == true}">
                        <label>There has been an error submitting salaries and job lengths.</label>
                    </c:if>
                    <c:if test="${param.skills == true}">
                        <label>There has been an error submitting a new skill.<br>
                        Please ensure the skill is between 5-20 characters.</label>
                    </c:if>
                    <c:if test="${param.locations == true}">
                        <label>There has been an error submitting a new location..<br>
                            Please ensure the locations is between 5-20 characters.</label>
                    </c:if>
                </div>
            </c:if>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label>Skills:</label>
                <table class="table table-sm table-striped table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>Skill Name</td>
                        <td>Remove?</td>
                    </tr>
                    </thead>
                    <c:forEach items="${newApplication.skills}" varStatus="i">
                        <tr>
                            <td><label>${newApplication.skills[i.index].skillName}</label></td>
                            <td><form:checkbox path="skillsSet" value="${newApplication.skills[i.index].skillID}"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td><input type="submit" class="text-right contact btn btn-default" name="deleteskill"
                                   value="Delete Skills"></td>
                    </tr>
                </table>
                <form:input path="skill.skillName" class="form-control" placeholder="Skill Name"/>
                <input type="submit" class="contact btn btn-default" name="addskill" value="Add Skill">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label>Location:</label>
                <table class="table table-striped table-sm table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>Location</td>
                        <td>Remove?</td>
                    </tr>
                    </thead>
                    <c:forEach items="${newApplication.locations}" varStatus="i">
                        <tr>
                            <td><label>${newApplication.locations[i.index].locationName}</label></td>
                            <td><form:checkbox path="locationSet" value="${newApplication.locations[i.index].locationID}"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td><input type="submit" class="text-right contact btn btn-default" name="deletelocation"
                                   value="Delete Location"></td>
                    </tr>
                </table>
                <form:input path="location.locationName" class="form-control" placeholder="Location Name"/>
                <input type="submit" class="contact btn btn-default" name="addlocation" value="Add Location">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label>Salary (Minimum and Maximum Range)</label>
                <table class="table table-striped table-sm table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>#</td>
                        <td>Minimum Range</td>
                        <td>Maximum Range</td>
                    </tr>
                    </thead>
                    <c:forEach items="${newApplication.salarys}" varStatus="i">
                    <tr>
                        <td>${(i.index) + 1}</td>
                        <td><form:input path="salarys[${i.index}].salaryMinValue" class="form-control"/></td>
                        <td><form:input path="salarys[${i.index}].salaryMaxValue" class="form-control"/></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label>Job Length:</label>
                <table class="table table-striped table-sm table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>#</td>
                        <td>Minimum Time</td>
                        <td>Maximum Time</td>
                    </tr>
                    </thead>
                <c:forEach items="${newApplication.jobLengths}" var="jobLength" varStatus="i">
                    <tr>
                        <td>${(i.index) + 1}</td>
                        <td><form:input path="jobLengths[${i.index}].jobLengthMin" class="form-control" /></td>
                        <td><form:input path="jobLengths[${i.index}].jobLengthMax" class="form-control"/></td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-default" value="Confirm Changes" name="configureapplication">
        </div>
    </form:form>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>