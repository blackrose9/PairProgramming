package dao;

import models.Students;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.*;

public class Sql2oStudentsDaoTest {
    private static Connection conn;
    private static Sql2oStudentsDao studentsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/paired_test";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        studentsDao = new Sql2oStudentsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
//        studentsDao.clearAll();
//        System.out.println("Nice clean slate coming up...");
    }

    @Test
    public void addingStudentSetsAnId() throws Exception {
        Students testStudent = setUpStudent();
        int ogStudentId = testStudent.getId();
        studentsDao.addStudent(testStudent);
        assertNotEquals(ogStudentId, testStudent.getId());
    }

    @Test
    public void getAllReturnsAllStudentsFromTheDb() throws Exception{
        Students student1 = setUpStudent();
        Students student2 = setUpStudent();
        assertEquals(2, studentsDao.getAllStudents().size());
    }

    @Test
    public void noStudentsReturnedIfListIsEmpty() throws Exception {
        assertEquals(0, studentsDao.getAllStudents().size());
    }

    @Test
    public void deleteCorrectStudentById() throws Exception {
        Students student = setUpStudent();
        studentsDao.addStudent(student);
//        studentsDao.deleteById(student.getId());
        assertEquals(0, studentsDao.getAllStudents().size());
    }

    @Test
    public void clearAllStudentsFromLIst() throws Exception {
        Students student1 = setUpStudent();
        Students student2 = setUpStudent();
//        studentsDao.clearAll();
        assertEquals(0, studentsDao.getAllStudents().size());
    }

    Students setUpStudent(){
        return new Students("Brooklyn");
    }
}