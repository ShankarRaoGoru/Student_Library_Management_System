package com.example.Student_Library_Management_System.Controllers;



import com.example.Student_Library_Management_System.DTOs.StudentMobUpdateDto;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){

        return studentService.createStudent(student);
    }
    @GetMapping("/get-student")
    public String getStudent(@RequestParam String email){
        return studentService.getStudent(email);
    }
    @PutMapping("update-mob")
    public String update(@RequestBody StudentMobUpdateDto studentMobUpdateDto){
        return studentService.update(studentMobUpdateDto);

    }



}