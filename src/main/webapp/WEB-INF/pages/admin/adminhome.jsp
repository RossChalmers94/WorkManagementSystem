<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Admin Home</h1>
    </div>
    <div class="row text-center">
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 col-md-offset-3 col-lg-offset-3">
            <a class="selection btn btn-default" href="configureapplication">Configure Application</a>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <a class="selection btn btn-default" href="manageusers">Manage Users</a>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
            <a class="selection btn btn-default" href="adminpassword">Application Settings</a>
        </div>
    </div>
</div>
</div>
</body>
<footer class="footer">
    <div class="container"></div>
</footer>
</html>