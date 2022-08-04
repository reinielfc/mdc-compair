<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
  <h1 class="fw-bold">
    <span class="text-danger"><i class="fa fa-triangle-exclamation"></i></span>
    Java Error
    <i class="fa-brands fa-java"></i>
  </h1>
  <hr />
</header>

<div class="container row">
  <div class="col-lg-8">
    <p>Sorry, Java has thrown an exception.</p>
    <p>To continue, click the Back button.</p>

    <div class="card border-danger">
      <div class="card-header bg-danger text-white">
        <h3>
          <i class="fa-solid fa-circle-info"></i>
          Details
        </h3>
      </div>
      <div class="card-body">
        <p>Type: ${pageContext.exception["class"]}</p>
        <p>Message: ${pageContext.exception.message}</p>
      </div>
    </div>
  </div>
</div>

