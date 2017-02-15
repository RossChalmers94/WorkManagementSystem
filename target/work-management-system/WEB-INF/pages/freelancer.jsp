<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Become a Freelancer</h1>
    </div>
        <div class="row">
            <h3 class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-left">Personal Details</h3>
            <form action="freelancerpreferences">
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label for="first_name_freelancer">First Name:</label>
                    <input type="text" class="form-control" id="first_name_freelancer"
                           placeholder="First Name"
                            maxlength="20">
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label for="last_name_freelancer">Last Name:</label>
                    <input type="text" class="form-control" id="last_name_freelancer"
                           placeholder="Last Name"
                            maxlength="20">
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label for="telephone_no_freelancer">Telephone No:</label>
                    <input type="text" class="form-control" id="telephone_no_freelancer"
                           placeholder="Telephone No"
                            maxlength="20">
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label for="email_address_freelancer">Email Address:</label>
                    <input type="email" class="form-control" id="email_address_freelancer"
                           placeholder="Email Address"
                            maxlength="40">
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label for="address_freelancer">Address:</label>
                    <input type="text" class="form-control" id="address_freelancer" placeholder="Address"
                            maxlength="100">
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label for="postcode_freelancer">Postcode:</label>
                    <input type="text" class="form-control" id="postcode_freelancer" placeholder="Postcode"
                            maxlength="7">
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label for="town_city_freelancer">Town/City:</label>
                    <input type="text" class="form-control" id="town_city_freelancer"
                           placeholder="Town/City"
                            maxlength="20">
                </div>
                <div class="submit details col-md-12 col-xs-12 col-sm-12">
                    <input type="submit" class="btn btn-default" value="Confirm">
                </div>

            </form>
        </div>
</div>
</body>
<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>
</html>