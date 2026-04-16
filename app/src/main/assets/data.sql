-- Dummy data for voter database
CREATE TABLE IF NOT EXISTS users (
    SR_NO INTEGER PRIMARY KEY,
    EPIC_Number TEXT,
    Voter_Name TEXT,
    Relative_Name TEXT,
    Relation TEXT,
    House_Number TEXT,
    Age INTEGER,
    Gender TEXT
);

INSERT INTO users VALUES (1, 'ABC123', 'John Doe', 'Jane Doe', 'Wife', '123 Main St', 30, 'Male');
INSERT INTO users VALUES (2, 'DEF456', 'Jane Doe', 'John Doe', 'Husband', '123 Main St', 28, 'Female');
INSERT INTO users VALUES (3, 'GHI789', 'Bob Smith', 'Alice Smith', 'Mother', '456 Elm St', 45, 'Male');
-- Add more dummy data as needed