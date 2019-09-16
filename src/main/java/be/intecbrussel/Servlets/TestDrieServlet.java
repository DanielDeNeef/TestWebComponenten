package be.intecbrussel.Servlets;


import javax.faces.component.html.HtmlOutputText;
import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/testdrie")
public class TestDrieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        Object getName = session.getAttribute("name");
        Object getMessage = session.getAttribute("message");
        String username = "";
        String message = "";

        if (getName != null) {
            username = (String) getName;
        }

        if (getMessage != null) {
            message = (String) getMessage;
            session.setAttribute("message", message);
        }

// Strings vergelijken doe je met equals
        if (username != "") {
            session.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "";
        String message = "";

        HttpSession session = req.getSession();
        Object gotName = session.getAttribute("username");
        Object gotMessage = session.getAttribute("message");

        if (gotName != null) {
            name = (String) gotName;
        } else {
            name = req.getParameter("name");
        }

        session.setAttribute("name", name);

        if (gotMessage != null) {
            message = (String) gotMessage;
        } else if (message == "") {
            message = message = ",gelieve een geldige naam in te voeren";
        }

        session.setAttribute("message", message);
// beter is resp.sendRedirect. Wat je hier in feite doet is: je roept de methode doGet aan, manueel, wat overeen komt met een forward.
        //een redirect zorgt voor een nieuwe 'get' request bij de client, geen constante 'post' messages bij het refreshen dus
        doGet(req, resp);
    }
}
