package com.bookstore.service.impl;

import com.bookstore.dto.request.ReqBook;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespBook;
import com.bookstore.dto.response.RespStatus;
import com.bookstore.dto.response.Response;
import com.bookstore.entity.Book;
import com.bookstore.enums.EnumAviableStatus;
import com.bookstore.exception.ExceptionConstants;
import com.bookstore.exception.LibraryException;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.repository.UserTokenRepository;
import com.bookstore.service.BookService;
import com.bookstore.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final UserTokenRepository userTokenRepository;

    private final UserRepository userRepository;

    private final Utility utility;

    @Override
    public Response<List<RespBook>> getBookList(ReqToken reqToken) {
        Response <List<RespBook>> response = new Response<>();
        try {
            utility.checkToken(reqToken);
            List<Book> bookList = bookRepository.findAllByActive(EnumAviableStatus.ACTIVE.value);
            if (bookList.isEmpty()) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            List<RespBook> respBookList = bookList.stream().map(book -> mapping(book)).collect(Collectors.toList());
            response.setT(respBookList);
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
    public Response<RespBook> getBookById(ReqBook reqBook ) {
        Response<RespBook> response = new Response<>();
        try {
            Long bookId = reqBook.getBookId();
            utility.checkToken(reqBook.getReqToken());
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAviableStatus.ACTIVE.value);
            if (book == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            RespBook respBook = mapping(book);
            response.setT(respBook);
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
    public Response addBook(ReqBook reqBook) {
        Response response = new Response<>();
        try {
            ReqToken reqToken = reqBook.getReqToken();
            utility.checkToken(reqToken);
            String name = reqBook.getName();

            if (name == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = Book.builder().
                    name(name).
                    author(reqBook.getAuthor()).
                    lang(reqBook.getLang()).
                    amount(reqBook.getAmount()).
                    year(reqBook.getYear()).
                    build();
            bookRepository.save(book);
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
    public Response updateBook(ReqBook reqBook) {
        Response response = new Response<>();
        try {
            ReqToken reqToken = reqBook.getReqToken();
            utility.checkToken(reqToken);
            String name = reqBook.getName();
            Long bookId = reqBook.getBookId();
            if (name == null || bookId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAviableStatus.ACTIVE.value);
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            book.setName(name);
            book.setAuthor(reqBook.getAuthor());
            book.setLang(reqBook.getLang());
            book.setYear(reqBook.getYear());
            book.setAmount(reqBook.getAmount());
            bookRepository.save(book);
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
    public Response deleteBook(ReqBook reqBook) {
        Response response = new Response<>();
        try {
            Long bookId = reqBook.getBookId();
            ReqToken reqToken = reqBook.getReqToken();

            utility.checkToken(reqToken);
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAviableStatus.ACTIVE.value);
            if (bookId == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            book.setActive(EnumAviableStatus.DEACTIVE.value);
            bookRepository.save(book);
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

    private RespBook mapping (Book book) {
        RespBook respBook = RespBook.builder().
                bookId(book.getId()).
                name(book.getName()).
                author(book.getAuthor()).
                lang(book.getLang()).
                year(book.getYear()).
                amount(book.getAmount()).
                build();
        return respBook;
    }


}
