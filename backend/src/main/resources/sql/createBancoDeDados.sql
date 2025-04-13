CREATE TABLE "candidatos"
(
    "id"              INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "nome"            varchar(255)        NOT NULL,
    "cpf"             varchar(14) UNIQUE  NOT NULL,
    "email"           varchar(255) UNIQUE NOT NULL,
    "descricao"       text                NOT NULL,
    "data_nascimento" date                NOT NULL,
    "linkedin_link"   varchar(255)        NOT NULL,
    "senha"           varchar(255)        NOT NULL
);

CREATE TABLE "enderecos_candidatos"
(
    "id"           INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "id_candidato" integer      NOT NULL,
    "cidade"       varchar(100) NOT NULL,
    "estado"       varchar(100) NOT NULL,
    "pais"         varchar(100) NOT NULL,
    "cep"          varchar(20)  NOT NULL,
    FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id") ON DELETE CASCADE
);

CREATE TABLE "empresas"
(
    "id"            INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "nome"          varchar(255) UNIQUE NOT NULL,
    "cnpj"          varchar(18) UNIQUE  NOT NULL,
    "email"         varchar(255) UNIQUE NOT NULL,
    "descricao"     text                NOT NULL,
    "linkedin_link" varchar(255)        NOT NULL,
    "senha"         varchar(255)        NOT NULL
);

CREATE TABLE "enderecos_empresas"
(
    "id"         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "id_empresa" integer      NOT NULL,
    "cidade"     varchar(100) NOT NULL,
    "estado"     varchar(100) NOT NULL,
    "pais"       varchar(100) NOT NULL,
    "cep"        varchar(20)  NOT NULL,
    FOREIGN KEY ("id_empresa") REFERENCES "empresas" ("id") ON DELETE CASCADE
);

CREATE TABLE "vagas"
(
    "id"         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "id_empresa" integer      NOT NULL,
    "titulo"     varchar(255) NOT NULL,
    "descricao"  text         NOT NULL,
    FOREIGN KEY ("id_empresa") REFERENCES "empresas" ("id") ON DELETE CASCADE
);

CREATE TABLE "competencias"
(
    "id"   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "nome" varchar(100) UNIQUE NOT NULL
);

CREATE TABLE "candidato_competencia"
(
    "id_candidato"   integer,
    "id_competencia" integer,
    PRIMARY KEY ("id_candidato", "id_competencia"),
    FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_competencia") REFERENCES "competencias" ("id") ON DELETE CASCADE
);

CREATE TABLE "vaga_competencia"
(
    "id_vaga"        integer,
    "id_competencia" integer,
    PRIMARY KEY ("id_vaga", "id_competencia"),
    FOREIGN KEY ("id_vaga") REFERENCES "vagas" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_competencia") REFERENCES "competencias" ("id") ON DELETE CASCADE
);

CREATE TABLE "curtidas_candidato"
(
    "id_candidato" integer,
    "id_vaga"      integer,
    "data_curtida" timestamp NOT NULL,
    PRIMARY KEY ("id_candidato", "id_vaga"),
    FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_vaga") REFERENCES "vagas" ("id") ON DELETE CASCADE
);

CREATE TABLE "curtidas_empresa"
(
    "id_empresa"   integer,
    "id_candidato" integer,
    "data_curtida" timestamp NOT NULL,
    PRIMARY KEY ("id_empresa", "id_candidato"),
    FOREIGN KEY ("id_empresa") REFERENCES "empresas" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id") ON DELETE CASCADE
);

CREATE TABLE "matches"
(
    "id_empresa"   integer,
    "id_candidato" integer,
    PRIMARY KEY ("id_empresa", "id_candidato"),
    FOREIGN KEY ("id_empresa") REFERENCES "empresas" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id") ON DELETE CASCADE
);

-- Inserts

INSERT INTO candidatos (nome, cpf, email, descricao, data_nascimento, linkedin_link, senha)
VALUES ('Alex', '07101101277', 'alex.oliveiramartins05@gmail.com', 'vibe coder open to work', '2005-03-04',
        'https://www.linkedin.com/in/alexoliveiramartins/', 'coxinha123'),

       ('Colin Galen', '07654302277', 'colingalen2657@gmail.com', 'international grandmaster', '2003-03-02',
        'https://www.linkedin.com/in/colingalen/', 'dfsbfs'),

       ('Gennady Korotkevich', '07145312477', 'tourist@gmail.com', 'Programador competitivo', '2000-03-02',
        'https://www.linkedin.com/in/tourist/', '1234567890'),

       ('Benjamin Qi', '07101781277', 'benqi@gmail.com', 'Analista Quant', '2002-03-02',
        'https://www.linkedin.com/in/benjaminqi/', 'securepass'),

       ('Maria Silva', '07234567890', 'maria.silva@example.com', 'New candidate ready to make a difference',
        '2001-05-15', 'https://www.linkedin.com/in/mariasilva/', 'password123');

INSERT INTO enderecos_candidatos (id_candidato, cidade, estado, pais, cep)
VALUES ((SELECT id FROM candidatos WHERE email = 'alex.oliveiramartins05@gmail.com'), 'Goianira', 'GO', 'BR',
        '74211-020'),
       ((SELECT id FROM candidatos WHERE email = 'colingalen2657@gmail.com'), 'São Paulo', 'SP', 'BR', '04795-100'),
       ((SELECT id FROM candidatos WHERE email = 'tourist@gmail.com'), 'Corumba de Goias', 'GO', 'BR', '13225-120'),
       ((SELECT id FROM candidatos WHERE email = 'benqi@gmail.com'), 'Ubatuba', 'SP', 'BR', '25713-123'),
       ((SELECT id FROM candidatos WHERE email = 'maria.silva@example.com'), 'Belo Horizonte', 'MG', 'BR', '30100-000');

INSERT INTO empresas (nome, cnpj, email, descricao, linkedin_link, senha)
VALUES ('Google', '33.123.456/0001-99', 'contact@google.com', 'Mecanismo de buscas no1',
        'https://www.linkedin.com/in/google/', 'google123'),

       ('Microsoft', '12.987.654/0001-88', 'contact@microsoft.com', 'Developers Developers Developers',
        'https://www.linkedin.com/in/microsoft/', 'microsoft123'),

       ('Nubank', '44.567.890/0001-77', 'contato@nubank.com.br', 'https://www.linkedin.com/in/nubank/', 'Juros Altos',
        'nubank123'),

       ('SpaceX', '55.321.654/0001-66', 'contact@spacex.com', 'Carros eletricos e foguetes',
        'https://www.linkedin.com/in/spacex/', 'spacex123'),

       ('Amazon', '77.888.999/0001-55', 'contact@amazon.com', 'E-commerce',
        'https://www.linkedin.com/in/amazon/', 'amazon123');

INSERT INTO enderecos_empresas (id_empresa, cidade, estado, pais, cep)
VALUES ((SELECT id FROM empresas WHERE email = 'contact@google.com'), 'São Paulo', 'SP', 'BR', '04795-100'),
       ((SELECT id FROM empresas WHERE email = 'contact@microsoft.com'), 'São Paulo', 'SP', 'BR', '04538-132'),
       ((SELECT id FROM empresas WHERE email = 'contato@nubank.com.br'), 'São Paulo', 'SP', 'BR', '05423-040'),
       ((SELECT id FROM empresas WHERE email = 'contact@spacex.com'), 'Curitiba', 'PR', 'BR', '80540-900'),
       ((SELECT id FROM empresas WHERE email = 'contact@amazon.com'), 'Rio de Janeiro', 'RJ', 'BR', '20000-000');

INSERT
INTO vagas (id_empresa, titulo, descricao)
VALUES ((SELECT id FROM empresas WHERE nome = 'Google'), 'Engenheiro de Software',
        'Desenvolver e manter aplicações web escaláveis usando tecnologias modernas no Google Brasil.'),
       ((SELECT id FROM empresas WHERE nome = 'Google'), 'Cientista de Dados',
        'Analisar grandes volumes de dados e criar modelos de machine learning para melhorar os algoritmos de busca do Google Brasil.'),
       ((SELECT id FROM empresas WHERE nome = 'Microsoft'), 'Arquiteto de Soluções em Nuvem',
        'Projetar e implementar soluções em nuvem utilizando o Azure para clientes corporativos na Microsoft Brasil.'),
       ((SELECT id FROM empresas WHERE nome = 'Microsoft'), 'Analista de Cibersegurança',
        'Garantir a segurança da infraestrutura e dos serviços em nuvem da Microsoft na América Latina.'),
       ((SELECT id FROM empresas WHERE nome = 'Nubank'), 'Desenvolvedor Backend',
        'Trabalhar nos sistemas financeiros do Nubank, garantindo escalabilidade e alta performance para milhões de clientes.'),
       ((SELECT id FROM empresas WHERE nome = 'Nubank'), 'Gerente de Produto',
        'Liderar equipes multidisciplinares para criar e lançar produtos financeiros inovadores no Nubank.'),
       ((SELECT id FROM empresas WHERE nome = 'SpaceX'), 'Engenheiro Aeroespacial',
        'Contribuir para o design e desenvolvimento da nova geração de foguetes e espaçonaves na SpaceX Brasil.'),
       ((SELECT id FROM empresas WHERE nome = 'SpaceX'), 'Gerente de Cadeia de Suprimentos',
        'Otimizar a logística e a cadeia de suprimentos para as operações da SpaceX na América do Sul.'),
       ((SELECT id FROM empresas WHERE nome = 'Amazon'), 'Logistics Coordinator',
        'Otimizar o sistema de E-Commerce e Otimizar Servidores AWS');

INSERT INTO curtidas_candidato(id_candidato, id_vaga, data_curtida)
VALUES (1, 1, CURRENT_DATE),
       (1, 4, CURRENT_DATE),
       (2, 2, CURRENT_DATE),
       (3, 2, CURRENT_DATE),
       (4, 8, CURRENT_DATE),
       (5, 4, CURRENT_DATE),
       (5, 3, CURRENT_DATE);

INSERT INTO curtidas_empresa(id_empresa, id_candidato, data_curtida)
VALUES (1, 5, CURRENT_DATE),
       (2, 2, CURRENT_DATE),
       (3, 1, CURRENT_DATE),
       (3, 5, CURRENT_DATE),
       (4, 4, CURRENT_DATE),
       (2, 5, CURRENT_DATE),
       (5, 4, CURRENT_DATE);

INSERT INTO matches(id_empresa, id_candidato)
VALUES (4, 4),
       (2, 5);

SELECT *
FROM vagas;