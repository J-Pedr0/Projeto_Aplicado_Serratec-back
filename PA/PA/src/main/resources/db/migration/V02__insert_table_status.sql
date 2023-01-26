insert into status (nome_status, status_obrigatorio, status_manutencao)
values
('Livre',
false,
false
),

('Em Manutenção',
false,
true
),

('Emprestado',
true,
false
),

('Em Análise',
false,
true
),

('Inativo',
false,
false
);