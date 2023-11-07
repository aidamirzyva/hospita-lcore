package com.bookstore.controller;

import com.bookstore.dto.request.ReqStudent;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespStudent;
import com.bookstore.dto.response.Response;
import com.bookstore.entity.Student;
import com.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    @PostMapping(value = "/GetStudentList")
    public Response<List<RespStudent>> getStudentList(@RequestBody ReqToken reqToken){


        //localhost:8082/bookstore/GetStudentList
        return studentService.getStudentList(reqToken);

    }
    @PostMapping("/GetStudentById")
    public Response<RespStudent> getStudentById(@RequestBody ReqStudent reqStudent){
        return studentService.getStudentById(reqStudent);
    }

    @PostMapping("/AddStudent")
    public Response addStudent(@RequestBody ReqStudent reqStudent) {
        return studentService.addStudent(reqStudent);
    }
    @PostMapping("/UpdateStudent")
    public Response updateStudent(@RequestBody ReqStudent reqStudent){
        return studentService.updateStudent(reqStudent);
    }
    @PostMapping("/DeleteStudent")
    public Response deleteStudent(@RequestBody ReqStudent reqStudent){
        return studentService.deleteStudent(reqStudent);
    }

}
