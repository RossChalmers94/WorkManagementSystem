<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>Become an ${currentUser.role}</h1>
    </div>
    <form:form modelAttribute="newWorker" method="post">
    <c:if test="${currentUser.role == 'Employer'}">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <h3 class="text-left">Job Title</h3>
            <form:input path="jobTitle" id="jobTitle" class="form-control" placeholder="Job Title"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <h3 class="text-left">Job Description</h3>
            <form:textarea path="jobDescription" id="jobDescription" class="form-control" rows="10"/>
        </div>
    </div>
    </c:if>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 class="text-left">Skills</h3>
        </div>
        <c:forEach items="${application.skills}" var="skills" varStatus="i">
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                <label><form:checkbox path="skill" value="${application.skills[i.index].skillID}"/>
                        ${application.skills[i.index].skillName}</label>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 class="text-left">Location</h3>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <form:select path="location" class="form-control">
                <c:forEach items="${application.locations}" var="locations" varStatus="i">
                    <form:option value="${application.locations[i.index].locationID}"
                                 label="${application.locations[i.index].locationName}"/>
                </c:forEach>
            </form:select>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 class="text-left">Salary (In Pounds)</h3>
        </div>
        <c:forEach items="${application.salarys}" var="salarys" varStatus="i">
            <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                <label class="text-center radio-inline"><form:radiobutton path="salary"
                                                                          value="${application.salarys[i.index].salaryID}"/>
                        ${application.salarys[i.index].salaryMinValue}
                    - ${application.salarys[i.index].salaryMaxValue}</label>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 class="text-left">Time To Complete Work (Months)</h3>
        </div>
        <c:forEach items="${application.jobLengths}" var="joblengths" varStatus="i">
            <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                <label class="text-center radio-inline"><form:radiobutton path="jobLength"
                                                                          value="${application.jobLengths[i.index].jobLengthID}"/>
                        ${application.jobLengths[i.index].jobLengthMin} -
                        ${application.jobLengths[i.index].jobLengthMax}</label>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 class="text-left">Freelancer Rating (Minimum Rating)</h3>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="rating"  value="1"/>Poor</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="rating"  value="2"/>Below Average</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="rating" value="3"/>Average</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="rating"  value="4"/>Good</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="rating" value="5"/>Excellent</label>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 class="text-left">Relax Preferences After</h3>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="relaxPreferences" value="1"/>Never</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="relaxPreferences" value="2"/>1 Week</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="relaxPreferences" value="3"/>2 Weeks</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="relaxPreferences" value="4"/>3 Weeks</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <label class="text-center radio-inline"><form:radiobutton path="relaxPreferences" value="5"/>4 Weeks</label>
        </div>
    </div>

        ${user.employerID}
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <input type="submit" class="btn btn-default" value="Confirm">
        </div>
    </div>
    </form:form>
</div>
    </body>
    <footer class="footer">
        <div class="container">
            Work Management System
        </div>
    </footer>
</html>