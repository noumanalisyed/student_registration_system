<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
  <title>Course Registration App</title>

  <link type="text/css" rel="stylesheet" href="view/css/style.css">
</head>

<body>

<div id="wrapper">
  <div id="header">
    <h2>Course Registration App</h2>
    <c:set var="context" value="${pageContext.request.contextPath}" />
  </div>
</div>

<div id="container">

  <div id="content">

    <!-- put new button: Add Student -->

    <input type="button" value="Add Course"
           onclick="window.location.href='view/add-course-form.jsp'; return false;"
           class="add-student-button"
    />
    <input type="button" value="Add Student"
           onclick="window.location.href='view/add-student-form.jsp'; return false;"
           class="add-student-button"
    />
    <input type="button" value="Add Registration"
           onclick="window.location.href='view/add-registration-form.jsp'; return false;"
           class="add-student-button"
    />

  </div>

</div>
</body>


</html>

