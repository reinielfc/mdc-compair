<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar navbar-expand-md navbar-dark py-3 bg-dark fixed-top">
  <div class="container-fluid container-md">
    <a class="navbar-brand" href="<c:url value='/' />">
      <img src="<c:url value='/image/logo.png' />" height="48" alt="CompAir">
    </a>

    <div class="button navbar-toggler" data-bs-target="#navmenu" data-bs-toggle="collapse" type="button">
      <span class="navbar-toggler-icon"></span>
    </div>

    <div id="navmenu" class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto">
        <div class="nav-item">
          <a href="<c:url value='/admin/user' />" class="nav-link">
            <i class="fa fa-table"></i>
            User Table
          </a>
        </div>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a href="<c:url value='/login' />" class="btn btn-outline-secondary py-2 px-1">
            <i class="fa fa-right-to-bracket"></i>
            Log In
          </a>
        </li>
      </ul>
    </div>
  </div>
</div>

