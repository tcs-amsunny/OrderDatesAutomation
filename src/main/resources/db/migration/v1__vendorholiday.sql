CREATE TABLE vendor_holiday (
    id SERIAL PRIMARY KEY NOT NULL AUTO_INCREMENT,
    holiday VARCHAR,
    holiday_date DATE
);

INSERT INTO vendor_holiday (holiday, holiday_date) VALUES
('New Years Day', '2025-01-01'),
('President''s Day', '2024-02-19'),
('Good Friday', '2024-03-29'),
('Memorial Day', '2024-05-27'),
('Independence Day', '2024-07-04'),
('Day after Independence', '2024-07-05'),
('Labor Day', '2024-09-02'),
('Columbus Day', '2024-10-14'),
('Thanksgiving', '2024-11-28'),
('Thanksgiving', '2024-11-29'),
('Christmas', '2024-12-25'),
('Christmas', '2023-12-26'),
('Christmas', '2023-12-27'),
('Christmas', '2023-12-28'),
('Christmas', '2023-12-29');