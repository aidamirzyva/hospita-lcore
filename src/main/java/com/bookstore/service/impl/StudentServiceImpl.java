package com.bookstore.service.impl;


import com.bookstore.dto.request.ReqStudent;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespStatus;
import com.bookstore.dto.response.RespStudent;
import com.bookstore.dto.response.Response;
import com.bookstore.entity.Student;
import com.bookstore.enums.EnumAviableStatus;
import com.bookstore.exception.ExceptionConstants;
import com.bookstore.exception.LibraryException;
import com.bookstore.repository.StudentRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.StudentService;
import com.bookstore.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Utility utility;
    private final UserRepository userRepository;



    @Override
    public Response<RespStudent> getStudentById(ReqStudent reqStudent) {
        Response response = new Response<>();
        try {
            Long studentId= reqStudent.getStudentId();
            utility.checkToken(reqStudent.getReqToken());
            if (studentId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Student student = studentRepository.findStudentByIdAndActive(studentId, EnumAviableStatus.ACTIVE.value);
            if (student == null) {
                throw new LibraryException(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found");
            }
            RespStudent respStudent = mapping(student);
            response.setT(respStudent);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<List<RespStudent>> getStudentList(ReqToken reqToken) {
        Response<List<RespStudent>> response = new Response<>();
        try {
            utility.checkToken(reqToken);
            List<Student>studentList = studentRepository.findAllByActive(EnumAviableStatus.ACTIVE.value);
            if (studentList.isEmpty()) {
                throw new LibraryException(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found");
            }
            List<RespStudent> respStudentList = studentList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respStudentList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;

    }


    @Override
    public Response addStudent(ReqStudent reqStudent) {
        Response response = new Response();

        try {
            String name = reqStudent.getName();
            String surname = reqStudent.getSurname();
            ReqToken reqToken = reqStudent.getReqToken();
            utility.checkToken(reqToken);
            if (name == null || surname == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Student student = Student.builder().
                    name(name).
                    surname(surname).
                    dob(reqStudent.getDob()).
                    phone(reqStudent.getPhone()).
                    email(reqStudent.getEmail()).

                    build();
            //repository save metodu var ozu insert edir. saveicine patient gonderib ozu arxada save edir
            studentRepository.save(student);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateStudent(ReqStudent reqStudent) {
        Response response = new Response();
        try {
            String name = reqStudent.getName();
            String surname = reqStudent.getSurname();
            Long studentId = reqStudent.getStudentId();
            ReqToken reqToken = reqStudent.getReqToken();
            utility.checkToken(reqToken);
            if (name == null || surname == null || studentId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Student student = studentRepository.findStudentByIdAndActive(studentId, EnumAviableStatus.ACTIVE.value);
            if (student == null) {
                throw new LibraryException(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found");

            }
            student.setName(name);
            student.setSurname(surname);
            student.setDob(reqStudent.getDob());
            student.setPhone(reqStudent.getPhone());
            student.setEmail(reqStudent.getEmail());
            studentRepository.save(student);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteStudent(ReqStudent reqStudent) {
        Response response = new Response();
        try {
            Long studentId= reqStudent.getStudentId();
            ReqToken reqToken = reqStudent.getReqToken();
            utility.checkToken(reqToken);
            if (studentId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            } //patientId gore obyekt gotururuk sonra onun null-gunu yoxlayiriq
            Student student = studentRepository.findStudentByIdAndActive(studentId, EnumAviableStatus.ACTIVE.value);
            if (student == null) {
                throw new LibraryException(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found");

            }
            student.setActive(EnumAviableStatus.DEACTIVE.value);
            studentRepository.save(student);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }


        return response;
    }
    private RespStudent mapping (Student student) {
        RespStudent respStudent = RespStudent.builder()
                .studentId(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .phone(student.getPhone())
                .dob(student.getDob())
                .build();
        return respStudent;
    }

}
