<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	  <%@ page contentType="text/html;charset=utf-8" %>
	  <title>German Testing</title>
	  <style><%@include file="/style/main_style.css"%></style>
	  <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700" rel="stylesheet">
	 
 </head>
 
 <body>
 
  <div id="container">
	   <div id="header">Testing for citizenship</div><!шапка>	   
	   <div id="sidebar"> 
			<p><a href="http://nikbali.ru/" class="btn_menu">Finish the testing</a></p>
	   </div>
	   <script type="text/javascript" charset="utf-8"><%@include file="/js/scriptForTest.js"%></script>
	   <div id="content">
                    <script>loadData()</script>
			<h2>Testing</h2>
            <form name="quiz">
                <ol id = "process">
                    <script>
                    nextQuestion()
                    </script>
                </ol>
            <input type="button" onClick="Score()" value="Проверить результаты" />
            <input type="button" onClick="nextQuestion()" value="Следующий вопрос" />
            </form>
	   </div>	   
	   <div id="footer">&copy; Nikita Balily</div>
  </div> 
 </body>
</html>
