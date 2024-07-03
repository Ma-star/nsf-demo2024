package com.example.webservice.book;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-05T15:37:10.228+08:00
 * Generated source version: 3.4.2
 *
 */
@WebService(targetNamespace = "http://www.cleverbuilder.com/BookService/", name = "BookService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BookService {

    @WebMethod(operationName = "GetBook", action = "http://www.cleverbuilder.com/BookService/GetBook")
    @WebResult(name = "GetBookResponse", targetNamespace = "http://www.cleverbuilder.com/BookService/", partName = "parameters")
    public GetBookResponse getBook(

        @WebParam(partName = "parameters", name = "GetBook", targetNamespace = "http://www.cleverbuilder.com/BookService/")
        GetBook parameters
    );

    @WebMethod(operationName = "AddBook", action = "http://www.cleverbuilder.com/BookService/AddBook")
    @WebResult(name = "AddBookResponse", targetNamespace = "http://www.cleverbuilder.com/BookService/", partName = "parameters")
    public AddBookResponse addBook(

        @WebParam(partName = "parameters", name = "AddBook", targetNamespace = "http://www.cleverbuilder.com/BookService/")
        AddBook parameters
    );

    @WebMethod(operationName = "GetAllBooks", action = "http://www.cleverbuilder.com/BookService/GetAllBooks")
    @WebResult(name = "GetAllBooksResponse", targetNamespace = "http://www.cleverbuilder.com/BookService/", partName = "parameters")
    public GetAllBooksResponse getAllBooks(

        @WebParam(partName = "parameters", name = "GetAllBooks", targetNamespace = "http://www.cleverbuilder.com/BookService/")
        GetAllBooks parameters
    );
}