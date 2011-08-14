<%@ page import="java.io.File" %>
<%@ page import="org.cccs.serverbrowser.FileManager"  %>
<%--
  User: Craig Cook
  Date: Nov 12, 2008
  Time: 14:06:23 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Server Browser</title></head>
  <body>

  <%
    String strDir = request.getParameter("txtDir");
    strDir = (strDir == null) ? "" : strDir;

    String strDeep = request.getParameter("txtDeep");
    strDeep = (strDeep == null) ? "0" : strDeep;
  %>

  <form action="index.jsp">

      <table>
          <tr>
              <td>
                  Directory Location: <input type="text" width="150px" name="txtDir" value="<%=strDir%>"/>
                  &nbsp;&nbsp;Levels deep: <input type="text" width="50px" name="txtDeep" value="<%=strDeep%>"/>
              </td>
              <td>
                  <input type="submit" value="Search"/>
              </td>
          </tr>
      </table>
  </form>

  <%
    if (strDir != null && strDir.length() != 0) {
        out.print("Browsing directory: " + strDir + "<br>");

        int intDeep = Integer.parseInt(strDeep);

        FileManager file = new FileManager(intDeep);
        file.check(new File(strDir));
        out.print(file.getFiles());
    }
  %>

  </body>
</html>