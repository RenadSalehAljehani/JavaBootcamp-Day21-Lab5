package com.example.lab5q1.Controller;

import com.example.lab5q1.Model.Student;
import com.example.lab5q1.ResponseAPI.ResponseAPI;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/studentsSystem")
public class StudentController {

    // 1. Create a list of Students
    ArrayList<Student> students = new ArrayList<>();

    // 2. Create a new student
    @PostMapping("/createStudent")
    public ResponseAPI createStudent(@RequestBody Student student) {
        students.add(student);
        return new ResponseAPI("New Student Created.");
    }

    // 3. Display all students
    @GetMapping("/getStudents")
    public ArrayList<Student> getStudents() {
        return students;
    }

    // 4. Update a student
    @PutMapping("/updateStudent/{index}")
    public ResponseAPI updateStudent(@PathVariable int index, @RequestBody Student student) {
        students.set(index, student);
        return new ResponseAPI("Student Updated.");
    }

    // 5. Delete a student
    @DeleteMapping("/deleteStudent/{index}")
    public ResponseAPI deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ResponseAPI("Student Deleted.");
    }

    // 6. Based on GPA, classify students into honors categories
    @GetMapping("/classifyStudents/{index}")
    public ResponseAPI classifyStudents(@PathVariable int index) {
        if(!students.isEmpty()) {
            if (students.get(index).getGPA() >= 4.5 && students.get(index).getGPA() <= 5.0) {
                return new ResponseAPI("Excellent");
            } else if (students.get(index).getGPA() >= 3.75 && students.get(index).getGPA() <= 4.49) {
                return new ResponseAPI("Very Good");
            } else if (students.get(index).getGPA() >= 2.75 && students.get(index).getGPA() <= 3.74) {
                return new ResponseAPI("Good");
            } else if (students.get(index).getGPA() >= 2.0 && students.get(index).getGPA() <= 2.74) {
                return new ResponseAPI("Pass");
            } else if (students.get(index).getGPA() < 2.0) {
                return new ResponseAPI("Fail");
            }
        }
        return new ResponseAPI("There Are No Students In the System.");
    }


    // 7. Display a group of students whose GPA is greater than the average GPA
    @GetMapping("/greaterGpaStudents")
    public ArrayList<Student> greaterGpaStudents() {
        double average = 0;
        double total = 0;
        ArrayList<Student> smartStudents = new ArrayList<>();

        for (Student student : students) {
            total = total + student.getGPA();
        }

        average = total / students.size();

        for (Student student : students) {
            if (student.getGPA() > average) {
                smartStudents.add(student);
            }
        }
        return smartStudents;
    }
}