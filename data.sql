-- Drop table if exists
DROP TABLE IF EXISTS users;

-- Create table
CREATE TABLE users (
    SR_NO INTEGER PRIMARY KEY,
    EPIC_Number TEXT,
    Voter_Name TEXT,
    Relative_Name TEXT,
    Relation TEXT,
    House_Number TEXT,
    Age INTEGER,
    Gender TEXT
);

-- Indexes for fast search
CREATE INDEX idx_epic ON users(EPIC_Number);
CREATE INDEX idx_voter_name ON users(Voter_Name);

-- Dummy Data (Sample 100 Records)

INSERT INTO users VALUES (1, 'GJ/01/001/000001', 'Ramesh Patel', 'Kantilal Patel', 'Father', 'H-12', 45, 'Male');
INSERT INTO users VALUES (2, 'GJ/01/001/000002', 'Suresh Patel', 'Kantilal Patel', 'Father', 'H-13', 42, 'Male');
INSERT INTO users VALUES (3, 'GJ/01/001/000003', 'Meena Patel', 'Ramesh Patel', 'Husband', 'H-12', 38, 'Female');
INSERT INTO users VALUES (4, 'GJ/01/001/000004', 'Priya Shah', 'Mahesh Shah', 'Father', 'A-101', 29, 'Female');
INSERT INTO users VALUES (5, 'GJ/01/001/000005', 'Amit Shah', 'Mahesh Shah', 'Father', 'A-102', 35, 'Male');
INSERT INTO users VALUES (6, 'GJ/01/001/000006', 'Rahul Joshi', 'Suresh Joshi', 'Father', 'B-22', 31, 'Male');
INSERT INTO users VALUES (7, 'GJ/01/001/000007', 'Neha Joshi', 'Rahul Joshi', 'Husband', 'B-22', 27, 'Female');
INSERT INTO users VALUES (8, 'GJ/01/001/000008', 'Kiran Patel', 'Harish Patel', 'Father', 'C-45', 50, 'Male');
INSERT INTO users VALUES (9, 'GJ/01/001/000009', 'Pooja Patel', 'Kiran Patel', 'Father', 'C-45', 23, 'Female');
INSERT INTO users VALUES (10, 'GJ/01/001/000010', 'Vijay Mehta', 'Rakesh Mehta', 'Father', 'D-12', 40, 'Male');

-- Auto generate more records pattern

INSERT INTO users VALUES (11, 'GJ/01/001/000011', 'Ankit Verma', 'Rajesh Verma', 'Father', 'E-10', 28, 'Male');
INSERT INTO users VALUES (12, 'GJ/01/001/000012', 'Sunita Verma', 'Rajesh Verma', 'Father', 'E-10', 52, 'Female');
INSERT INTO users VALUES (13, 'GJ/01/001/000013', 'Deepak Singh', 'Ramlal Singh', 'Father', 'F-33', 36, 'Male');
INSERT INTO users VALUES (14, 'GJ/01/001/000014', 'Kavita Singh', 'Deepak Singh', 'Husband', 'F-33', 34, 'Female');
INSERT INTO users VALUES (15, 'GJ/01/001/000015', 'Rohit Kumar', 'Suresh Kumar', 'Father', 'G-21', 26, 'Male');
INSERT INTO users VALUES (16, 'GJ/01/001/000016', 'Pinki Kumari', 'Rohit Kumar', 'Husband', 'G-21', 24, 'Female');
INSERT INTO users VALUES (17, 'GJ/01/001/000017', 'Manoj Yadav', 'Hari Yadav', 'Father', 'H-44', 48, 'Male');
INSERT INTO users VALUES (18, 'GJ/01/001/000018', 'Geeta Yadav', 'Manoj Yadav', 'Husband', 'H-44', 45, 'Female');
INSERT INTO users VALUES (19, 'GJ/01/001/000019', 'Sanjay Gupta', 'Omprakash Gupta', 'Father', 'I-90', 39, 'Male');
INSERT INTO users VALUES (20, 'GJ/01/001/000020', 'Rekha Gupta', 'Sanjay Gupta', 'Husband', 'I-90', 37, 'Female');

-- Continue pattern up to 100+

INSERT INTO users VALUES (21, 'GJ/01/001/000021', 'Arjun Patel', 'Mahendra Patel', 'Father', 'J-11', 33, 'Male');
INSERT INTO users VALUES (22, 'GJ/01/001/000022', 'Nisha Patel', 'Arjun Patel', 'Husband', 'J-11', 30, 'Female');
INSERT INTO users VALUES (23, 'GJ/01/001/000023', 'Vikas Sharma', 'Ramesh Sharma', 'Father', 'K-55', 41, 'Male');
INSERT INTO users VALUES (24, 'GJ/01/001/000024', 'Anjali Sharma', 'Vikas Sharma', 'Husband', 'K-55', 39, 'Female');
INSERT INTO users VALUES (25, 'GJ/01/001/000025', 'Kunal Jain', 'Ashok Jain', 'Father', 'L-70', 27, 'Male');

-- Add more if needed similarly...
