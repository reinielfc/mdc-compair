<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Log In</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style/main.css" />
  </head>
  <body>
    <header>
      <nav>
        <ul>
          <li>
            <h1>CompAir</h1>
          </li>
        </ul>
      </nav>
    </header>

    <article>
      <h1>Log In</h1>
      <section>
        <form action="login" method="post">
          <div class="form-field">
            <table>
              <tr>
                <td><p style="color: red;">${message}</p></td>
              </tr>
              <tr>
                <td><label for="username">Username</label></td>
              </tr>
              <tr>
                <td><input type="text" name="username" /></td>
              </tr>
              <tr>
                <td><label for="password">Password</label></td>
              </tr>
              <tr>
                <td><input type="password" name="password" /></td>
              </tr>
              <tr>
                <td><input class="blue-button" style="width: 100%" type="submit" value="SUBMIT" /></td>
              </tr>
            </table>
          </div>
        </form>
      </section>
    </article>
    <jsp:include page="/include/footer.jsp" /> 