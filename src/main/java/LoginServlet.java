import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Ken Huang on 15/7/31.
 */

@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = {"/user"})
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGetPost(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGetPost(request, response);
    }

    private void doGetPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        User userinfo = new User();
        userinfo.setName(request.getParameter("name"));

        response.setContentType("text/html;charset=UTF-8");
        if (request.getMethod() == "get") {
            response.getOutputStream().println("user name="
                    + request.getParameter("name")
                    + "<br/> sex=" + request.getParameter("sex")
                    + "<br/> select=" + request.getParameter("select")
                    + "<br/> textarea=" + request.getParameter("textarea")
                    + "<br/> pass=" + request.getParameter("pass"));
            if (request.getParameterValues("checkbox1") != null)
                response.getOutputStream().println(" checkbox=" + Arrays.asList(request.getParameterValues("checkbox1")));
        } else {
            JSONObject jsonData = new JSONObject();
            jsonData.put("name", userinfo.getName());
            response.getOutputStream().println(jsonData.toString());
        }
    }
}
