<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>Configure Application</h1>
    </div>
    <c:if test="${param.error == true}">
        <div class="row">
            <div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <c:if test="${error == true}">
                    <label>There has been an error.</label><br>
                </c:if>
                <c:if test="${param.skills == true}">
                    <label>There has been an error submitting a new skill.
                        Please ensure the skill is between 5-20 characters.</label>
                </c:if>
                <c:if test="${param.locations == true}">
                    <label>There has been an error submitting a new location.
                        Please ensure the location is between 5-20 characters.</label>
                </c:if>
            </div>
        </div>
    </c:if>
    <div class="row">
        <form:form modelAttribute="configureSkills" method="post">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Skills:</label>
            <table class="table table-sm table-striped table-hover table-bordered">
                <thead class="thead">
                <tr>
                    <td>Skill Name</td>
                    <td>Remove?</td>
                </tr>
                </thead>
                <c:forEach items="${configureSkills.skills}" varStatus="i">
                    <tr>
                        <td><label>${configureSkills.skills[i.index].skillName}</label></td>
                        <td><form:checkbox path="skillsSet" value="${configureSkills.skills[i.index].skillID}"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td><input type="submit" class="text-right contact btn btn-default" name="deleteskill"
                               value="Delete Skills"></td>
                </tr>
            </table>
            <form:input path="skillName" class="form-control" placeholder="Skill Name"/>
            <form:errors path="skillName" class="alert alert-danger"/>
            <input type="submit" class="contact btn btn-default" name="newSkill" value="Add Skill">
        </div>
        </form:form>
        <form:form modelAttribute="configureLocations" method="post">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Location:</label>
            <table class="table table-striped table-sm table-hover table-bordered">
                <thead class="thead">
                <tr>
                    <td>Location</td>
                    <td>Remove?</td>
                </tr>
                </thead>
                <c:forEach items="${configureLocations.locations}" varStatus="i">
                    <tr>
                        <td><label>${configureLocations.locations[i.index].locationName}</label></td>
                        <td><form:checkbox path="locationSet"
                                           value="${configureLocations.locations[i.index].locationID}"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td><input type="submit" class="text-right contact btn btn-default" name="deletelocation"
                               value="Delete Location"></td>
                </tr>
            </table>
            <form:input path="locationName" class="form-control" placeholder="Location Name"/>
            <form:errors path="locationName" class="alert alert-danger"/>
            <input type="submit" class="contact btn btn-default" name="newLocation" value="Add Location">
        </div>
        </form:form>
    </div>
    <form:form modelAttribute="configureSalaries" method="post">
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
                <c:forEach items="${configureSalaries.salarys}" varStatus="i">
                    <tr>
                        <td>${(i.index) + 1}</td>
                        <td><form:select path="salarys[${i.index}].salaryMinValue" class="form-control">
                            <form:option value="10000">10,000</form:option>
                            <form:option value="20000">20,000</form:option>
                            <form:option value="30000">30,000</form:option>
                            <form:option value="40000">40,000</form:option>
                            <form:option value="50000">50,000</form:option>
                            <form:option value="60000">60,000</form:option>
                            <form:option value="70000">70,000</form:option>
                            <form:option value="80000">80,000</form:option>
                            <form:option value="90000">90,000</form:option>
                            <form:option value="100000">100,000</form:option>
                            <form:option value="110000">110,000</form:option>
                            <form:option value="120000">120,000</form:option>
                        </form:select></td>
                        <td><form:select path="salarys[${i.index}].salaryMaxValue" class="form-control">
                            <form:option value="10000">10,000</form:option>
                            <form:option value="20000">20,000</form:option>
                            <form:option value="30000">30,000</form:option>
                            <form:option value="40000">40,000</form:option>
                            <form:option value="50000">50,000</form:option>
                            <form:option value="60000">60,000</form:option>
                            <form:option value="70000">70,000</form:option>
                            <form:option value="80000">80,000</form:option>
                            <form:option value="90000">90,000</form:option>
                            <form:option value="100000">100,000</form:option>
                            <form:option value="110000">110,000</form:option>
                            <form:option value="120000">120,000</form:option>
                        </form:select></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" class="btn btn-default" value="Confirm Salaries" name="newSalaries">
            <c:if test="${salarySuccess == true}">
                <div class="alert alert-success">
                    <label>You have successfully updated the Salary Ranges!</label>
                </div>
            </c:if>
        </div>

        </form:form>
        <form:form modelAttribute="configureJobLengths" method="post">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Job Length (Months):</label>
            <table class="table table-striped table-sm table-hover table-bordered">
                <thead class="thead">
                <tr>
                    <td>#</td>
                    <td>Minimum Time</td>
                    <td>Maximum Time</td>
                </tr>
                </thead>
                <c:forEach items="${configureJobLengths.jobLengths}" varStatus="i">
                    <tr>
                        <td>${(i.index) + 1}</td>
                        <td><form:select path="jobLengths[${i.index}].jobLengthMin" class="form-control">
                            <form:option value="1">1</form:option>
                            <form:option value="2">2</form:option>
                            <form:option value="3">3</form:option>
                            <form:option value="4">4</form:option>
                            <form:option value="5">5</form:option>
                            <form:option value="6">6</form:option>
                            <form:option value="7">7</form:option>
                            <form:option value="8">8</form:option>
                            <form:option value="9">9</form:option>
                            <form:option value="10">10</form:option>
                            <form:option value="11">11</form:option>
                            <form:option value="12">12</form:option>
                        </form:select></td>
                        <td><form:select path="jobLengths[${i.index}].jobLengthMax" class="form-control">
                            <form:option value="1">1</form:option>
                            <form:option value="2">2</form:option>
                            <form:option value="3">3</form:option>
                            <form:option value="4">4</form:option>
                            <form:option value="5">5</form:option>
                            <form:option value="6">6</form:option>
                            <form:option value="7">7</form:option>
                            <form:option value="8">8</form:option>
                            <form:option value="9">9</form:option>
                            <form:option value="10">10</form:option>
                            <form:option value="11">11</form:option>
                            <form:option value="12">12</form:option>
                        </form:select></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" class="btn btn-default" value="Confirm Job Lengths" name="newJobLengths">
            <c:if test="${jobLengthSuccess == true}">
                <div class="alert alert-success">
                    <label>You have successfully updated the Job Lengths!</label>
                </div>
            </c:if>
        </div>
    </div>
    <div class="row">
        <a class="contact btn btn-default" href="adminhome">Back</a>
    </div>
    </form:form>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>