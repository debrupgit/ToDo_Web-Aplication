<%@page import="org.jsp.todo_app.dto.Task"%>
<%@page import="org.jsp.todo_app.dao.TodoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
    
        body {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    background: linear-gradient(19deg, #21D4FD 0%, #B721FF 100%);
    min-height: 100vh;
}

.container {
    max-width: 400px;
    margin: 50px auto;
    padding: 20px;
    background-color: #333;
    border-radius: 10px;
}

.task-form fieldset {
    border: none;
}

.task-form legend {
    color: #fff;
    font-size: 20px;
    margin-bottom: 20px;
    text-align: center;
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;
    color: #fff;
    margin-bottom: 5px;
}

.form-group input[type="text"],
.form-group textarea,
.form-group input[type="file"] {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 5px;
    background-color: #fff;
    box-shadow: none;
    transition: box-shadow 0.3s;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.form-group input[type="text"]:focus,
.form-group textarea:focus,
.form-group input[type="file"]:focus {
    box-shadow: 0 0 10px rgba(0, 255, 255, 0.5);
}

.form-group textarea {
    height: 100px;
}

.update-btn,
.cancel-btn {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    color: #fff;
    font-weight: bold;
    transition: background-color 0.3s, transform 0.2s;
}

.update-btn {
    background-color: #4caf50;
}

.cancel-btn {
    background-color: tomato;
}

.update-btn:hover,
.cancel-btn:hover {
    background-color: rgba(255, 255, 255, 0.2);
}

.update-btn:focus,
.cancel-btn:focus {
    outline: none;
}

.cancel-btn:hover {
    transform: scale(1.1);
}
.update-btn:hover {
    transform: scale(1.1);
}

.cancel-btn:active,
.update-btn:active {
    transform: translateY(1px);
}

#buttons
{
    display: flex;
    justify-content: space-evenly;
}

    
    </style>
</head>
<body>
<%
	int id=Integer.parseInt(request.getParameter("id")); 
	TodoDao dao=new TodoDao();
	Task task=dao.findTaskByid(id);%>

	<div class="container">
        <form action="edit-task" class="task-form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%=task.getId()%>" >
            <fieldset>
                <legend>Enter Task Details</legend>
                <div class="form-group">
                    <label for="task-name">Task Name:</label>
                    <input type="text" id="task-name" name="taskname" value="<%=task.getName()%>">
                </div>
                <div class="form-group">
                    <label for="task-description">Task Description:</label>
                    <textarea id="task-description" name="description"><%=task.getDescription()%></textarea>
                </div>
                <div class="form-group">
                    <label for="task-image">Task Image:</label>
                    <input type="file" name="taskimage" id="task-image"><img height="70px" width="70px" src="data:image/png;base64,<%=task.getEncodeImage()%>" alt="">
                </div>
                <div class="form-group" id="buttons">
                    <button type="submit" class="update-btn">Update Task</button>
                    <button type="reset" class="cancel-btn">Cancel Task</button>
                </div>
            </fieldset>
        </form>
    </div>
</body>
</html>
	

</body>
</html>