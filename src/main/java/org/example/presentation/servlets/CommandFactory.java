package org.example.presentation.servlets;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Map<String, Command>> commands = new HashMap<>();

    static {
        Map<String, Command> journalCommands = new HashMap<>();
        journalCommands.put("GET", new JournalGetCommand());
        commands.put("/api/v1/journal", journalCommands);

        Map<String, Command> attendanceLogCommands = new HashMap<>();
        attendanceLogCommands.put("OPTIONS", new AttendanceLogOptionsCommand());
        attendanceLogCommands.put("POST", new AttendanceLogPostCommand());
        commands.put("/api/v1/attendancelog", attendanceLogCommands);

        Map<String, Command> lessonCommands = new HashMap<>();
        lessonCommands.put("OPTIONS", new LessonOptionsCommand());
        lessonCommands.put("POST", new LessonPostCommand());
        commands.put("/api/v1/lesson", lessonCommands);
    }

    public static Command getCommand(String path, String method) {
        Map<String, Command> methodMap = commands.get(path);
        if (methodMap != null) {
            return methodMap.get(method);
        }
        return null;
    }
}
