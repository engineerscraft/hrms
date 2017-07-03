INSERT INTO HRMS.ORGANISATION_TYPE_MASTER VALUES
(1, 'University', 'University');

INSERT INTO HRMS.ORGANISATION_MASTER VALUES
(1, 'Hamdard University', 1, 'Bangladesh');

INSERT INTO HRMS.UNIT_MASTER VALUES
(1, 'Hamdard University', 1, 'HUB', 'HUB_EMP_SEQ', 'Bangladesh');

INSERT INTO HRMS.DEPARTMENT_MASTER VALUES
(1, 'Dept. of Mathematics', 1, 'Bangladesh', NULL),
(2, 'Dept. of Electrical and Electronic Engineering', 1, 'Bangladesh', NULL),
(3, 'Dept. of Computer Science and Engineering', 1, 'Bangladesh', NULL),
(4, 'Dept. of Accounting', 1, 'Bangladesh', NULL),
(5, 'Dept. of Finance', 1, 'Bangladesh', NULL),
(6, 'Dept. of Management', 1, 'Bangladesh', NULL),
(7, 'Dept. of Marketing', 1, 'Bangladesh', NULL),
(8, 'Dept. of English', 1, 'Bangladesh', NULL),
(9, 'Dept. of Economics', 1, 'Bangladesh', NULL),
(10, 'Dept. of Unani Medicine', 1, 'Bangladesh', NULL),
(11, 'Dept. of Ayurvedic Medicine', 1, 'Bangladesh', NULL),
(12, 'Dept. Security', 1, 'Bangladesh', NULL),
(13, 'Dept. Human Resources', 1, 'Bangladesh', NULL);

INSERT INTO HRMS.doc_type_master values
(1, 'Pre-employment Medical Report', NULL),
(2, 'Passport', 'X'),
(3, 'Marriage Certificate', NULL),
(4, 'Graduation Certificate', NULL),
(5, 'Post-Graduation Certificate', NULL);

INSERT INTO HRMS.DESIGNATION_MASTER VALUES
(1,'Assistant Professor'),
(2, 'Lab. Assistant'),
(3, 'Lecturer'),
(4, 'Research Assistant'),
(5, 'Professor'),
(6, 'Head of Department'),
(7, 'Security Officer'),
(8, 'Security Guard'),
(9, 'HR Trainee'),
(10, 'HR Executive'),
(11, 'HR Head');

INSERT INTO HRMS.GRADE_MASTER VALUES
(1, 'T1'),
(2, 'T2'),
(3, 'T3'),
(4, 'T4'),
(5, 'T5'),
(6, 'H1'),
(7, 'H2'),
(8, 'H3'),
(9, 'S1'),
(10, 'S2');

INSERT INTO HRMS.JOB_ROLE_MASTER VALUES
(1, 1, 1, 1, 3),
(2, 1, 2, 1, 3),
(3, 1, 3, 1, 3),
(4, 1, 4, 1, 3),
(5, 1, 5, 1, 3),
(6, 1, 6, 1, 3),
(7, 1, 7, 1, 3),
(8, 1, 8, 1, 3),
(9, 1, 9, 1, 3),
(10, 1, 10, 1, 3);

INSERT INTO HRMS.job_role_designation VALUES
(1, 1),
(1, 2),
(1, 4),
(2, 3),
(2, 5),
(2, 6),
(3, 6),
(9, 7),
(9, 8),
(10, 8),
(6, 9),
(7, 10),
(8, 11);

INSERT INTO HRMS.employee (EMP_ID,TITLE,EMPLOYEE_FIRST_NAME,EMPLOYEE_LAST_NAME,SEX,MARITAL_STATUS,DATE_OF_JOINING,ORG_ID,UNIT_ID,DEPARTMENT_ID,NATIONALITY,IDENTITY_DOC_TYPE_ID,IDENTITY_NUMBER,DATE_OF_BIRTH,FATHER_NAME,EMAIL_ID,CONTACTNO,ENTRY_DATE) 
VALUES 
('HUB1','Dr.','Hillary','Sargent','Male','Divorced','1990-08-17 04:01:37','1','1','1','India','2','W6G 3V1','1960-01-28 14:11:41','Robert G. Lott','cubilia.Curae.Donec@faucibus.edu','1-812-495-5556','1987-05-18 12:44:55'),
('HUB2','Mrs.','Ryder','Salas','Male','Single','1993-06-19 06:49:01','1','1','1','India','2','H8B 4G5','1988-03-10 18:45:33','Neil A. Kerr','eu@Nuncullamcorper.net','1-545-262-9846','1986-05-15 16:11:24'),
('HUB3','Ms.','Maxwell','Norris','Others','Single','1995-12-25 18:45:37','1','1','1','India','2','Q8X 8C6','1989-08-25 17:35:57','Camden G. Leblanc','vitae.erat@maurissapien.org','1-568-793-2171','1996-07-25 18:54:52'),
('HUB4','Dr.','Quail','Larsen','Female','Divorced','1984-10-13 04:18:09','1','1','1','India','2','F8P 1B1','1987-06-08 07:13:26','Dalton Y. Rosario','Proin.vel.arcu@urnaetarcu.ca','1-896-674-4527','1984-12-23 00:33:06'),
('HUB5','Dr.','Ronan','Hudson','Others','Divorced','1992-07-07 13:36:26','1','1','1','India','2','T9P 2X3','1991-07-20 00:25:25','Brynne X. Dejesus','massa.Suspendisse@metusIn.co.uk','1-347-176-6681','1994-11-24 16:02:26'),
('HUB6','Mrs.','Zorita','Hamilton','Female','Divorced','2011-11-11 19:09:52','1','1','1','India','2','I6V 0M7','1979-12-07 14:45:02','Alexa J. Lewis','ipsum@vitaerisusDuis.ca','1-377-961-7159','1985-06-14 13:27:28'),
('HUB7','Ms.','Boris','Potts','Female','Married','2002-08-17 02:44:56','1','1','1','India','2','I1L 2K6','1980-09-24 06:29:03','Wylie V. Farley','odio@musProin.edu','1-158-304-9801','2009-03-30 19:06:13'),
('HUB8','Ms.','Aileen','Franco','Male','Divorced','1998-05-14 16:35:03','1','1','1','India','2','N7V 7B4','1960-05-18 09:51:21','Britanney N. Baxter','Aenean.sed@interdum.net','1-655-269-8704','1995-10-06 17:27:46'),
('HUB9','Dr.','Bertha','Clemons','Female','Common-Law','2013-04-01 22:04:55','1','1','1','India','2','P4O 3L6','1994-05-21 22:49:24','Montana M. Vega','mi@elit.ca','1-958-113-8634','1989-09-10 13:25:31'),
('HUB10','Mrs.','Brandon','George','Others','Common-Law','1988-10-19 22:51:09','1','1','1','India','2','M2P 1X0','1994-09-13 09:13:18','Imani E. Castillo','in.hendrerit.consectetuer@acfeugiatnon.net','1-303-660-1349','2002-02-25 18:04:01'),
('HUB11','Mrs.','Karina','Pittman','Male','Married','1985-01-04 14:26:11','1','1','1','India','2','L2R 6F3','1973-08-09 22:37:33','Keiko J. Horne','orci.tincidunt@amagna.com','1-649-556-7423','2013-04-27 07:35:48'),
('HUB12','Dr.','Ifeoma','Britt','Others','Divorced','2005-09-26 16:35:28','1','1','1','India','2','L6L 9E8','1972-07-01 10:40:12','Arthur T. Bradshaw','Maecenas.malesuada@semsemper.edu','1-441-730-5306','2010-08-16 23:57:24'),
('HUB13','Dr.','Ciaran','Lott','Others','Married','2012-10-15 16:49:44','1','1','1','India','2','Y5H 7X7','1976-12-11 16:36:28','Quon D. Yates','dolor.nonummy.ac@sapienCras.com','1-133-378-2925','1990-05-17 01:46:58'),
('HUB14','Mrs.','Lila','Wagner','Female','Divorced','1996-05-31 19:21:48','1','1','1','India','2','D3R 1S1','1996-01-23 22:30:26','Quin S. Shields','diam@convalliserat.com','1-912-667-0622','2011-10-31 11:30:08'),
('HUB15','Mr.','James','Fleming','Others','Single','2006-04-21 05:13:39','1','1','1','India','2','A8Q 9B4','1977-03-22 00:21:00','Avye Q. Pearson','Nullam.scelerisque.neque@montes.edu','1-893-820-1921','1991-11-23 05:27:32'),
('HUB16','Mr.','Courtney','Stanley','Female','Common-Law','1989-12-11 21:01:34','1','1','1','India','2','S7K 9F1','1989-07-16 10:22:58','Sara G. Kent','Aliquam.vulputate.ullamcorper@ipsumporta.com','1-468-140-6753','2013-01-16 05:07:34'),
('HUB17','Mr.','Tatiana','Potts','Male','Single','2014-07-30 20:58:19','1','1','1','India','2','Q4N 4O2','1961-05-08 13:54:58','Odysseus S. Ramirez','sed.sem@molestie.com','1-136-196-5998','2014-04-02 01:37:35'),
('HUB18','Ms.','Simone','Hooper','Female','Single','2001-12-16 09:30:51','1','1','1','India','2','N5P 6X2','1980-10-25 09:00:57','Willow J. Leon','primis.in.faucibus@dolorDonec.ca','1-585-120-6500','1989-08-05 10:12:46'),
('HUB19','Mr.','Clinton','Lynn','Male','Single','2007-08-13 12:23:31','1','1','1','India','2','M8A 5G1','1963-03-10 17:44:48','Thane C. Barnett','urna.Nullam.lobortis@nullavulputate.org','1-645-724-2478','2003-10-25 21:55:54'),
('HUB20','Mr.','Alec','Chavez','Others','Single','1988-09-13 18:15:47','1','1','1','India','2','V1H 6G1','1989-07-04 01:13:00','Ivan Y. Murray','lectus.ante.dictum@quis.org','1-646-900-1729','2016-07-28 09:32:24'),
('HUB21','Mrs.','Glenna','Castro','Male','Single','1985-08-25 01:18:12','1','1','1','India','2','A2V 3V7','1995-12-14 08:13:23','Nichole Z. Reynolds','amet.consectetuer.adipiscing@nectellusNunc.com','1-339-175-7247','1999-02-22 15:02:26'),
('HUB22','Mr.','Noelle','Dawson','Female','Married','1995-09-22 13:53:34','1','1','1','India','2','I4X 0N6','1972-10-06 23:07:30','Brenna W. Robinson','elementum@adipiscing.ca','1-687-381-0704','2013-03-27 13:26:34'),
('HUB23','Mr.','Emi','Cannon','Male','Divorced','2007-07-01 23:01:40','1','1','1','India','2','M9R 6J3','1985-07-26 16:46:43','Arsenio B. Marks','malesuada.id.erat@a.ca','1-548-702-3160','1994-02-20 02:27:50'),
('HUB24','Mrs.','Bernard','Gallagher','Male','Common-Law','2005-08-23 18:32:56','1','1','1','India','2','X1G 0U0','1975-04-22 21:29:49','Felix M. Mcfadden','adipiscing.Mauris.molestie@iaculis.ca','1-723-187-9392','1987-03-03 20:30:49'),
('HUB25','Ms.','Priscilla','Collins','Others','Single','2011-10-17 17:56:53','1','1','1','India','2','W1E 7Q2','1960-10-04 18:08:03','Samuel A. Hurley','Mauris@velmaurisInteger.com','1-870-172-2966','2000-07-18 07:29:37'),
('HUB26','Mrs.','Sloane','Gilmore','Male','Common-Law','2006-05-10 03:07:55','1','1','1','India','2','N7C 7H6','1988-04-23 13:07:19','Francesca R. Leblanc','imperdiet.dictum.magna@faucibusut.com','1-590-570-5699','2009-12-11 16:05:42'),
('HUB27','Mrs.','Honorato','Sullivan','Male','Divorced','2007-04-18 21:20:52','1','1','1','India','2','P1D 2O1','1967-06-22 22:23:26','Anthony Z. Aguirre','pede.Nunc.sed@ligula.org','1-304-786-1809','1988-06-15 10:32:31'),
('HUB28','Mr.','Buffy','Townsend','Others','Divorced','1990-10-09 22:45:06','1','1','1','India','2','L2L 3Q1','1988-03-22 13:18:23','Delilah W. Peters','rhoncus.Donec.est@vitae.ca','1-732-838-4790','1983-06-01 04:47:14'),
('HUB29','Mrs.','Eric','Cote','Female','Common-Law','2016-04-29 22:02:36','1','1','1','India','2','R8Y 3G5','1965-01-15 06:32:07','Velma I. Roberson','elit.Aliquam@estcongue.org','1-943-490-6563','1987-12-14 02:49:32'),
('HUB30','Mr.','Raya','Estes','Male','Married','2006-05-29 22:36:52','1','1','1','India','2','N9T 7R1','1960-11-24 17:41:07','Stewart M. Vasquez','nunc.sit.amet@molestie.org','1-148-874-3355','2007-08-25 05:19:32'),
('HUB31','Ms.','Evangeline','Charles','Others','Married','2014-09-04 00:31:34','1','1','1','India','2','Y7U 8C4','1973-02-17 08:11:18','Sylvia D. Mcmillan','quis@aliquamiaculislacus.co.uk','1-966-683-6679','2003-08-18 23:37:46'),
('HUB32','Mr.','Zeus','Figueroa','Others','Married','1984-05-11 05:52:11','1','1','1','India','2','L3G 8X1','1963-01-24 15:20:11','Skyler O. Richmond','facilisis.facilisis@euismod.org','1-467-116-4817','2015-12-02 02:20:43'),
('HUB33','Mr.','Dahlia','Oneal','Male','Married','2008-09-08 10:04:40','1','1','1','India','2','N2F 3E4','1990-08-20 22:48:59','Pamela Z. Powell','vel.arcu@semegestasblandit.org','1-870-532-7342','1985-03-12 02:34:39'),
('HUB34','Mr.','Julian','Wiggins','Others','Divorced','1985-07-27 03:23:03','1','1','1','India','2','J9E 3X8','1984-11-05 09:37:24','Charity T. Mendez','consectetuer@vulputatelacus.com','1-780-635-0699','1981-05-03 12:23:37'),
('HUB35','Ms.','Flynn','Sherman','Male','Common-Law','1988-02-19 10:59:27','1','1','1','India','2','S6L 4T0','1990-10-21 12:26:28','Nash T. Hogan','mauris.eu@Suspendissecommodo.org','1-346-374-1519','2007-03-23 01:34:42'),
('HUB36','Ms.','Maite','Casey','Female','Divorced','2013-07-14 07:19:06','1','1','1','India','2','K0R 0Q2','1964-07-30 21:11:24','Heather V. Huff','lectus.pede.ultrices@iaculis.org','1-488-439-6698','2015-04-15 02:05:08'),
('HUB37','Mr.','Ursula','Bradshaw','Male','Divorced','1985-03-04 09:47:37','1','1','1','India','2','U4P 3T7','1992-04-07 03:28:56','Francesca V. Garza','egestas@ante.edu','1-793-701-2866','1985-05-07 17:13:35'),
('HUB38','Ms.','Nadine','Glover','Male','Single','2008-09-23 10:25:53','1','1','1','India','2','W7I 5A3','1984-06-10 22:14:39','Justine D. Herring','vel@sedsapienNunc.net','1-871-933-0723','1992-02-02 20:41:06'),
('HUB39','Mr.','Guinevere','Mason','Male','Common-Law','1980-09-18 12:31:30','1','1','1','India','2','L3N 6Z4','1996-08-30 21:25:59','September O. Ramsey','aliquet.libero@velitAliquam.com','1-972-417-3456','2011-06-14 22:46:03'),
('HUB40','Mr.','Blaine','England','Male','Common-Law','1996-12-13 14:08:00','1','1','1','India','2','Y4J 9V2','1961-01-27 12:41:05','Scarlet Y. Dillon','Aliquam.nec@accumsanconvallisante.ca','1-155-467-5796','2000-06-07 10:51:43'),
('HUB41','Ms.','Hermione','Nguyen','Female','Married','2000-07-14 21:35:03','1','1','1','India','2','X2E 4N0','1978-10-03 11:27:56','Castor H. Harding','rhoncus.id.mollis@tempusscelerisque.net','1-253-611-4952','2008-06-04 10:39:33'),
('HUB42','Ms.','Guy','Leblanc','Others','Common-Law','1990-02-19 16:29:18','1','1','1','India','2','R0P 8U1','1959-10-30 22:19:38','Emerson S. Gonzalez','dolor.Donec@non.co.uk','1-646-602-8618','1982-10-20 23:34:41'),
('HUB43','Mr.','Amy','Hall','Male','Married','2009-09-05 20:24:50','1','1','1','India','2','Y4H 3P6','1973-12-20 00:21:37','Michael L. Robertson','eu.tempor@urnaVivamusmolestie.org','1-930-405-8914','1995-01-08 08:39:17'),
('HUB44','Mr.','Chastity','Vance','Others','Common-Law','1981-03-14 09:03:48','1','1','1','India','2','T0T 9O5','1989-07-26 18:25:51','Martina Q. Briggs','aliquet@hendreritid.ca','1-681-720-2491','1995-02-28 12:50:26'),
('HUB45','Ms.','Vielka','Hebert','Female','Single','2003-11-11 07:41:45','1','1','1','India','2','K5R 9V2','1989-12-26 18:50:25','Linus U. Booth','ornare.libero.at@acmattisvelit.edu','1-830-511-7961','2014-08-14 19:45:41'),
('HUB46','Mr.','Aubrey','Knox','Male','Married','2011-05-13 14:25:27','1','1','1','India','2','C3G 8S0','1970-05-22 10:40:36','Castor E. Monroe','volutpat.ornare.facilisis@luctus.edu','1-134-857-8576','1988-09-24 15:39:09'),
('HUB47','Mr.','Stone','Roth','Others','Single','1987-09-06 13:33:31','1','1','1','India','2','X3S 6X2','1988-03-28 10:57:39','Brianna X. Hogan','purus@Ut.com','1-959-206-7262','1997-10-08 23:24:54'),
('HUB48','Mr.','Nyssa','Bentley','Female','Married','2003-04-28 22:42:25','1','1','1','India','2','F1Z 3J7','1967-05-12 17:26:42','Malcolm M. Solis','tristique.pellentesque.tellus@vitae.edu','1-484-194-9577','2016-06-17 12:27:04'),
('HUB49','Dr.','Jorden','Lindsay','Male','Married','2000-02-22 10:04:56','1','1','1','India','2','W3U 3I6','1990-05-21 12:52:12','Odessa C. Patrick','Morbi.quis.urna@loremluctus.org','1-452-162-1213','2010-02-09 05:09:29'),
('HUB50','Mr.','Indira','Bowman','Others','Single','2015-10-17 16:22:23','1','1','1','India','2','Z4Y 5K0','1980-12-27 05:11:04','Inez Y. Hancock','rutrum.lorem.ac@urnaNullamlobortis.com','1-824-678-1635','1984-07-05 14:17:41'),
('HUB51','Mrs.','Zelenia','Craig','Others','Common-Law','2007-10-02 09:40:41','1','1','1','India','2','G5V 7K5','1978-08-22 21:35:49','Elijah A. Stephenson','ac@Etiambibendumfermentum.org','1-307-546-7245','2009-10-12 07:33:28'),
('HUB52','Mr.','Hanae','Frank','Others','Single','2007-01-09 02:18:23','1','1','1','India','2','L8O 1B6','1987-02-25 08:33:57','Ramona Q. Klein','penatibus.et.magnis@egetipsumDonec.com','1-881-667-8973','1983-01-22 12:49:08'),
('HUB53','Mrs.','Daria','Sullivan','Female','Divorced','1985-03-28 06:37:53','1','1','1','India','2','O8Q 8I5','1977-08-31 14:49:28','Audrey O. Alston','Sed.eget.lacus@Nam.edu','1-422-509-0856','2009-10-30 10:58:32'),
('HUB54','Mrs.','Hillary','Guthrie','Female','Divorced','1984-06-24 11:21:31','1','1','1','India','2','W4Z 9U9','1986-05-15 06:57:53','Ray C. Hickman','tellus@eleifendCras.com','1-601-299-0401','2007-11-24 17:49:38'),
('HUB55','Dr.','Tyrone','Everett','Others','Divorced','1992-04-10 20:39:26','1','1','1','India','2','D1U 9G6','1961-10-21 14:10:22','Cedric I. Hensley','magna.sed@neque.edu','1-446-788-6683','1995-04-22 22:17:58'),
('HUB56','Mr.','Leroy','Foster','Male','Single','1991-04-04 08:23:34','1','1','1','India','2','Q3B 7H8','1985-06-05 10:35:43','Aphrodite A. Fuller','et.magna.Praesent@aliquamarcu.co.uk','1-891-472-9869','1993-06-08 23:48:11'),
('HUB57','Mrs.','Adrian','Delacruz','Male','Single','1981-03-05 18:51:30','1','1','1','India','2','Z7A 1L5','1975-03-08 05:27:39','Quemby I. Dyer','Nunc.quis@vulputate.org','1-989-364-0122','1985-08-27 13:08:19'),
('HUB58','Dr.','Moses','Bean','Male','Divorced','1998-01-07 01:54:38','1','1','1','India','2','Z2P 0V2','1994-03-14 12:24:39','Cairo G. Foster','sagittis@eget.co.uk','1-461-687-9202','1985-03-17 01:21:10'),
('HUB59','Ms.','Grant','Mejia','Male','Single','1994-11-15 06:30:41','1','1','1','India','2','A8P 0F1','1986-01-25 05:43:23','Beck J. Nicholson','aliquam.enim.nec@augueeu.org','1-311-753-0712','1982-05-07 04:05:07'),
('HUB60','Ms.','Hedley','Donovan','Male','Common-Law','1983-03-31 02:33:42','1','1','1','India','2','G0J 9P1','1984-04-25 19:34:53','Sybill A. Mcfarland','et@odioa.co.uk','1-245-470-8997','1983-10-09 10:27:33'),
('HUB61','Mr.','Kaden','Blair','Male','Married','2003-07-16 10:18:31','1','1','1','India','2','A0R 2Z7','1995-11-13 20:04:26','Libby F. Ayers','Sed.dictum@dictumultriciesligula.net','1-270-603-9880','2015-11-26 14:34:56'),
('HUB62','Mr.','Stacey','Doyle','Female','Common-Law','1993-02-04 00:38:20','1','1','1','India','2','K1A 6R4','1964-08-08 12:43:38','Berk V. Weber','nulla.Integer@montesnascetur.net','1-682-607-4785','2000-12-15 09:40:33'),
('HUB63','Ms.','Jamalia','Contreras','Others','Divorced','1983-12-23 17:06:02','1','1','1','India','2','G6U 8N7','1976-08-25 21:44:34','Kane M. Sampson','lectus.a@Integeraliquam.edu','1-329-957-7289','2006-08-13 07:00:37'),
('HUB64','Ms.','Sydney','Pope','Male','Married','2009-12-28 00:26:12','1','1','1','India','2','O1P 9O8','1974-08-31 07:26:12','Ulric N. Ball','dui@Mauris.com','1-455-789-7421','2002-03-08 09:29:49'),
('HUB65','Dr.','Byron','Ware','Female','Common-Law','2009-10-19 21:53:49','1','1','1','India','2','W3P 3V0','1971-09-07 12:51:51','Tanya L. Thomas','lectus.ante.dictum@enimnon.net','1-900-219-4148','1985-08-13 22:03:57'),
('HUB66','Mr.','Melodie','Pollard','Male','Common-Law','1996-06-26 13:07:13','1','1','1','India','2','B0P 1L7','1963-05-28 11:49:34','Robert K. Vang','egestas.Duis.ac@velitjustonec.edu','1-414-669-5012','2012-08-20 14:56:26'),
('HUB67','Mr.','Denton','Poole','Male','Married','2002-09-30 17:36:46','1','1','1','India','2','Z1I 9Q5','1964-08-18 05:12:27','Jasper U. Adkins','eget.lacus@nonummyFuscefermentum.ca','1-640-253-7657','2011-08-09 01:01:59'),
('HUB68','Dr.','Lenore','Pace','Female','Divorced','1996-11-12 14:47:13','1','1','1','India','2','R3Y 4R3','1988-03-31 02:24:27','Eric L. Mullen','et@nec.edu','1-268-639-8900','1985-02-15 04:04:20'),
('HUB69','Mr.','Florence','Mcneil','Male','Single','1984-02-15 20:21:06','1','1','1','India','2','B4M 9I6','1989-08-06 06:50:10','Orli Q. Hendrix','Maecenas.malesuada.fringilla@pede.org','1-352-426-0024','2002-10-18 19:50:18'),
('HUB70','Dr.','Oprah','Atkinson','Male','Married','1988-12-13 17:27:13','1','1','1','India','2','T0R 9H0','1975-05-24 11:59:34','Jolie Q. Glass','penatibus@aodio.ca','1-380-432-0136','2000-04-27 21:43:31'),
('HUB71','Mr.','Edward','Hinton','Female','Divorced','1982-02-17 04:29:46','1','1','1','India','2','T7H 4S6','1989-02-05 19:57:39','Maxine H. Cline','dolor.sit@massarutrummagna.com','1-663-236-8805','1994-07-27 05:08:18'),
('HUB72','Mr.','Yvonne','Camacho','Female','Married','2000-05-25 18:06:04','1','1','1','India','2','O5I 9P0','1991-11-28 17:12:35','Lydia Y. Harmon','Lorem.ipsum@nonfeugiat.co.uk','1-262-122-9542','2014-10-16 22:41:24'),
('HUB73','Mr.','Maggie','Harper','Male','Married','1990-03-25 15:19:16','1','1','1','India','2','B8G 3H8','1980-12-21 20:36:05','Elaine N. Drake','mauris@utdolor.co.uk','1-447-717-9659','1999-10-24 13:08:55'),
('HUB74','Mr.','Driscoll','Berry','Male','Common-Law','1988-12-22 07:56:07','1','1','1','India','2','B0Z 3M7','1984-01-03 19:57:02','Melodie T. Bonner','magna@Sedpharetrafelis.net','1-541-314-9382','2012-05-10 14:52:44'),
('HUB75','Dr.','Ronan','Jordan','Male','Single','1983-04-15 07:51:50','1','1','1','India','2','N1H 8W6','1975-02-25 16:47:57','Drew Z. Eaton','enim.Etiam@tortorNunccommodo.com','1-410-286-4367','1994-02-11 10:36:32'),
('HUB76','Ms.','Samantha','Faulkner','Others','Common-Law','1981-09-07 21:23:37','1','1','1','India','2','W0M 4X1','1981-08-06 20:47:57','Baker P. Parsons','ante.Nunc@Quisquepurussapien.co.uk','1-307-954-3525','1996-05-06 04:01:39'),
('HUB77','Mrs.','Arden','Ashley','Male','Divorced','1984-08-19 08:29:54','1','1','1','India','2','M5D 2S7','1974-12-08 03:55:47','Cody U. Hays','diam.eu@Morbisitamet.org','1-265-415-1582','2010-05-06 16:42:30'),
('HUB78','Mrs.','Dominique','Melendez','Female','Single','1988-09-29 01:37:22','1','1','1','India','2','Y6D 6K3','1983-12-13 04:56:49','Wing D. Arnold','neque.Nullam@ultricies.com','1-642-912-6032','1993-03-21 23:06:29'),
('HUB79','Ms.','Ross','Beasley','Male','Common-Law','1997-07-24 13:20:38','1','1','1','India','2','A9W 4A0','1991-11-04 19:42:58','Mona W. Gomez','luctus@consequatenim.org','1-306-216-8944','1991-04-11 21:11:41'),
('HUB80','Mrs.','Dana','Albert','Male','Married','2014-01-07 23:29:29','1','1','1','India','2','W0Y 3Z5','1996-04-12 10:25:44','Lillian P. Odom','semper.Nam.tempor@massarutrummagna.com','1-272-662-9683','2006-09-18 20:53:35'),
('HUB81','Dr.','Nelle','Lindsay','Male','Divorced','1993-08-17 03:24:29','1','1','1','India','2','S7Y 8Z3','1974-09-18 19:24:29','Kylie A. Sargent','Maecenas@SedmolestieSed.co.uk','1-776-150-5316','1982-05-02 07:25:58'),
('HUB82','Mrs.','Aladdin','Mcfarland','Others','Married','2002-10-09 07:35:32','1','1','1','India','2','G2P 4N9','1981-04-21 00:03:27','Petra H. Hahn','mauris.Suspendisse@est.ca','1-496-822-7090','1981-02-26 17:37:00'),
('HUB83','Dr.','Larissa','Keith','Others','Divorced','1985-09-25 10:29:07','1','1','1','India','2','C1V 3A6','1982-12-26 10:59:24','Lani K. Raymond','nec.urna.suscipit@torquentperconubia.co.uk','1-302-868-7380','1982-04-20 05:44:15'),
('HUB84','Dr.','Lysandra','Guzman','Female','Single','2015-09-14 09:40:58','1','1','1','India','2','Q8V 1M9','1966-05-05 02:12:36','Kasper K. Mclaughlin','et.ultrices@aliquetsem.co.uk','1-519-387-4717','2011-12-25 09:48:28'),
('HUB85','Mr.','Xantha','Patton','Female','Single','2010-02-08 11:26:50','1','1','1','India','2','Z4D 5Z0','1968-06-04 14:22:12','Hyatt G. Roberson','interdum@vulputatenisi.org','1-935-953-8004','1983-10-10 08:26:14'),
('HUB86','Ms.','Lacy','Carr','Female','Divorced','2008-09-11 10:30:00','1','1','1','India','2','Y1Y 2K9','1969-11-14 12:29:11','Amaya E. Atkinson','pretium.et@miac.co.uk','1-285-329-1806','1987-10-10 03:13:43'),
('HUB87','Mr.','Vera','Webster','Female','Divorced','1999-07-26 04:03:27','1','1','1','India','2','O5E 6F1','1988-04-26 20:23:05','Cassidy P. Roy','molestie@Maecenasiaculis.net','1-462-385-8104','1991-06-01 22:44:05'),
('HUB88','Mrs.','Chancellor','Joseph','Male','Married','2017-02-25 12:30:54','1','1','1','India','2','C0D 9O8','1973-10-31 09:59:01','Denise I. Baker','semper@eleifendegestas.co.uk','1-333-251-7732','1993-09-16 11:26:12'),
('HUB89','Mr.','Dante','Rose','Others','Single','1984-07-18 03:32:17','1','1','1','India','2','O9F 5Y4','1970-05-29 01:31:28','Kylee R. Higgins','amet.ultricies@aarcuSed.org','1-895-228-9324','2005-08-23 21:12:37'),
('HUB90','Dr.','Talon','Merritt','Others','Divorced','2001-05-23 17:51:14','1','1','1','India','2','V8P 0O2','1966-05-10 20:28:50','Victoria K. Moses','ipsum@lobortisaugue.com','1-124-554-3527','2017-03-24 04:51:28'),
('HUB91','Mr.','Randall','Walls','Others','Single','2014-02-07 18:23:28','1','1','2','India','2','G7T 3M2','1968-06-12 09:46:57','Kato F. Spears','est.vitae.sodales@iaculisquispede.edu','1-785-557-1576','2016-02-20 02:44:06'),
('HUB92','Ms.','Lois','James','Male','Married','2001-11-17 06:57:58','1','1','2','India','2','S4E 2C1','1967-04-28 07:29:08','Nissim D. Cannon','auctor@tellus.org','1-177-267-3452','2007-09-17 13:02:23'),
('HUB93','Dr.','Althea','Moses','Female','Single','1983-12-07 19:23:52','1','1','2','India','2','T4V 9H9','1961-10-10 03:00:25','Ray L. Pace','risus.Nulla.eget@elit.co.uk','1-591-166-3589','1982-09-11 21:02:43'),
('HUB94','Ms.','Kellie','Mcconnell','Male','Common-Law','2014-05-18 14:53:12','1','1','2','India','2','E6W 9E1','1991-10-02 13:04:34','Sean I. Diaz','nisi.dictum.augue@anteiaculis.co.uk','1-257-561-4551','2006-03-17 03:34:10'),
('HUB95','Mr.','Avram','Hudson','Others','Common-Law','1982-04-02 04:34:39','1','1','2','India','2','N0Y 4N4','1969-05-21 07:44:29','Bree Q. Mcbride','diam.at@duiCras.org','1-440-691-9908','2016-01-16 00:26:57'),
('HUB96','Mr.','Burton','Jensen','Male','Single','2016-06-17 15:51:06','1','1','2','India','2','W1R 3K0','1995-12-08 05:03:59','Claudia P. Griffin','erat.Sed.nunc@urna.edu','1-280-279-4452','1981-10-29 21:23:09'),
('HUB97','Dr.','Clementine','Donaldson','Male','Divorced','2009-07-13 03:14:43','1','1','2','India','2','U0C 7D2','1963-05-07 15:57:54','Dominique T. Rodriguez','vel@euaugue.ca','1-661-313-3634','2017-05-02 15:27:54'),
('HUB98','Mr.','Chiquita','Dennis','Others','Single','1999-05-09 19:12:32','1','1','2','India','2','D3Y 8P8','1988-10-10 01:12:07','Clinton B. Norris','egestas.blandit.Nam@Curabiturvellectus.edu','1-922-870-2801','1998-11-14 03:36:14'),
('HUB99','Mrs.','Irma','Rosales','Male','Single','1997-10-27 22:02:42','1','1','2','India','2','A7P 6U7','1972-09-28 08:11:36','Dalton T. Adkins','blandit.enim.consequat@sapienmolestieorci.org','1-529-119-9087','1983-01-03 16:48:29'),
('HUB100','Mr.','Lani','Sexton','Male','Divorced','2017-02-28 05:22:23','1','1','2','India','2','H8S 7Y3','1967-07-30 04:24:45','Quinn G. Nixon','sem.mollis.dui@necquamCurabitur.org','1-419-332-9541','2004-06-24 11:26:10');


INSERT INTO HRMS.employee_hierarchy_status (EMP_ID, SUPERVISOR_ID, HR_ID)
(SELECT EMP_ID, 'HUB1', 'HUB10' FROM HRMS.EMPLOYEE);

UPDATE HRMS.employee_hierarchy_status SET SUPERVISOR_ID=NULL WHERE EMP_ID='HUB1';

UPDATE HRMS.employee_hierarchy_status SET HR_ID=NULL WHERE EMP_ID='HUB10';

UPDATE HRMS.employee_hierarchy_status SET SUPERVISOR_ID='HUB42' where CAST(substr(emp_id,4) AS INTEGER)>42;

UPDATE HRMS.employee_hierarchy_status SET SUPERVISOR_ID='HUB60' where CAST(substr(emp_id,4) AS INTEGER)>60;

UPDATE HRMS.EMPLOYEE SET SUPERVISOR_FLAG='X' WHERE EMP_ID IN ('HUB1','HUB42','HUB60');

INSERT INTO HRMS.APPRAISAL_CYCLE VALUES 
(1, '2015', to_date('01-JAN-2015', 'DD-MON-YYYY'), to_date('31-DEC-2015', 'DD-MON-YYYY'), to_date('1-NOV-2015', 'DD-MON-YYYY'), to_date('31-DEC-2015', 'DD-MON-YYYY'), to_date('01-JAN-2016', 'DD-MON-YYYY'), to_date('01-FEB-2016', 'DD-MON-YYYY')),
(2, '2016', to_date('01-JAN-2016', 'DD-MON-YYYY'), to_date('31-DEC-2016', 'DD-MON-YYYY'), to_date('1-NOV-2016', 'DD-MON-YYYY'), to_date('31-DEC-2016', 'DD-MON-YYYY'), to_date('01-JAN-2017', 'DD-MON-YYYY'), to_date('01-FEB-2017', 'DD-MON-2017')),
(3, '2016', to_date('01-JAN-2017', 'DD-MON-YYYY'), to_date('31-DEC-2017', 'DD-MON-YYYY'), to_date('1-APR-2017', 'DD-MON-YYYY'), to_date('31-DEC-2017', 'DD-MON-YYYY'), NULL, NULL);

INSERT INTO HRMS.APPRAISAL_RATING
SELECT EMP_ID, APPRAISAL_ID, 3, 'Good', 1, 1
FROM 
HRMS.APPRAISAL_CYCLE A, HRMS.EMPLOYEE B;