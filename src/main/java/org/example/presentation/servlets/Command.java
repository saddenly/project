package org.example.presentation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.application.ServiceFactory;
import org.example.application.service.JournalService;

import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    default JournalService getService() {
        return ServiceFactory.createJournalService();
    }
}
