create table ativo
(id_ativo SERIAL primary key, 
patrimonio varchar(10) not null unique, 
nro_serie varchar(20) not null unique, 
descricao varchar(250) not null,
data_aquisicao date,
data_garantia date,
status_ativo Boolean,
marca varchar(250) not null
);

create table status
(id_status SERIAL primary key, 
nome_status varchar(250) not null,
status_obrigatorio Boolean,
status_manutencao Boolean
);

create table movimento
(id_movimento SERIAL primary key,
data_inicio date,
data_fim date,
id_usuario varchar(50),
full_name varchar(255),
comentario varchar(600) not null,
status_id int references status(id_status),
ativo_id int references ativo(id_ativo)
);