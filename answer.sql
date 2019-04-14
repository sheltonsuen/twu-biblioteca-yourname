-- Who checked out the book 'The Hobbit’?
SELECT member.*
FROM book,
     checkout_item,
     member
where book.id = checkout_item.book_id
  and member.id = checkout_item.member_id
  and book.title = 'The Hobbit';

-- How many people have not checked out anything?
select count(*)
from member
where member.id not in (select member_id from checkout_item);

-- What books and movies aren't checked out?
select *
from book
where book.id not in (select book_id from checkout_item where checkout_item.book_id not null)
union
select *
from movie
where movie.id not in (select movie_id from checkout_item where checkout_item.movie_id not null);

--Add the book 'The Pragmatic Programmer',
insert into book
values (null, 'The Pragmatic Programmer');

-- and add yourself as a member.
insert into member
values (null, 'Xiaocong Sun');

-- Check out 'The Pragmatic Programmer'.
insert into checkout_item
select member.id, book.id, null
from book,
     member
where book.title = 'The Pragmatic Programmer'
  and member.name = 'Xiaocong Sun';

-- Use your query from question 1 to verify that you have checked it out. Also, provide the SQL used to update the database.
SELECT member.*
FROM book,
     checkout_item,
     member
where book.id = checkout_item.book_id
  and member.id = checkout_item.member_id
  and book.title = 'The Pragmatic Programmer';


--      Who has checked out more that 1 item?  // not so clear understand this question
--      Tip: Research the GROUP BY syntax.

select *
from member,
     checkout_item
where member.id = checkout_item.member_id
  and book_id not null
  and movie_id not null
