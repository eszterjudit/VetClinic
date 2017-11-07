-- ADDRESS

insert into address(id, country, city, street, zip) values(1, 'Hungary', 'Budapest', 'Németvölgyi út 20', '1126')
insert into address(id, country, city, street, zip) values(2, 'Hungary', 'Budapest', 'II. János Pál pápa tér 9', '1081')
insert into address(id, country, city, street, zip) values(3, 'Hungary', 'Budapest', 'Fecske utca 1', '1084')
insert into address(id, country, city, street, zip) values(4, 'Hungary', 'Budapest', 'Molnár utca 3', '1056')
insert into address(id, country, city, street, zip) values(5, 'Hungary', 'Budapest', 'Báthory utca 3', '1054')

insert into address(id, country, city, street, zip) values(6, 'Hungary', 'Budapest', 'Böszörményi út 3b', '1126')
insert into address(id, country, city, street, zip) values(7, 'Hungary', 'Budapest', 'Páva utca 9', '1094')
insert into address(id, country, city, street, zip) values(8, 'Hungary', 'Budapest', 'Marek József utca 11', '1078')
insert into address(id, country, city, street, zip) values(9, 'Hungary', 'Budapest', 'Lehel utca 43', '1135')
insert into address(id, country, city, street, zip) values(10, 'Hungary', 'Budapest', 'Tartsay Vilmos u. 19', '1126')
insert into address(id, country, city, street, zip) values(11, 'Hungary', 'Budapest', 'Chinoin u. 4', '1045')
insert into address(id, country, city, street, zip) values(12, 'Hungary', 'Miskolc', 'Lévay József utca 1', '3529')

-- PETOWNER

insert into pet_owner(id, first_name, last_name, email, phone, address_id) values(1,'Jack', 'Jonson', 'jack.jonson@email.com', '06301234567', 1)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) values(2,'Rose', 'Martin', 'rose.martin11@email.com', '7772028', 2)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) values(3,'George', 'Bennett', 'george.bennett21@email.com', '06206008590', 3)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) values(4,'Mátyás', 'Gábor', 'matyasgabor123@email.com', '0670601378', 4)
insert into pet_owner(id, first_name, last_name, email, phone, address_id) values(5,'Csilla', 'Kovács', 'kovacscsilla1@email.com', '06302536472', 5)

-- PET

insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(1, 1, 'Mr Snuggles', 3, '2005-05-07', 1)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(2, 1, 'Bruno', 4, '2007-08-02', 1)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(3, 0, 'Blanket', 10, '2004-10-04', 1)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(4, 2, 'Cold', 0.5, '2015-01-03', 2)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(5, 0, 'Picasso', 2, '2017-05-07', 2)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(6, 4, 'Rigby', 1, '2005-05-07', 2)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(7, 3, 'Danger', 0.4, '2015-11-01', 3)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(8, 0, 'Bert', 7, '2012-01-10', 3)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(9, 2, 'Mint', 3, '2000-03-05', 3)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(10, 3, 'Sandy', 2, '2014-06-06', 4)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(11, 4, 'Tank', 3, '2016-01-07', 4)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(12, 4, 'Roseanne', 1, '2014-02-02', 5)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(13, 1, 'Ernest', 9, '2014-08-19', 5)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(14, 3, 'Rotor', 1, '2016-02-10', 5)
insert into pet(id, pet_type, pet_name, pet_weight, date_of_birth, pet_owner_id) values(15, 0, 'Sassy', 1, '2014-10-02', 5)

-- VET

insert into vet(VET_ID, first_name, last_name, email, phone, address_id) values(1, 'Victor', 'White', 'victor.white12@email.com', '06205463728', 1)
insert into specialities(VET_ID, pet_type_id) values(1, 0)
insert into specialities(VET_ID, pet_type_id) values(1, 1)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) values(2, 'Tracy', 'Reed', 'tracy.reed@email.com', '06305463732', 2)
insert into specialities(VET_ID, pet_type_id) values(2, 0)
insert into specialities(VET_ID, pet_type_id) values(2, 1)
insert into specialities(VET_ID, pet_type_id) values(2, 2)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) values(3, 'Bill', 'Orbien', 'billorbien@email.com', '06701234567',3)
insert into specialities(VET_ID, pet_type_id) values(3, 2)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) values(4, 'Ruben', 'Stone', 'ruben.stone33@email.com', '06204352601', 4)
insert into specialities(VET_ID, pet_type_id) values(4, 3)
insert into specialities(VET_ID, pet_type_id) values(4, 4)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) values(5, 'Seth', 'Barrett', 'seth.barrett@email.com', '2345677',5)
insert into specialities(VET_ID, pet_type_id) values(5, 4)
insert into vet(VET_ID, first_name, last_name, email, phone, address_id) values(6, 'Cindy', 'Jackson', 'cindy.jackson123@email.com', '06304567890',6)
insert into specialities(VET_ID, pet_type_id) values(6, 0)


-- CLINIC

insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values(1, 'Böszi-Vet', 6, '09:00', '19:00')
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values(2, 'Állatorvosi Rendelő a Sánta Kutyához', 7, '09:00', '12:00')
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values(3, 'Kerületi Állatorvos', 8, '09:00', '12:00')
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values(4, 'Budapesti Állatkórház', 9, '00:01', '23:59')
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values(5, 'Buda-Vet', 10, '09:00', '19:00')
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values(6, 'Újpest Mancsok', 11, '09:00', '21:00')
insert into clinic(CLINIC_ID, clinic_name, address_id, opening_hour, closing_hour) values(7, 'Népkerti Állatorvosi Rendelő', 12, '09:00', '19:00')

-- JOIN VETS IN CLINICS

insert into VET_CLINIC(VET_ID, CLINIC_ID) values(1, 1)
insert into VET_CLINIC(VET_ID, CLINIC_ID) values(2, 1)
insert into VET_CLINIC(VET_ID, CLINIC_ID) values(3, 1)

insert into VET_CLINIC(VET_ID, CLINIC_ID) values(4, 2)

insert into VET_CLINIC(VET_ID, CLINIC_ID) values(1, 3)
insert into VET_CLINIC(VET_ID, CLINIC_ID) values(5, 3)

insert into VET_CLINIC(VET_ID, CLINIC_ID) values(5, 4)
insert into VET_CLINIC(VET_ID, CLINIC_ID) values(6, 4)

insert into VET_CLINIC(VET_ID, CLINIC_ID) values(2, 5)
insert into VET_CLINIC(VET_ID, CLINIC_ID) values(6, 5)

insert into VET_CLINIC(VET_ID, CLINIC_ID) values(3, 6)
insert into VET_CLINIC(VET_ID, CLINIC_ID) values(4, 6)

insert into VET_CLINIC(VET_ID, CLINIC_ID) values(4, 7)