-- Create the categories table
CREATE TABLE categories (
                            id VARCHAR(255) PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            parent_category_id VARCHAR(255),
                            CONSTRAINT fk_parent_category
                                FOREIGN KEY (parent_category_id)
                                    REFERENCES categories(id)
                                    ON DELETE SET NULL
);

-- Root categories
INSERT INTO categories (id, name, parent_category_id) VALUES ('electronics', 'Electronics', NULL);

-- Subcategories of Electronics
INSERT INTO categories (id, name, parent_category_id) VALUES ('computers', 'Computers', 'electronics');
INSERT INTO categories (id, name, parent_category_id) VALUES ('laptops', 'Laptops', 'computers');
INSERT INTO categories (id, name, parent_category_id) VALUES ('desktops', 'Desktops', 'computers');
INSERT INTO categories (id, name, parent_category_id) VALUES ('camera', 'Camera', 'electronics');
INSERT INTO categories (id, name, parent_category_id) VALUES ('smartphones', 'Smartphones', 'electronics');

-- Subcategories of Accessories (under Electronics)
INSERT INTO categories (id, name, parent_category_id) VALUES ('accessories', 'Accessories', 'electronics');
INSERT INTO categories (id, name, parent_category_id) VALUES ('mouse', 'Mouse', 'accessories');
INSERT INTO categories (id, name, parent_category_id) VALUES ('speaker', 'Speaker', 'accessories');
INSERT INTO categories (id, name, parent_category_id) VALUES ('gaming', 'Gaming', 'accessories');
INSERT INTO categories (id, name, parent_category_id) VALUES ('monitor', 'Monitor', 'accessories');
INSERT INTO categories (id, name, parent_category_id) VALUES ('keyboard', 'Keyboard', 'accessories');
INSERT INTO categories (id, name, parent_category_id) VALUES ('network', 'Network', 'accessories');
INSERT INTO categories (id, name, parent_category_id) VALUES ('storage', 'Storage', 'accessories');

-- Subcategories of Mouse (under Accessories)
INSERT INTO categories (id, name, parent_category_id) VALUES ('wiredMouse', 'Wired Mouse', 'mouse');
INSERT INTO categories (id, name, parent_category_id) VALUES ('bluetoothMouse', 'Bluetooth Mouse', 'mouse');

-- Subcategories of Speaker (under Accessories)
INSERT INTO categories (id, name, parent_category_id) VALUES ('headset', 'Headset', 'speaker');
INSERT INTO categories (id, name, parent_category_id) VALUES ('headphone', 'Headphone', 'speaker');

-- Subcategories of smartphones
INSERT INTO categories (id, name, parent_category_id) VALUES ('smartwatch', 'Smart watch', 'smartphones');
INSERT INTO categories (id, name, parent_category_id) VALUES ('android', 'Android Smart Phone', 'smartphones');
INSERT INTO categories (id, name, parent_category_id) VALUES ('iphone', 'Apple IPhone', 'smartphones');
