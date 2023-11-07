package com.bookstore.service;

import com.bookstore.dto.request.ReqStudent;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespStudent;
import com.bookstore.dto.response.Response;

import java.util.List;

public interface StudentService {

    Response<List<RespStudent>> getStudentList(ReqToken reqtoken);
    Response<RespStudent>getStudentById(ReqStudent reqStudent);

    Response addStudent(ReqStudent reqStudent);


    Response updateStudent(ReqStudent reqStudent);
    Response deleteStudent(ReqStudent reqStudent);


}
