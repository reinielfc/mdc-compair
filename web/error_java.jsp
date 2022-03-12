<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="style/main.css" type="text/css" />
  </head>
  <body>
    <header>
        <h1>Java Error</h1>
    </header>
    <section>
      <p>Sorry, Java has thrown an exception.</p>
      <p>To continue, click the Back button.</p>

      <h2>Details</h2>
      <p>Type: ${pageContext.exception["class"]}</p>
      <p>Message: ${pageContext.exception.message}</p>
    </section>
  </body>
</html>
