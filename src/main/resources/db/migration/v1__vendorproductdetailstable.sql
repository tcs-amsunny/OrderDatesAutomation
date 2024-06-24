CREATE TABLE vendor_product_details (
    id SERIAL PRIMARY KEY,
	vendor_name varchar,
    product_desc varchar,
manufacturing_code varchar,
manufacturing_total_biz_days bigint,
replacement_manufacturing_code varchar,
replacement_manufacturing_total_biz_days bigint
   );


INSERT INTO vendor_product_details (vendor_name, product_desc, manufacturing_code, manufacturing_total_biz_days, replacement_manufacturing_code, replacement_manufacturing_total_biz_days) VALUES
('Everything Else', 'slab (smooth)', 'VC20', 20, 'RVC3', 3),
('JB Cutting', '5-Piece Fronts with Matching Center Panel (solid)', 'VC16', 16, 'RVC8', 8),
('Brushy Creek', 'Slim Shaker 3Dl 1 Piece Front', 'VC24', 24, 'RVC16', 16),
('JB Cutting & House of Glass', '5-Piece Doors/Drawers with Mirror Insert', 'VC22', 22, 'RVC14', 14),
('JB Cutting', '5-Piece Doors/Drawers with Glass Insert', 'VC35', 35, 'RVC27', 27),
('JB Cutting', '5-Piece Doors/Drawers with Mesh Insert', 'VC36', 36, 'RVC28', 28),
('JB Cutting', '5-Piece Doors/Drawers with REEDED/FLUTED Glass Insert', 'VC36', 36, 'RVC28', 28),
('Element Designs', 'Aluminum Frame & Glass/Mirror Doors/Drawers', 'VC40', 40, 'RVC32', 32),
('Element Designs', 'Backpainted Glass Tops', 'VC30', 30, 'RVC22', 22),
('Element Designs', 'elusion Glass Tops', 'VC35', 35, 'RVC27', 27),
('Element Designs', 'Backpainted Magnetic Markerboad', 'VC30', 30, 'RVC22', 22),
('EcoDomo', 'RLV Leather Door/Drawer Fronts/Countertop (Colors: Shagreen, Crocodile, Buffalo)', 'VC30', 30, 'RVC22', 22),
('Oakbrook Glass', 'Glass Shelves', 'VC17', 17, 'RVC9', 9),
('Element/Custom', 'CUSTOM COLOR Aluminum Frame and Glass Doors - Match Approval must be received (2 weeks), then 20 business days lead (manuf)', 'VC22', 22, 'RVC22', 22),
('Element/Custom', 'CUSTOM COLOR Backpainted Glass Tops - Match Approval Must be received (2 weeks), then 20 business days lead (manuf)', NULL, NULL, 'RVC22', 22);