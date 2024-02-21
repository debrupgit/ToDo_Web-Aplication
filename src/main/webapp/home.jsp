<!DOCTYPE html>
<%@page import="org.jsp.todo_app.dto.Task"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-image: linear-gradient(19deg, #21D4FD 0%, #B721FF 100%);
  min-height: 100vh;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.container {
  max-width: 900px;
  margin: 20px auto;
  padding: 20px;
  background-color:  #333;
  border-radius: 50px;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

table {
  width: 100%;
  /* border-collapse: collapse; */
}

table th, table td {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #ccc;
}

table th {
  background-color: #333;
}

table tr:nth-child(even) {
  background-color: #f9f9f9;
}

.btn-container {
  text-align: center;
  margin-top: 20px;
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #0ff;
  color: black;
  cursor: pointer;
  transition: transform 0.3s ease;
  font-size: large;
}
#com
{
	background-color: green;
}

#del
{
	background-color: tomato;
}

#edit
{
	background-color: blue;
}

.btn:hover {
  transform: scale(1.1);
}
</style>
</head>
<body>
    <h1>Home Page</h1>
  <div class="container">
   
    <%
	List<Task>tasks=(List<Task>)request.getAttribute("tasks");
	%>
	
	<%
	if(!tasks.isEmpty()){
	%>
    <table>
      <thead>
        <tr>
          <th>Task Image</th>
          <th>Task Name</th>
          <th>Task Description</th>
          <th>Created Time</th>
          <th>Status</th>
          <th>Delete</th>
          <th>Edit</th>
        </tr>
      </thead>
      <tbody>
        <!-- Table rows will be dynamically generated here -->
        <%
		for(Task task:tasks){
		%>
        <tr>
            <th><img height="70px" width="70px" alt="" src="data:image/png;base64,<%=task.getEncodeImage() %>"/></th>
            <th><%=task.getName()%></th>
            <th><%=task.getDescription()%></th>
            <th><%=task.getAddedTime()%></th>
            <th><%if(task.isStatus()) {%>
            Completed<%} 
            else{%><a href="complete-task?id=<%=task.getId()%>"><button class="btn" id="com">Complete</button></a><%} %></th>
            <th><a href="delete?id=<%=task.getId()%>"><button class="btn" id="del">Delete</button></a></th>
            <th><a href="edit-task.jsp?id=<%=task.getId()%>"><button class="btn" id="edit">Edit</button></a></th>
        </tr>
		<%
		}
		%>
        <!-- Add more rows as needed -->
      </tbody>
    </table>
    <%
	}
    %> 
    <div class="btn-container">
     <a href="add-task.html"> <button class="btn">Add Task</button></a>
    <a href="logout"><button class="btn">Logout</button></a>
    </div>
  </div>
</body>
</html>
