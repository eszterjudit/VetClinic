-- CREATE TABLES FOR STORING USER CREDENTIALS
drop table if exists users
drop table if exists authorities
CREATE TABLE users (USERNAME VARCHAR(50), PASSWORD VARCHAR(500), ENABLED boolean, PRIMARY KEY (USERNAME))
CREATE TABLE authorities (USERNAME VARCHAR(50), AUTHORITY VARCHAR(20))

-- INSERT SAMPLE PET OWNER
insert into users(USERNAME, PASSWORD, ENABLED) values('jack.jonson@email.com', 'password', TRUE)
insert into authorities(USERNAME, AUTHORITY) values('jack.jonson@email.com', 'ROLE_USER')

-- INSERT SAMPLE VET
insert into users(USERNAME, PASSWORD, ENABLED) values('seth.barrett@email.com', 'password', TRUE)
insert into authorities(USERNAME, AUTHORITY) values('seth.barrett@email.com', 'ROLE_USER')

-- ADDRESS

insert into address(id, country, city, street, zip) values (1, 'Hungary', 'Budapest', 'Németvölgyi út 20', '1126' )
insert into address(id, country, city, street, zip) values (2, 'Hungary', 'Budapest', 'II. János Pál pápa tér 9', '1081')

-- PETOWNER

insert into pet_owner(id, first_name, last_name, email, phone, address_id) values (1,'Jack', 'Jonson', 'jack.jonson@email.com', '06301234567', 1)

-- PET

insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values (1, 1, 'Mr Snuggles', 3, '2005-05-07', 1)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values (2, 1, 'Bruno', 4, '2007-08-02', 1)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values (3, 0, 'Blanket', 10, '2004-10-04', 1)

-- VET

insert into vet(VET_ID, first_name, last_name, email, phone, address_id) values (1, 'Victor', 'White', 'victor.white12@email.com', '06205463728', 1)
insert into specialities(VET_ID, pet_type_id) values (1, 0)
insert into specialities(VET_ID, pet_type_id) values (1, 1)

-- CLINIC

insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values (1, 'Böszi-Vet', 1, '09:00', '19:00')

-- JOIN VETS IN CLINICS

insert into VET_CLINIC(VET_ID, CLINIC_ID) values (1, 1)