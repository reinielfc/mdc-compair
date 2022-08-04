<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- email -->
<div class="form-group">
  <label for="login-email">Email</label>
  <div class="input-group">
    <input
      class="form-control"
      id="login-email"
      name="email"
      type="email"
      value="${email}"
      placeholder="jsmith@example.com"
      required
    />
    <span class="input-group-text">
      <i class="fa fa-envelope"></i>
    </span>
  </div>
</div>

<!-- password -->
<div class="form-group">
  <label for="login-password">Password</label>
  <div class="input-group">
    <input
      class="form-control"
      id="login-password"
      name="password"
      type="password"
      value="${password}"
      placeholder="********"
      required="required"
    />
    <span class="input-group-text">
      <i class="fa fa-lock"></i>
    </span>
  </div>
</div>

<c:if test="${!message.blank}">
  <p class="small my-1 text-${messageType == null ? 'danger' : messageType}">${message}</p>
</c:if>