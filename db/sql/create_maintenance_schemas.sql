CREATE TABLE IF NOT EXISTS category (
  id tinyint PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS request (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  owner_id bigint NOT NULL,
  resident_id bigint NOT NULL,
  property_id bigint NOT NULL,
  category_id smallint NOT NULL,
  description varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  status varchar(15) NOT NULL DEFAULT 'IN_PROGRESS',
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY owner_id_idx (owner_id),
  KEY resident_id_idx (resident_id),
  KEY property_id_idx (property_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO category(name) values ('Appliance');
INSERT INTO category(name) values ('Doors/Keys');
INSERT INTO category(name) values ('Electrical');
INSERT INTO category(name) values ('Exterior');
INSERT INTO category(name) values ('Flooring/Carpet');
INSERT INTO category(name) values ('HVAC');
INSERT INTO category(name) values ('Inspection');
INSERT INTO category(name) values ('Landscape');
INSERT INTO category(name) values ('Pest Control');
INSERT INTO category(name) values ('Plumbing/Leaks');
INSERT INTO category(name) values ('Windows');