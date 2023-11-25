<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
  <h1>Welcome!</h1>
  <hr />
</header>

<section>
  <h4>Account details:</h4>

  <div class="row">
    <div class="col-lg-8">
      <table class="table">
        <thead class="table-dark">
          <tr>
            <th scope="col">Email</th>
            <th scope="col">Username</th>
            <th scope="col">Password</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>${user.email}</td>
            <td>${user.username}</td>
            <td>
              <span class="d-inline-block text-truncate" style="max-width: 16rem;" >
                ${user.password}
              </span>
            </td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</section>

