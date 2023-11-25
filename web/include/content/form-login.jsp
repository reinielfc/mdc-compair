<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
  <h1>Log In</h1>
  <hr />
</header>

<section>
  <div class="container row">
    <div class="col-md-10 col-lg-4">
      <div class="card">
        <div class="card-body">
          <p>Enter your credentials here:</p>

          <div class="container">
            <form id="login" action="<c:url value='/login' />" method="post">
              <c:import url="/include/form/login.jsp" charEncoding="UTF-8" />
            </form>
          </div>
        </div>
        <div class="card-footer d-flex align-items-end py-2">
          <button class="btn btn-primary ms-auto" type="submit" form="login">Log In</button>
        </div>
      </div>
    </div>
  </div>
</section>

