<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
  <h1>
    <i class="fa fa-pen-to-square"></i>
    Edit User
  </h1>
  <hr />
</header>

<section>
  <div class="row">
    <div class="col-lg-8">
      <form action="<c:url value='/admin/user/edit' />" method="post">
        <input type="hidden" name="originalEmail" value="${DBUser.email}">
        <div class="card">
          <div class="card-header bg-dark p-0 m-0 pt-1"></div>
          <div class="card-body p-0">
            <div class="table-responsive">
              <table class="table table-striped m-0">
                <thead class="bg-dark text-light">
                  <tr>
                    <th scope="col">Field Name</th>
                    <th scope="col" class="w-75">
                      <div class="d-flex">
                        <div class="mt-auto me-auto">
                          Value
                        </div>
                        <button type="submit" name="action" value="save" class="btn btn-outline-light">
                          <i class="fa fa-floppy-disk"></i>
                          Save
                        </button>
                      </div>
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">Email</th>
                    <td>
                      <div class="input-group has-validation text-break">
                        <!-- prettier-ignore -->
                        <input class="form-control${emailValidationMessage == null ? '' : ' is-invalid'}" type="email" name="email" value="${DBUser.email}" placeholder="jsmith@example.com" required />
                        <c:if test="${emailValidationMessage != null}">
                          <div class="invalid-feedback">${emailValidationMessage}</div>
                        </c:if>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">Username</th>
                    <td>
                      <div class="input-group has-validation">
                        <!-- prettier-ignore -->
                        <input class="form-control${usernameValidationMessage == null ? '' : ' is-invalid'}" type="text" name="username" value="${DBUser.username}" placeholder="jsmith" required />
                        <c:if test="${usernameValidationMessage != null}">
                          <div class="invalid-feedback">${usernameValidationMessage}</div>
                        </c:if>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">Password</th>
                    <td>
                      <div class="input-group has-validation">
                        <!-- prettier-ignore -->
                        <input class="form-control${passwordValidationMessage == null ? '' : ' is-invalid'}" type="password" name="password" placeholder="********" />
                        <c:if test="${passwordValidationMessage != null}">
                          <div class="invalid-feedback">${passwordValidationMessage}</div>
                        </c:if>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">First Name</th>
                    <td>
                      <div class="input-group has-validation">
                        <!-- prettier-ignore -->
                        <input class="form-control${firstNameValidationMessage == null ? '' : ' is-invalid'}" type="text" name="firstName" value="${DBUser.firstName}" placeholder="John" required />
                        <c:if test="${firstNameValidationMessage != null}">
                          <div class="invalid-feedback">${firstNameValidationMessage}</div>
                        </c:if>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">Last Name</th>
                    <td>
                      <div class="input-group has-validation">
                        <!-- prettier-ignore -->
                        <input class="form-control${lastNameValidationMessage == null ? '' : ' is-invalid'}" type="text" name="lastName" value="${DBUser.lastName}" placeholder="Smith" required />
                        <c:if test="${lastNameValidationMessage != null}">
                          <div class="invalid-feedback">${lastNameValidationMessage}</div>
                        </c:if>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</section>

