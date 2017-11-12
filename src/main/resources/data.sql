-- CREATE TABLES FOR STORING USER CREDENTIALS
--drop table if exists users
--drop table if exists authorities
--CREATE TABLE users (USERNAME VARCHAR(50), PASSWORD VARCHAR(500), ENABLED boolean, PRIMARY KEY (USERNAME))
--CREATE TABLE authorities (USERNAME VARCHAR(50), AUTHORITY VARCHAR(20))

-- INSERT SAMPLE PET OWNER
--insert into users(USERNAME, PASSWORD, ENABLED) values('jack.jonson@email.com', 'password', TRUE)
--insert into authorities(USERNAME, AUTHORITY) values('jack.jonson@email.com', 'ROLE_USER')

-- INSERT SAMPLE VET
--insert into users(USERNAME, PASSWORD, ENABLED) values('seth.barrett@email.com', 'password', TRUE)
--insert into authorities(USERNAME, AUTHORITY) values('jack.jonson@email.com', 'ROLE_USER')

-- ADDRESS

insert into address(id, country, city, street, zip) select 1, 'Hungary', 'Budapest', 'Németvölgyi út 20', '1126' from dual where not exists(select id from address where id = 1)
insert into address(id, country, city, street, zip) select 2, 'Hungary', 'Budapest', 'II. János Pál pápa tér 9', '1081' from dual where not exists(select id from address where id = 2)
insert into address(id, country, city, street, zip) select 3, 'Hungary', 'Budapest', 'Fecske utca 1', '1084' from dual where not exists(select id from address where id = 3)
insert into address(id, country, city, street, zip) select 4, 'Hungary', 'Budapest', 'Molnár utca 3', '1056' from dual where not exists(select id from address where id = 4)
insert into address(id, country, city, street, zip) select 5, 'Hungary', 'Budapest', 'Báthory utca 3', '1054' from dual where not exists(select id from address where id = 5)

insert into address(id, country, city, street, zip) select 6, 'Hungary', 'Budapest', 'Böszörményi út 3b', '1126' from dual where not exists(select id from address where id = 6)
insert into address(id, country, city, street, zip) select 7, 'Hungary', 'Budapest', 'Páva utca 9', '1094' from dual where not exists(select id from address where id = 7)
insert into address(id, country, city, street, zip) select 8, 'Hungary', 'Budapest', 'Marek József utca 11', '1078' from dual where not exists(select id from address where id = 8)
insert into address(id, country, city, street, zip) select 9, 'Hungary', 'Budapest', 'Lehel utca 43', '1135' from dual where not exists(select id from address where id = 9)
insert into address(id, country, city, street, zip) select 10, 'Hungary', 'Budapest', 'Tartsay Vilmos u. 19', '1126' from dual where not exists(select id from address where id = 10)
insert into address(id, country, city, street, zip) select 11, 'Hungary', 'Budapest', 'Chinoin u. 4', '1045' from dual where not exists(select id from address where id = 11)
insert into address(id, country, city, street, zip) select 12, 'Hungary', 'Miskolc', 'Lévay József utca 1', '3529' from dual where not exists(select id from address where id = 12)

-- PETOWNER

insert into pet_owner(id, first_name, last_name, email, phone, address_id) select 1,'Jack', 'Jonson', 'jack.jonson@email.com', '06301234567', 1 from dual where not exists(select id from pet_owner where id = 1)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) select 2,'Rose', 'Martin', 'rose.martin11@email.com', '7772028', 2 from dual where not exists(select id from pet_owner where id = 2)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) select 3,'George', 'Bennett', 'george.bennett21@email.com', '06206008590', 3 from dual where not exists(select id from pet_owner where id = 3)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) select 4,'Mátyás', 'Gábor', 'matyasgabor123@email.com', '0670601378', 4 from dual where not exists(select id from pet_owner where id = 4)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) select 5,'Csilla', 'Kovács', 'kovacscsilla1@email.com', '06302536472', 5 from dual where not exists(select id from pet_owner where id = 5)

-- PET

insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 1, 1, 'Mr Snuggles', 3, '2005-05-07', 1 from dual where not exists(select id from pet where id = 1)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 2, 1, 'Bruno', 4, '2007-08-02', 1 from dual where not exists(select id from pet where id = 2)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 3, 0, 'Blanket', 10, '2004-10-04', 1 from dual where not exists(select id from pet where id = 3)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 4, 2, 'Cold', 0.5, '2015-01-03', 2 from dual where not exists(select id from pet where id = 4)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 5, 0, 'Picasso', 2, '2017-05-07', 2 from dual where not exists(select id from pet where id = 5)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 6, 4, 'Rigby', 1, '2005-05-07', 2 from dual where not exists(select id from pet where id = 6)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 7, 3, 'Danger', 0.4, '2015-11-01', 3 from dual where not exists(select id from pet where id = 7)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 8, 0, 'Bert', 7, '2012-01-10', 3 from dual where not exists(select id from pet where id = 8)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 9, 2, 'Mint', 3, '2000-03-05', 3 from dual where not exists(select id from pet where id = 9)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 10, 3, 'Sandy', 2, '2014-06-06', 4 from dual where not exists(select id from pet where id = 10)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 11, 4, 'Tank', 3, '2016-01-07', 4 from dual where not exists(select id from pet where id = 11)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 12, 4, 'Roseanne', 1, '2014-02-02', 5 from dual where not exists(select id from pet where id = 12)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 13, 1, 'Ernest', 9, '2014-08-19', 5 from dual where not exists(select id from pet where id = 13)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 14, 3, 'Rotor', 1, '2016-02-10', 5 from dual where not exists(select id from pet where id = 14)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) select 15, 0, 'Sassy', 1, '2014-10-02', 5 from dual where not exists(select id from pet where id = 15)

-- VET

insert into vet(VET_ID, first_name, last_name, email, phone, address_id) select 1, 'Victor', 'White', 'victor.white12@email.com', '06205463728', 1 from dual where not exists(select VET_ID from vet where VET_ID = 1)
insert into specialities(VET_ID, pet_type_id) select 1, 0 from dual where not exists(select * from specialities where VET_ID = 1 and pet_type_id = 0)
insert into specialities(VET_ID, pet_type_id) select 1, 1 from dual where not exists(select * from specialities where VET_ID = 1 and pet_type_id = 1)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) select 2, 'Tracy', 'Reed', 'tracy.reed@email.com', '06305463732', 2 from dual where not exists(select VET_ID from vet where VET_ID = 2)
insert into specialities(VET_ID, pet_type_id) select 2, 0 from dual where not exists(select * from specialities where VET_ID = 2 and pet_type_id = 0)
insert into specialities(VET_ID, pet_type_id) select 2, 1 from dual where not exists(select * from specialities where VET_ID = 2 and pet_type_id = 1)
insert into specialities(VET_ID, pet_type_id) select 2, 2 from dual where not exists(select * from specialities where VET_ID = 2 and pet_type_id = 2)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) select 3, 'Bill', 'Orbien', 'billorbien@email.com', '06701234567',3 from dual where not exists(select VET_ID from vet where VET_ID = 3)
insert into specialities(VET_ID, pet_type_id) select 3, 2 from dual where not exists(select * from specialities where VET_ID = 3 and pet_type_id = 2)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) select 4, 'Ruben', 'Stone', 'ruben.stone33@email.com', '06204352601', 4 from dual where not exists(select VET_ID from vet where VET_ID = 4)
insert into specialities(VET_ID, pet_type_id) select 4, 3 from dual where not exists(select * from specialities where VET_ID = 4 and pet_type_id = 3)
insert into specialities(VET_ID, pet_type_id) select 4, 4 from dual where not exists(select * from specialities where VET_ID = 4 and pet_type_id = 4)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) select 5, 'Seth', 'Barrett', 'seth.barrett@email.com', '2345677',5 from dual where not exists(select VET_ID from vet where VET_ID = 5)
insert into specialities(VET_ID, pet_type_id) select 5, 4 from dual where not exists(select * from specialities where VET_ID = 5 and pet_type_id = 4)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) select 6, 'Cindy', 'Jackson', 'cindy.jackson123@email.com', '06304567890',6 from dual where not exists(select VET_ID from vet where VET_ID = 6)
insert into specialities(VET_ID, pet_type_id) select 6, 0 from dual where not exists(select * from specialities where VET_ID = 6 and pet_type_id = 0)


-- CLINIC

insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) select 1, 'Böszi-Vet', 6, '09:00', '19:00' from dual where not exists(select CLINIC_ID from clinic where CLINIC_ID = 1)
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) select 2, 'Állatorvosi Rendelo a Sánta Kutyához', 7, '09:00', '12:00' from dual where not exists(select CLINIC_ID from clinic where CLINIC_ID = 2)
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) select 3, 'Kerületi Állatorvos', 8, '09:00', '12:00' from dual where not exists(select CLINIC_ID from clinic where CLINIC_ID = 3)
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) select 4, 'Budapesti Állatkórház', 9, '00:01', '23:59' from dual where not exists(select CLINIC_ID from clinic where CLINIC_ID = 4)
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) select 5, 'Buda-Vet', 10, '09:00', '19:00' from dual where not exists(select CLINIC_ID from clinic where CLINIC_ID = 5)
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) select 6, 'Újpest Mancsok', 11, '09:00', '21:00' from dual where not exists(select CLINIC_ID from clinic where CLINIC_ID = 6)
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) select 7, 'Népkerti Állatorvosi Rendelo', 12, '09:00', '19:00' from dual where not exists(select CLINIC_ID from clinic where CLINIC_ID = 7)

-- JOIN VETS IN CLINICS

insert into VET_CLINIC(VET_ID, CLINIC_ID) select 1, 1 from dual where not exists(select * from VET_CLINIC where VET_ID = 1 and CLINIC_ID = 1)
insert into VET_CLINIC(VET_ID, CLINIC_ID) select 2, 1 from dual where not exists(select * from VET_CLINIC where VET_ID = 2 and CLINIC_ID = 1)
insert into VET_CLINIC(VET_ID, CLINIC_ID) select 3, 1 from dual where not exists(select * from VET_CLINIC where VET_ID = 3 and CLINIC_ID = 1)

insert into VET_CLINIC(VET_ID, CLINIC_ID) select 4, 2 from dual where not exists(select * from VET_CLINIC where VET_ID = 4 and CLINIC_ID = 2)

insert into VET_CLINIC(VET_ID, CLINIC_ID) select 1, 3 from dual where not exists(select * from VET_CLINIC where VET_ID = 1 and CLINIC_ID = 3)
insert into VET_CLINIC(VET_ID, CLINIC_ID) select 5, 3 from dual where not exists(select * from VET_CLINIC where VET_ID = 5 and CLINIC_ID = 3)

insert into VET_CLINIC(VET_ID, CLINIC_ID) select 5, 4 from dual where not exists(select * from VET_CLINIC where VET_ID = 5 and CLINIC_ID = 4)
insert into VET_CLINIC(VET_ID, CLINIC_ID) select 6, 4 from dual where not exists(select * from VET_CLINIC where VET_ID = 6 and CLINIC_ID = 4)

insert into VET_CLINIC(VET_ID, CLINIC_ID) select 2, 5 from dual where not exists(select * from VET_CLINIC where VET_ID = 2 and CLINIC_ID = 5)
insert into VET_CLINIC(VET_ID, CLINIC_ID) select 6, 5 from dual where not exists(select * from VET_CLINIC where VET_ID = 6 and CLINIC_ID = 5)

insert into VET_CLINIC(VET_ID, CLINIC_ID) select 3, 6 from dual where not exists(select * from VET_CLINIC where VET_ID = 3 and CLINIC_ID = 6)
insert into VET_CLINIC(VET_ID, CLINIC_ID) select 4, 6 from dual where not exists(select * from VET_CLINIC where VET_ID = 4 and CLINIC_ID = 6)

insert into VET_CLINIC(VET_ID, CLINIC_ID) select 4, 7 from dual where not exists(select * from VET_CLINIC where VET_ID = 4 and CLINIC_ID = 7)