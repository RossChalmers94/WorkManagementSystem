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
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <label>Users</label>
                <table class="table table-sm table-striped table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>Username</td>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>FreelancerID</td>
                        <td>EmployerID</td>
                    </tr>
                    </thead>
                    <c:forEach items="${users}" varStatus="i">
                        <tr>
                            <td><label>${users[i.index].username}</label></td>
                            <td><label>${users[i.index].firstname}</label></td>
                            <td><label>${users[i.index].lastname}</label></td>
                            <c:choose>
                                <c:when test="${users[i.index].freelancerID == 0}">
                                    <td><label>N/A</label></td>
                                </c:when>
                                <c:otherwise>
                                    <td><label>${users[i.index].freelancerID}</label></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${users[i.index].employerID == 0}">
                                    <td><label>N/A</label></td>
                                </c:when>
                                <c:otherwise>
                                    <td><label>${users[i.index].employerID}</label></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label>Freelancers</label>
                <table class="table table-sm table-striped table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>Freelancers</td>
                        <td>Remove?</td>
                    </tr>
                    </thead>
                    <c:forEach items="${freelancers}" varStatus="i">
                        <tr>
                            <td><label>${freelancers[i.index].workerID}</label></td>
                            <td><form:checkbox path="freelancers" value="${freelancers[i.index].workerID}"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td><input type="submit" class="text-right contact btn btn-default" name="deletefreelancer"
                                   value="Delete Freelancer"></td>
                    </tr>
                </table>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label>Employers</label>
                <table class="table table-sm table-striped table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>Employers</td>
                        <td>Remove?</td>
                    </tr>
                    </thead>
                    <c:forEach items="${employers}" varStatus="i">
                        <tr>
                            <td><label>${employers[i.index].workerID}</label></td>
                            <td><form:checkbox path="employers" value="${employers[i.index].workerID}"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td><input type="submit" class="text-right contact btn btn-default" name="deleteemployer"
                                   value="Delete Employer"></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <label>Matches</label>
                <table class="table table-sm table-striped table-hover table-bordered">
                    <thead class="thead">
                    <tr>
                        <td>MatchID</td>
                        <td>FreelancerID</td>
                        <td>EmployerID</td>
                        <td>Remove?</td>
                    </tr>
                    </thead>
                    <c:forEach items="${matches}" varStatus="i">
                        <tr>
                            <td><label>${matches[i.index].matchID}</label></td>
                            <td><label>${matches[i.index].freelancerID}</label></td>
                            <td><label>${matches[i.index].employerID}</label></td>
                            <td><form:checkbox path="matches" value="${matches[i.index].matchID}"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><input type="submit" class="text-right contact btn btn-default" name="deletematch"
                                   value="Delete Match"></td>
                    </tr>
                </table>
            </div>
        </div>
    </form:form>
</div>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>