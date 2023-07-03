INSERT INTO category(id, name) values (1, 'Appliance');
INSERT INTO category(id, name) values (2, 'Doors/Keys');
INSERT INTO category(id, name) values (3, 'Electrical');
INSERT INTO category(id, name) values (4, 'Exterior');
INSERT INTO category(id, name) values (5, 'Flooring/Carpet');
INSERT INTO category(id, name) values (6, 'HVAC');
INSERT INTO category(id, name) values (7, 'Inspection');
INSERT INTO category(id, name) values (8, 'Landscape');
INSERT INTO category(id, name) values (9, 'Pest Control');
INSERT INTO category(id, name) values (10, 'Plumbing/Leaks');
INSERT INTO category(id, name) values (11, 'Windows');

INSERT INTO maintenance_request(id, owner_id, resident_id, property_id, category_id, description, status) values (100, 1, 1, 1, 1, 'Windows', 'IN_PROGRESS');
INSERT INTO maintenance_request(id, owner_id, resident_id, property_id, category_id, description, status) values (200, 2, 2, 2, 2, 'Windows', 'COMPLETED');