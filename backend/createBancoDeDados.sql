CREATE TABLE "candidatos"
(
    "id"              SERIAL PRIMARY KEY,
    "nome"            varchar(255)        NOT NULL,
    "cpf"             varchar(14) UNIQUE  NOT NULL,
    "email"           varchar(255) UNIQUE NOT NULL,
    "descricao"       text                NOT NULL,
    "data_nascimento" date                NOT NULL,
    "linkedin_link"   varchar(255)        NOT NULL,
    "endereco_id"     int                 NOT NULL,
    "senha"           varchar(255)        NOT NULL
);

CREATE TABLE "enderecos_candidatos"
(
    "id"         SERIAL PRIMARY KEY,
    "id_usuario" int          NOT NULL,
    "cidade"     varchar(100) NOT NULL,
    "estado"     varchar(100) NOT NULL,
    "pais"       varchar(100) NOT NULL,
    "cep"        varchar(20)  NOT NULL
);

CREATE TABLE "empresas"
(
    "id"            SERIAL PRIMARY KEY,
    "nome"          varchar(255)        NOT NULL,
    "cnpj"          varchar(18) UNIQUE  NOT NULL,
    "email"         varchar(255) UNIQUE NOT NULL,
    "descricao"     text                NOT NULL,
    "linkedin_link" varchar(255)        NOT NULL,
    "endereco_id"   int                 NOT NULL,
    "senha"         varchar(255)        NOT NULL
);

CREATE TABLE "enderecos_empresas"
(
    "id"         SERIAL PRIMARY KEY,
    "id_usuario" int          NOT NULL,
    "cidade"     varchar(100) NOT NULL,
    "estado"     varchar(100) NOT NULL,
    "pais"       varchar(100) NOT NULL,
    "cep"        varchar(20)  NOT NULL
);

CREATE TABLE "vagas"
(
    "id"         SERIAL PRIMARY KEY,
    "id_empresa" int          NOT NULL,
    "titulo"     varchar(255) NOT NULL,
    "descricao"  text         NOT NULL
);

CREATE TABLE "competencias"
(
    "id"   SERIAL PRIMARY KEY,
    "nome" varchar(100) UNIQUE NOT NULL
);

CREATE TABLE "candidato_competencia"
(
    "id_candidato"   int NOT NULL,
    "id_competencia" int NOT NULL,
    PRIMARY KEY ("id_candidato", "id_competencia")
);

CREATE TABLE "vaga_competencia"
(
    "id_vaga"        int NOT NULL,
    "id_competencia" int NOT NULL,
    PRIMARY KEY ("id_vaga", "id_competencia")
);

CREATE TABLE "curtidas_candidato"
(
    "id"           SERIAL PRIMARY KEY,
    "id_candidato" int       NOT NULL,
    "id_vaga"      int       NOT NULL,
    "data_curtida" timestamp NOT NULL
);

CREATE TABLE "curtidas_empresa"
(
    "id"           SERIAL PRIMARY KEY,
    "id_empresa"   int       NOT NULL,
    "id_candidato" int       NOT NULL,
    "data_curtida" timestamp NOT NULL
);

ALTER TABLE "candidatos"
    ADD FOREIGN KEY ("endereco_id") REFERENCES "enderecos_candidatos" ("id");
ALTER TABLE "empresas"
    ADD FOREIGN KEY ("endereco_id") REFERENCES "enderecos_empresas" ("id");
ALTER TABLE "vagas"
    ADD FOREIGN KEY ("id_empresa") REFERENCES "empresas" ("id");
ALTER TABLE "candidato_competencia"
    ADD FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id");
ALTER TABLE "candidato_competencia"
    ADD FOREIGN KEY ("id_competencia") REFERENCES "competencias" ("id");
ALTER TABLE "vaga_competencia"
    ADD FOREIGN KEY ("id_vaga") REFERENCES "vagas" ("id");
ALTER TABLE "vaga_competencia"
    ADD FOREIGN KEY ("id_competencia") REFERENCES "competencias" ("id");
ALTER TABLE "curtidas_candidato"
    ADD FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id");
ALTER TABLE "curtidas_candidato"
    ADD FOREIGN KEY ("id_vaga") REFERENCES "vagas" ("id");
ALTER TABLE "curtidas_empresa"
    ADD FOREIGN KEY ("id_empresa") REFERENCES "empresas" ("id");
ALTER TABLE "curtidas_empresa"
    ADD FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id");

INSERT INTO enderecos_candidatos (id_usuario, cidade, estado, pais, cep)
VALUES (1, 'Goianira', 'GO', 'BR', '74211-020'),
       (2, 'São Paulo', 'SP', 'BR', '04795-100'),
       (3, 'Corumba de Goias', 'GO', 'BR', '13225-120'),
       (4, 'Ubatuba', 'SP', 'BR', '25713-123'),
       (5, 'Belo Horizonte', 'MG', 'BR', '30100-000');

INSERT INTO candidatos (nome, cpf, email, descricao, data_nascimento, linkedin_link, senha, endereco_id)
VALUES ('Alex', '07101101277', 'alex.oliveiramartins05@gmail.com', 'vibe coder open to work', '2005-03-04',
        'https://www.linkedin.com/in/alexoliveiramartins/', 'coxinha123', 1),
       ('Colin Galen', '07654302277', 'colingalen2657@gmail.com', 'international grandmaster', '2003-03-02',
        'https://www.linkedin.com/in/colingalen/', 'dfsbfs', 2),
       ('Gennady Korotkevich', '07145312477', 'tourist@gmail.com', 'Programador competitivo', '2000-03-02',
        'https://www.linkedin.com/in/tourist/', '1234567890', 3),
       ('Benjamin Qi', '07101781277', 'benqi@gmail.com', 'Analista Quant', '2002-03-02',
        'https://www.linkedin.com/in/bqi343/', 'minecraft123', 4),
       ('Maria Silva', '07234567890', 'maria.silva@example.com', 'New candidate ready to make a difference',
        '2001-05-15', 'https://www.linkedin.com/in/mariasilva/', 'password123', 5);

INSERT INTO enderecos_empresas (id_usuario, cidade, estado, pais, cep)
VALUES (1, 'São Paulo', 'SP', 'BR', '04795-100'),
       (2, 'São Paulo', 'SP', 'BR', '04538-132'),
       (3, 'São Paulo', 'SP', 'BR', '05423-040'),
       (4, 'Curitiba', 'PR', 'BR', '80540-900'),
       (5, 'Rio de Janeiro', 'RJ', 'BR', '20000-000');

INSERT INTO empresas (nome, cnpj, email, descricao, linkedin_link, senha, endereco_id)
VALUES ('Google', '33.123.456/0001-99', 'contact@google.com', 'Mecanismo de buscas no1',
        'https://www.linkedin.com/in/google/', 'google123', 1),
       ('Microsoft', '12.987.654/0001-88', 'contact@microsoft.com', 'Developers Developers Developers',
        'https://www.linkedin.com/in/microsoft/', 'microsoft123', 2),
       ('Nubank', '44.567.890/0001-77', 'contato@nubank.com.br', 'https://www.linkedin.com/in/nubank/', 'Juros Altos',
        'nubank123', 3),
       ('SpaceX', '55.321.654/0001-66', 'contact@spacex.com', 'Carros eletricos e foguetes',
        'https://www.linkedin.com/in/spacex/', 'spacex123', 4),
       ('Amazon', '77.888.999/0001-55', 'contact@amazon.com', 'E-commerce', 'https://www.linkedin.com/in/amazon/',
        'amazon123', 5);

INSERT INTO vagas (id_empresa, titulo, descricao)
VALUES (1, 'Engenheiro de Software',
        'Desenvolver e manter aplicações web escaláveis usando tecnologias modernas no Google Brasil.'),
       (1, 'Cientista de Dados',
        'Analisar grandes volumes de dados e criar modelos de machine learning para melhorar os algoritmos de busca do Google Brasil.'),
       (2, 'Arquiteto de Soluções em Nuvem',
        'Projetar e implementar soluções em nuvem utilizando o Azure para clientes corporativos na Microsoft Brasil.'),
       (2, 'Analista de Cibersegurança',
        'Garantir a segurança da infraestrutura e dos serviços em nuvem da Microsoft na América Latina.'),
       (3, 'Desenvolvedor Backend',
        'Trabalhar nos sistemas financeiros do Nubank, garantindo escalabilidade e alta performance para milhões de clientes.'),
       (3, 'Gerente de Produto',
        'Liderar equipes multidisciplinares para criar e lançar produtos financeiros inovadores no Nubank.'),
       (4, 'Engenheiro Aeroespacial',
        'Contribuir para o design e desenvolvimento da nova geração de foguetes e espaçonaves na SpaceX Brasil.'),
       (4, 'Gerente de Cadeia de Suprimentos',
        'Otimizar a logística e a cadeia de suprimentos para as operações da SpaceX na América do Sul.'),
       (5, 'Logistics Coordinator', 'Otimizar o sistema de E-Commerce e Otimizar Servidores AWS');

SELECT *
FROM vagas;
