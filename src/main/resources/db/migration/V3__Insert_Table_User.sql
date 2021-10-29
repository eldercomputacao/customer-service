INSERT INTO "tb_user" ("id", "authorities", "name", "password", "username") VALUES
(1,	'ROLE_ADMIN,ROLE_USER',	'Root',	'{bcrypt}$2a$10$Izp2HEOra4Z0iCX3.V5qHeeW.UvgOHSPfn.wLfrLtvTuZyRBYVn1G',	'root'),
(2,	'ROLE_USER',	'User',	'{bcrypt}$2a$10$Izp2HEOra4Z0iCX3.V5qHeeW.UvgOHSPfn.wLfrLtvTuZyRBYVn1G',	'user');