package org.example.presentation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class LessonDeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String lessonIdParam = request.getParameter("lessonId");

            if (lessonIdParam != null) {
                int lessonId = Integer.parseInt(lessonIdParam);

                getService().deleteLesson(lessonId);
            } else {
                throw new ServletException("Lesson id is missing");
            }
        } catch (SQLException e) {
            throw new ServletException("Error deleting lesson", e);
        }
    }
}
