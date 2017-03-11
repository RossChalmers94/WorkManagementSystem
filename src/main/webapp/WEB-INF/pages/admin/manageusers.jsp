<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>Manage Users</h1>
    </div>
    <form:form modelAttribute="manageUsers" method="post">
    <div class="row">
        <h2 class="text-left">Users</h2>
        <label>This is a list of all the users in the system.</label>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <c:forEach items="${users}" varStatus="i">
                <div class="users col-xs-12 col-sm-6 col-md-4 col-lg-4">
                    <label>Username: ${users[i.index].username}</label><br>
                    <label>First Name: ${users[i.index].userPersonal.firstname}</label><br>
                    <label>Last Name: ${users[i.index].userPersonal.lastname}</label><br>
                    <c:choose>
                        <c:when test="${users[i.index].freelancerID != 0}">
                            <label>Freelancer ID: ${users[i.index].freelancerID}</label><br>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${users[i.index].employerID != 0}">
                            <label>Employer ID: ${users[i.index].employerID}</label><br>
                        </c:when>
                    </c:choose>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2 class="text-left">Freelancers</h2>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>This is all of the Freelancers currently active in the system. To delete a Freelancer, select the
                    checkbox
                    associated with the Freelancer and then select the Delete Freelancer button below.</label>
            </div>
            <c:forEach items="${freelancers}" varStatus="i">
                <div class="users col-xs-12 col-sm-6 col-md-4 col-lg-4">
                    <label>Freelancer ID: ${freelancers[i.index].workerID}</label>
                    <form:checkbox path="freelancers" value="${freelancers[i.index].workerID}"/>
                </div>
            </c:forEach>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <input type="submit" class="text-right contact btn btn-default" name="deletefreelancer"
                   value="Delete Freelancer">
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2 class="text-left">Employers</h2>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>This is all of the Employers currently active in the system. To delete an Employer, select the
                    checkbox associated with the Employer and then select the Delete Employer button below.</label>
            </div>
            <c:forEach items="${employers}" varStatus="i">
                <div class="users col-xs-12 col-sm-6 col-md-4 col-lg-4">
                    <label>Employer ID: ${employers[i.index].workerID}</label>
                    <form:checkbox path="employers" value="${employers[i.index].workerID}"/>
                </div>
            </c:forEach>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <input type="submit" class="text-right contact btn btn-default" name="deleteemployer"
                   value="Delete Employer">
        </div>
    </div>
    <div class="row">
        <h2 class="text-left">Matches</h2>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <label>This is a list of all the matches currently stored in the system. To delete a match,
                select the checkbox associated with the match and then select the Delete Matches button below.</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <c:forEach items="${matches}" varStatus="i">
                <div class="users col-xs-12 col-sm-6 col-md-4 col-lg-4">
                    <label>Match ID: ${matches[i.index].matchID}</label><br>
                    <label>Freelancer ID: ${matches[i.index].freelancerID}</label><br>
                    <label>Employer ID: ${matches[i.index].employerID}</label><br>
                    <form:checkbox path="matches" value="${matches[i.index].matchID}"/>
                </div>
            </c:forEach>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <input type="submit" class="text-right contact btn btn-default" name="deletematch"
                   value="Delete Matches">
            <a class="contact btn btn-default" href="adminhome">Back</a>
        </div>
        </form:form>
    </div>
    </body>
</div>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>