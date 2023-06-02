/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Hp
 */
public class PatientListTest {
    
    public PatientListTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addPatient method, of class PatientList.
     */
    @Test
    public void testAddPatient() {
        System.out.println("addPatient");
        NurseList nList = null;
        PatientList instance = new PatientList();
        instance.addPatient(nList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayPatients method, of class PatientList.
     */
    @Test
    public void testDisplayPatients() {
        System.out.println("displayPatients");
        PatientList instance = new PatientList();
        instance.displayPatients();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortPatients method, of class PatientList.
     */
    @Test
    public void testSortPatients() {
        System.out.println("sortPatients");
        PatientList instance = new PatientList();
        instance.sortPatients();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
