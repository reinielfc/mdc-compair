<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<c:import url="/include/head.jsp" charEncoding="UTF-8" />
<body class="has-navbar d-flex flex-column min-vh-100">
<c:import url="/include/navbar.jsp" charEncoding="UTF-8" />
<div class="container-fluid container-md py-3">
<c:import url="${templatePageContentURL}" charEncoding="UTF-8" />
</div>
<c:import url="/include/footer.jsp" charEncoding="UTF-8" />
</body>
</html>