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
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <label>All users with a Work Management System account.</label>
            </div>
            <c:forEach items="${users}" varStatus="i">
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <table class="table table-sm table-striped table-hover table-bordered">
                        <tr>
                            <td>Username:</td>
                            <td>${users[i.index].username}</td>
                        </tr>
                        <tr>
                            <td>First Name:</td>
                            <td>${users[i.index].userPersonal.firstname}</td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td>${users[i.index].userPersonal.lastname}</td>
                        </tr>
                        <tr>
                            <c:choose>
                                <c:when test="${users[i.index].freelancerID != 0}">
                                    <td>Freelancer ID</td>
                                    <td>${users[i.index].freelancerID}</td>
                                </c:when>
                                <c:when test="${users[i.index].employerID != 0}">
                                    <td>Employer ID</td>
                                    <td>${users[i.index].employerID}</td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </table>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2 class="text-left">Freelancers</h2>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>All Freelancers in the Work Management System. To delete a Freelancer, select the
                    checkbox
                    associated with the Freelancer and then select the Delete Freelancer button below.</label>
            </div>
            <c:forEach items="${freelancers}" varStatus="i">
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <table class="table table-sm table-striped table-hover table-bordered">
                        <thead class="thead">
                        <tr>
                            <td>FreelancerID</td>
                            <td>Remove?</td>
                        </tr>
                        </thead>
                        <tr>
                            <td>${freelancers[i.index].workerID}</td>
                            <td><form:checkbox path="freelancers" value="${freelancers[i.index].workerID}"/></td>
                        </tr>
                    </table>
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
                <label>All Employers in the Work Management System. To delete an Employer, select the
                    checkbox associated with the Employer and then select the Delete Employer button below.</label>
            </div>
            <c:forEach items="${employers}" varStatus="i">
                <div class="users col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <table class="table table-sm table-striped table-hover table-bordered">
                        <thead class="thead">
                        <tr>
                            <td>EmployerID</td>
                            <td>Remove?</td>
                        </tr>
                        </thead>
                        <tr>
                            <td>${employers[i.index].workerID}</td>
                            <td><form:checkbox path="employers" value="${employers[i.index].workerID}"/></td>
                        </tr>
                    </table>
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
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <label>All Matches between Freelancers and Employers in the Work Management System. To delete a match,
                select the checkbox associated with the match and then select the Delete Matches button below.</label>
            </div>
            <c:forEach items="${matches}" varStatus="i">
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <table class="table table-sm table-striped table-hover table-bordered">
                        <tr>
                            <td>MatchID:</td>
                            <td>${matches[i.index].matchID}</td>
                        </tr>
                        <tr>
                            <td>FreelancerID:</td>
                            <td>${matches[i.index].freelancerID}</td>
                        </tr>
                        <tr>
                            <td>EmployerID:</td>
                            <td>${matches[i.index].employerID}</td>
                        </tr>
                        <tr>
                            <td>Remove?</td>
                            <td><form:checkbox path="matches" value="${matches[i.index].matchID}"/></td>
                        </tr>
                    </table>
                </div>
                    <%--<label>Match ID: </label><br>--%>
                    <%--<label>Freelancer ID: </label><br>--%>
                    <%--<label>Employer ID: </label><br>--%>
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