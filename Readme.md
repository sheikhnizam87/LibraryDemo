Development
Requirements
JDK 8


Release Notes
0.0.1

Current version has below features.

Adding books in the library with number of copies.
Search book by its id i.e. ISBN.
Update the book details by its id i.e ISBN, in the library.
Delete the book record by its id i.e ISBN.

Search book by its name.
Search book by its Author.
Search book by multiple tags added in the book.

Import book details into library in a bulk.

API documentation by implementing Swagger2. 

Release procedure

Download or clone the project from git.
Extract it in your local machine and open the project in any IDE.
Open LibraryApplication.java file and run its main method.
Open http://localhost:8080/swagger-ui.html in browser, to see available api's.


To test import csv file feature
1. open swagger UI or postman and use below payload.

{"encodedFile":"{\"formData\":\"data:text/csv;base64,MzU0NSxEYSB2aW5jaSBjb2RlLERhbiBicm93biwiU3VzcGVuc2UsVGhyaWxsZXIiCjk4NzY1LE9yZGVyIG9mIHBoZW9uaXgsSksgUm93bGluZyxGYW50YXN5Cjc2MzQ2OSxUaGUgR3JlYXQgR2F0c2J5LEYuIEZpdHhHZXJhbGQsIk5vdmVsLEZpY3Rpb25hbCIK\"}"}

This payload has a csv file encoded with Base64 and converted into String.
It contains 3 records.

3545,Da vinci code,Dan brown,"Suspense,Thriller"
98765,Order of pheonix,JK Rowling,Fantasy
763469,The Great Gatsby,F. FitxGerald,"Novel,Fictional" 



