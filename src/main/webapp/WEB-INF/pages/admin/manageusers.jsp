<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Manage Users</h1>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Skills:</label>
            <table class="table table-sm table-striped table-hover table-bordered">
                <thead class="thead">
                <tr>
                    <td>Freelancers</td>
                    <td>Remove?</td>
                </tr>
                </thead>
                <c:forEach items="${skills}" varStatus="i">
                    <tr>
                        <td><label>${skills[i.index].skillName}</label></td>
                        <td><form:checkbox path="skillsSet" value="${skills[i.index].skillID}"/></td>
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
            <label>Skills:</label>
            <table class="table table-sm table-striped table-hover table-bordered">
                <thead class="thead">
                <tr>
                    <td>Skill Name</td>
                    <td>Remove?</td>
                </tr>
                </thead>
                <c:forEach items="${skills}" varStatus="i">
                    <tr>
                        <td><label>${skills[i.index].skillName}</label></td>
                        <td><form:checkbox path="skillsSet" value="${skills[i.index].skillID}"/></td>
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
    </div>
</div>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>