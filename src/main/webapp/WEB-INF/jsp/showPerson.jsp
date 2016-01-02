<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<body>
	<form action="<c:url value="savePerson.html"/>" method="post">
		<table>
			<tr>
				<td>Full name</td>
				<td><input name="fullName" value="${fullName}" /> <input
					type="hidden" name="oldFullName" value="${fullName}" /></td>
			</tr>
			<tr>
				<td>Company</td>
				<td>${company}</td>
			</tr>
			<tr>
				<td>Country</td>
				<td>${country}</td>
			</tr>
			<tr>
				<td>Telephone</td>
				<td><input name="phone" value="${phone}" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><input name="description" value="${description}" /></td>
			</tr>
		</table>
		<input type="submit" value="Save" />
	</form>
</body>

</html>
