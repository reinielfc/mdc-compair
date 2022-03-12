<!DOCTYPE html>
<html>
  <head>
    <title>CompAir</title>
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
      <h1>Welcome!</h1>
      <h2>These are your credentials:</h2>

      <section>
        <p>Username: ${user.username}</p>
        <p>Password: ${user.password}</p>
      </section>
    </article>
    <jsp:include page="/include/footer.jsp" />

