package org.example.persistence;

import org.example.persistence.daos.AttendanceLogDao;
import org.example.persistence.daos.LessonDao;
import org.example.persistence.daos.StudentDao;
import org.example.persistence.daos.TeamDao;

public class DaoFactory {
    private final static String URL = "jdbc:mysql://localhost:3306/journal";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "mysql42";

    public static StudentDao createStudentDao() {
        return new StudentDao(URL, USER_NAME, PASSWORD);
    }

    public static LessonDao createLessonDao() {
        return new LessonDao(URL, USER_NAME, PASSWORD);
    }

    public static AttendanceLogDao createAttendanceLogDao() {
        return new AttendanceLogDao(URL, USER_NAME, PASSWORD);
    }

    public static TeamDao createTeamDao() {
        return new TeamDao(URL, USER_NAME, PASSWORD);
    }

}
