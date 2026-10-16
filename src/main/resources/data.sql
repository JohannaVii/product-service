INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Necklace-1', 'En beskrivning till Necklace-1', 100.00, 10, 'NECKLACES', '/images/necklaces/necklace-1.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Necklace-1'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Necklace-2', 'En beskrivning till Necklace-2', 200.00, 20, 'NECKLACES', '/images/necklaces/necklace-2.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Necklace-2'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Necklace-3', 'En beskrivning till Necklace-3', 300.00, 30, 'NECKLACES', '/images/necklaces/necklace-3.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Necklace-3'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Bracelet-1', 'En beskrivning till Bracelet-1', 100.00, 10, 'BRACELETS', '/images/bracelets/bracelet-1.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Bracelet-1'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Bracelet-2', 'En beskrivning till Bracelet-2', 200.00, 20, 'BRACELETS', '/images/bracelets/bracelet-2.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Bracelet-2'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Bracelet-3', 'En beskrivning till Bracelet-3', 300.00, 30, 'BRACELETS', '/images/bracelets/bracelet-3.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Bracelet-3'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Earrings-1', 'En beskrivning till Earrings-1', 100.00, 10, 'EARRINGS', '/images/earrings/earrings-1.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Earrings-1'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Earrings-2', 'En beskrivning till Earrings-2', 200.00, 20, 'EARRINGS', '/images/earrings/earrings-2.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Earrings-2'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Earrings-3', 'En beskrivning till Earrings-3', 300.00, 30, 'EARRINGS', '/images/earrings/earrings-3.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Earrings-3'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Ring-1', 'En beskrivning till Ring-1', 100.00, 10, 'RINGS', '/images/rings/ring-1.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Ring-1'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Ring-2', 'En beskrivning till Ring-2', 200.00, 20, 'RINGS', '/images/rings/ring-2.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Ring-2'
);

INSERT INTO products (name, description, price, stock, category, image_url)
SELECT 'Ring-3', 'En beskrivning till Ring-3', 300.00, 30, 'RINGS', '/images/rings/ring-3.avif'
WHERE NOT EXISTS (
    SELECT 1 FROM products WHERE name = 'Ring-3'
);