DELETE FROM user_master;
INSERT INTO user_master(id,email,first_name,last_name,password,user_name) VALUES (1,'vkotecha91@gmail.com','Vishal','Kotecha','$2a$10$5ypR7XdiKzkWLBEPe2LRY.UFL2wHPcBFz0dgc4sgMxfyGB1CyxE7u','vishal');

DELETE FROM product;
INSERT INTO product (id, name) VALUES ('1', 'Shoes');
INSERT INTO product (id, name) VALUES ('2', 'Clothes');
