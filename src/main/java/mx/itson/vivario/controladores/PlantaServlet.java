package mx.itson.vivario.controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.itson.vivario.entidades.Planta;
import mx.itson.vivario.persistencia.PlantaDAO;

@WebServlet(name = "PlantaServlet", urlPatterns = {"/plantas"})
public class PlantaServlet extends HttpServlet {

    private final PlantaDAO dao = new PlantaDAO();

    /** GET: consulta las plantas y las manda al JSP. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("plantas", dao.obtenerTodas());
        } catch (Exception ex) {
            request.setAttribute("error", "No se pudo consultar la base de datos: " + ex.getMessage());
        }
        request.getRequestDispatcher("/plantas.jsp").forward(request, response);
    }

    /** POST: recibe el formulario y guarda en la base de datos. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Planta p = new Planta();
            p.setNombreComun(request.getParameter("nombreComun").trim());
            p.setNombreCient(request.getParameter("nombreCient").trim());
            p.setTipo(request.getParameter("tipo"));
            p.setRiegoDias(Integer.parseInt(request.getParameter("riegoDias")));
            p.setLuz(request.getParameter("luz"));
            p.setPrecio(Double.parseDouble(request.getParameter("precio")));
            dao.guardar(p);
            response.sendRedirect(request.getContextPath() + "/plantas?ok=1");
        } catch (Exception ex) {
            request.setAttribute("error", "No se pudo guardar: " + ex.getMessage());
            doGet(request, response);
        }
    }
}
