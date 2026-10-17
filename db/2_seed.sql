INSERT INTO users (id, name, email) VALUES
    (1, 'Иван Петров',      'ivan@example.com'),
    (2, 'Мария Сидорова',   'maria@example.com'),
    (3, 'Алексей Кузнецов', 'alexey@example.com'),
    (4, 'Ольга Иванова',    'olga@example.com');

INSERT INTO collectible_items (id, description, owner_id, item_condition) VALUES
    (1, 'Серебряная монета 1924 года',                  1, 'отличное'),
    (2, 'Почтовая марка «Чёрный пенни», 1840',          2, 'хорошее'),
    (3, 'Антикварные карманные часы',                   3, 'требуют реставрации'),
    (4, 'Коллекционная фигурка в оригинальной коробке', 1, 'новое');

INSERT INTO auction_lots (id, item_id, start_price, step, start_time, end_time, status) VALUES
    (1, 1, 1000.00, 100.00, UTC_TIMESTAMP(6) - INTERVAL 1 HOUR,  UTC_TIMESTAMP(6) + INTERVAL 7 DAY, 'ACTIVE'),
    (2, 2, 5000.00, 250.00, UTC_TIMESTAMP(6) - INTERVAL 10 DAY, UTC_TIMESTAMP(6) - INTERVAL 3 DAY, 'FINISHED');

INSERT INTO bids (id, lot_id, bidder_id, amount, placed_at) VALUES
    (1, 1, 2, 1000.00, UTC_TIMESTAMP(6) - INTERVAL 50 MINUTE),
    (2, 1, 3, 1100.00, UTC_TIMESTAMP(6) - INTERVAL 30 MINUTE),
    (3, 2, 1, 5000.00, UTC_TIMESTAMP(6) - INTERVAL 9 DAY),
    (4, 2, 3, 5250.00, UTC_TIMESTAMP(6) - INTERVAL 4 DAY);

INSERT INTO auction_deals (id, lot_id, winning_bid_id, amount, transfer_status) VALUES
    (1, 2, 4, 5250.00, 'PENDING');
