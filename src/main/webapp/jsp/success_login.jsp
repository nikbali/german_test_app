<html>
<body>
<h2>Success</h2>
   <p>Login:   <%= ((model.User) request.getAttribute("user")).getLogin()%></p>
   <p>Name: <%= ((model.User) request.getAttribute("user")).getFirst_name() + " " + ((model.User) request.getAttribute("user")).getLast_name() %></p>
   <p>Date registration: <%= ((model.User) request.getAttribute("user")).getDate_registration()%></p>
   <p><a href="/">On Main  Page</a></p>
</body>
</html>