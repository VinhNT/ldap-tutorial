<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="<c:url value="css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>
	<table>
		<thead>
			<td>Full name</td>
			<td>Company</td>
			<td>Country</td>
			<td>Telephone</td>
			<td>Description</td>
			<td>Action</td>
		</thead>
		<tbody>
			<c:forEach items="${allPerson}" var="person">
				<tr>
					<td>${person.fullName}</td>
					<td>${person.company}</td>
					<td>${person.country}</td>
					<td>${person.phone}</td>
					<td>${person.description}</td>
					<td><a href="<c:url value="editPerson.html?fullName=${person.fullName}" />">Edit</a> <a href="<c:url value="removePerson.html?fullName=${person.fullName}" />">Remove</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value="addPerson.html"/>">Add Person</a>
</body>
</html>
