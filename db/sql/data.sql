insert into customers (email, pwd) VALUES
  ('account@jairogalvez.com', 'to_be_encoded'),
  ('cards@jairogalvez.com', 'to_be_encoded'),
  ('loans@jairogalvez.com', 'to_be_encoded'),
  ('balance@jairogalvez.com', 'to_be_encoded');

insert into roles (role_name, description, id_customer) VALUES
  ('ROLE_ADMIN', 'cant view account endpoint', 1),
  ('ROLE_ADMIN', 'cant view cards endpoint', 2),
  ('ROLE_USER', 'cant view loans endpoint', 3),
  ('ROLE_USER', 'cant view balance endpoint', 4);

insert into partners(
    client_id,
    client_name,
    client_secret,
    scopes,
    grant_types,
    authentication_methods,
    redirect_uri,
    redirect_uri_logout
)
values ('company',
            'debuggeando company',
            'secret',
            'read,write',
            'authorization_code,refresh_token',
            'client_secret_basic,client_secret_jwt',
            'https://oauthdebugger.com/debug',
            'https://springone.io/authorized')

