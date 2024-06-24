CREATE TABLE preston_holiday (
    id SERIAL PRIMARY KEY NOT NULL AUTO_INCREMENT,
    holiday VARCHAR,
    holiday_date DATE
);

INSERT INTO preston_holiday (holiday, holiday_date) VALUES
('New Years Day', TO_DATE('01/01/2025', 'MM/DD/YYYY')),
('President''s Day', TO_DATE('02/19/2024', 'MM/DD/YYYY')),
('Good Friday', TO_DATE('03/29/2024', 'MM/DD/YYYY')),
('Memorial Day', TO_DATE('05/27/2024', 'MM/DD/YYYY')),
('Independence Day', TO_DATE('07/04/2024', 'MM/DD/YYYY')),
('Day after Independence', TO_DATE('07/05/2024', 'MM/DD/YYYY')),
('Labor Day', TO_DATE('09/02/2024', 'MM/DD/YYYY')),
('Columbus Day', TO_DATE('10/14/2024', 'MM/DD/YYYY')),
('Thanksgiving', TO_DATE('11/28/2024', 'MM/DD/YYYY')),
('Thanksgiving', TO_DATE('11/29/2024', 'MM/DD/YYYY')),
('Christmas', TO_DATE('12/25/2024', 'MM/DD/YYYY'));
