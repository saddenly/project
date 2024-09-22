package org.example.persistence;

import org.example.persistence.daos.AttendanceLogDao;
import org.example.persistence.daos.LessonDao;
import org.example.persistence.daos.StudentDao;
import org.example.persistence.daos.TeamDao;

public class DaoFactory {
    private final static String URL = "<SPECIFY_URL>";
    private final static String USER_NAME = "<SPECIFY_USER_NAME>";
    private final static String PASSWORD = "SPECIFY_PASSWORD";

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
