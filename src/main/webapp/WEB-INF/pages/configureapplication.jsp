<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Configure Application</h1>
    </div>
    <form action="adminhome.jsp">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label class="text-left">Database Server:</label>
            <select class="form-control">
                <option>MySQL</option>
                <option>Oracle</option>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label class="text-left">Industry Name:</label>
            <input type="text" class="form-control" placeholder="Industry Name">
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label class="text-left">Skills:</label>
            <input type="text" class="form-control" placeholder="Skill">
            <input type="submit" class="btn btn-default" style="margin-top: 10px">
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <table class="table table-striped">
                <thead>Skills</thead>
                <tbody>
                <tr>
                    <td>Java</td>
                </tr>
                <tr>
                    <td>Java</td>
                </tr>
                <tr>
                    <td>Java</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <h2 class="text-left">Change Admin Password:</h2>
            <label class="text-left">Old Password:</label>
            <input type="password" class="form-control" placeholder="Old Password">
            <label class="text-left">New Password:</label>
            <input type="password" class="form-control" placeholder="New Password">
            <label class="text-left">Confirm Password:</label>
            <input type="password" class="form-control" placeholder="Confirm Password">
            <input type="submit" class="btn btn-default" style="margin-top: 10px" value="Confirm Password">
        </div>
    </div>
</form>
</div>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>