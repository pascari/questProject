<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import = "java.util.*" session="true"%>

<html>
<head>
    <title>Quest Game</title>
    <link href="css/index.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<div>
    <h1>Enjoy the Quest</h1>
    <button onclick="window.location='game'">Start</button>
</div>
<div>
    <table>
        <tr>
            <td>
                <p><b>Statistics:</b></p>
                Today's date: <%= (new java.util.Date())%>
                <%
                    Integer counter = (Integer)session.getAttribute("counter");
                    if (counter == null) {
                        counter = 1;
                    } else {
                        counter = counter+ 1;
                    }
                    session.setAttribute("counter", counter);
                %>
                <br>
                Session ID: <%=session.getId()%>
                <br>
                Session creation time: <%=new Date(session.getCreationTime())%>
                <br>
                Number of times you've been here: <%=counter%>
                <br>
                <%! String ipAddress; %>
                <%
                    ipAddress = request.getRemoteAddr();
                %>
                Your IP Address : <%=ipAddress %>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
