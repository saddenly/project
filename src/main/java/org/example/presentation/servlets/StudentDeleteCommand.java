package org.example.presentation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class StudentDeleteCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String id = request.getParameter("id");

            if (id != null) {
                int studentId = Integer.parseInt(id);

                getService().deleteStudent(studentId);
            } else {
                throw new ServletException("Student id is missing.");
            }
        } catch (SQLException e) {
            throw new ServletException("Error deleting student.", e);
        }
    }
}
