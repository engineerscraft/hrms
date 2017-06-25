INSERT INTO ORGANISATION_MASTER VALUES
(1, 'Hamdard University', 1, 'Bangladesh');

INSERT INTO ORGANISATION_TYPE_MASTER VALUES
(1, 'University', 'University');

INSERT INTO UNIT_MASTER VALUES
(1, 'Hamdard University', 1, 'HUB', 'HUB_EMP_SEQ', 'Bangladesh');

INSERT INTO DEPARTMENT_MASTER VALUES
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
(11, 'Dept. of Ayurvedic Medicine', 1, 'Bangladesh', NULL);

INSERT INTO doc_type_master values
(1, 'Pre-employment Medical Report', NULL),
(2, 'Passport', 'X'),
(3, 'Marriage Certificate', NULL),
(4, 'Graduation Certificate', NULL),
(5, 'Post-Graduation Certificate', NULL);

INSERT INTO DESIGNATION_MASTER VALUES
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

INSERT INTO GRADE_MASTER VALUES
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

INSERT INTO JOB_ROLE_MASTER VALUES
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

INSERT INTO job_role_designation VALUES
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

