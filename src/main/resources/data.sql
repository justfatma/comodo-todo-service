insert into todo_group (id, name, todo_user_id) values (10000, 'Home', 1000);
insert into todo_group (id, name, todo_user_id) values (10001, 'Kids', 1000);
insert into todo_group (id, name, todo_user_id) values (10002, 'Work', 1001);


insert into todo (id, todo_group_id, explanation, priority, due_date, status) values (103, 10000, 'Hotel Booking', 'HIGH','2022-05-18', 'ACTIVE');
insert into todo (id, todo_group_id, explanation, priority, due_date, status) values (104, 10000, 'House cleaning', 'LOW','2022-05-13', 'ACTIVE');
insert into todo (id, todo_group_id, explanation, priority, due_date, status) values (100, 10000, 'Grocery shopping', 'MEDIUM','2022-05-21', 'ACTIVE');
insert into todo (id, todo_group_id, explanation, priority, due_date, status) values (101, 10000, 'Cinema Ticket', 'LOW','2022-04-21', 'DONE');
insert into todo (id, todo_group_id, explanation, priority, due_date, status) values (102, 10002, 'Annual Reports', 'HIGH','2022-04-25', 'DONE');



