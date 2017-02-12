<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Manage Users</h1>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <h3 class="text-left">Freelancers</h3>
            <table class="table table-striped">
                <tbody>
                <tr>
                    <td>Freelancer1</td>
                    <td><input type="checkbox" name="freelancer1"></td>
                </tr>
                <tr>
                    <td>Freelancer2</td>
                    <td><input type="checkbox" name="freelancer2"></td>
                </tr>
                <tr>
                    <td>Freelancer3</td>
                    <td><input type="checkbox" name="freelancer3"></td>
                </tr>
                </tbody>
            </table>
            <a class="contact btn btn-default">Confirm Deletions</a>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <h3 class="text-left">Employers</h3>
            <table class="table table-striped">
                <tbody>
                <tr>
                    <td>Employer1</td>
                    <td><input type="checkbox" name="employer1"></td>
                </tr>
                <tr>
                    <td>Employer2</td>
                    <td><input type="checkbox" name="employer2"></td>
                </tr>
                <tr>
                    <td>Employer3</td>
                    <td><input type="checkbox" name="employer3"></td>
                </tr>
                </tbody>
            </table>
            <a class="contact btn btn-default">Confirm Deletions</a>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <h3 class="text-left">Current Matches</h3>
            <table class="table table-striped">
                <tbody>
                <tr>
                    <td>Freelancer1</td>
                    <td>Employer1</td>
                    <td><input type="checkbox" name="employer1"></td>
                </tr>
                <tr>
                    <td>Freelancer2</td>
                    <td>Employer2</td>
                    <td><input type="checkbox" name="employer2"></td>
                </tr>
                <tr>
                    <td>Freelancer3</td>
                    <td>Employer3</td>
                    <td><input type="checkbox" name="employer3"></td>
                </tr>
                </tbody>
            </table>
            <a class="contact btn btn-default">Confirm Deletions</a>
        </div>
    </div>
</div>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>