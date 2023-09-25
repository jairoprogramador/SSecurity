insert into customers (email, pwd) VALUES
  ('account@jairogalvez.com', 'to_be_encoded'),
  ('cards@jairogalvez.com', 'to_be_encoded'),
  ('loans@jairogalvez.com', 'to_be_encoded'),
  ('balance@jairogalvez.com', 'to_be_encoded');

insert into roles (role_name, description, id_customer) VALUES
  ('VIEW_ACCOUNT', 'cant view account endpoint', 1),
  ('VIEW_CARDS', 'cant view cards endpoint', 2),
  ('VIEW_LOANS', 'cant view loans endpoint', 3),
  ('VIEW_BALANCE', 'cant view balance endpoint', 4);

