<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<form name="login" action ="<c:url value="/loginHanler.html"/>">
<input name="userName" type="text"/>
<input name="password" type="text"/>
<input type="submit" value="Login">
</form>
</body>
</html>
