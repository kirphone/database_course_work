CREATE INDEX account_email_index ON account USING hash (email);
CREATE INDEX shop_company_name ON shop USING hash (company_name);
CREATE INDEX product_shop_product_id ON product_shop USING hash (product_id);
CREATE INDEX product_shop_shop_id ON product_shop USING hash (shop_id);