insert into tag(id,name)
values(1000,'fictional');
insert into tag(id,name)
values(1001,'fantasy');
insert into tag(id,name)
values(1002,'horror');
insert into tag(id,name)
values(1003,'thriller');
insert into tag(id,name)
values(1004,'suspense');


insert into book(isbn,name,author,copies)
values('34577','the little prince','antoine de saint',2);
insert into book(isbn,name,author,copies)
values('7545454','the little engine that could','watty piper',1);
insert into book(isbn,name,author,copies)
values('243566','stuart little','e.b. white',5);
insert into book(isbn,name,author,copies)
values('576875','life of pi','yann martel',10);


insert into book_tag(isbn,tag_id)
values('34577',1000);
insert into book_tag(isbn,tag_id)
values('34577',1001);
insert into book_tag(isbn,tag_id)
values('7545454',1002);
insert into book_tag(isbn,tag_id)
values('7545454',1000);
insert into book_tag(isbn,tag_id)
values('243566',1002);
insert into book_tag(isbn,tag_id)
values('243566',1003);
insert into book_tag(isbn,tag_id)
values('576875',1004);