insert into tag(id,name)
values(1000,'Fictional');
insert into tag(id,name)
values(1001,'Fantasy');
insert into tag(id,name)
values(1002,'Horror');
insert into tag(id,name)
values(1003,'Thriller');
insert into tag(id,name)
values(1004,'Suspense');


insert into book(isbn,name,author,copies)
values('34577','The Little Prince','Antoine de Saint',2);
insert into book(isbn,name,author,copies)
values('7545454','The Little Engine That Could','Watty Piper',1);
insert into book(isbn,name,author,copies)
values('243566','Stuart Little','E.B. White',5);
insert into book(isbn,name,author,copies)
values('576875','Life of Pi','Yann Martel',10);


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