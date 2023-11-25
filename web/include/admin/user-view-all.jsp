<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
  <h1>
    <i class="fa fa-table"></i>
    User Table
  </h1>
  <hr />
</header>

<section>
  <div class="row">
    <div class="col-lg-8">
      <c:if test="${message != null}">
        <p>${message}</p>
      </c:if>
      <div class="card">
          <div class="card-header bg-dark m-0 p-0 pt-1"></div>
        <div class="card-body p-0 overflow-auto">
          <table class="table table-striped m-0">
            <thead class="bg-dark text-light">
              <tr>
                <th scope="col" class="p-1">
                  <a class="btn btn-outline-light btn-sm d-block" href="<c:url value='/admin/user/add' />">
                    <i class="fa fa-plus"></i>
                  </a>
                </th>
                <th scope="col">Email</th>
                <th scope="col">Username</th>
                <th scope="col">Password</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${DBUserList}" var="DBUser">
                <tr>
                  <td class="p-1">
                    <form action="<c:url value='/admin/user/edit' />" method="post">
                      <button class="btn btn-link text-primary d-block" type="submit" name="email" value="${DBUser.email}">
                        <i class="fa fa-pen-to-square"></i>
                      </button>
                    </form>
                  </td>
                  <td>${DBUser.email}</td>
                  <td>${DBUser.username}</td>
                  <td>
                    <span class="d-inline-block text-truncate" style="max-width: 16rem">${DBUser.password}</span>
                  </td>
                  <td>${DBUser.firstName}</td>
                  <td>${DBUser.lastName}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</section>

