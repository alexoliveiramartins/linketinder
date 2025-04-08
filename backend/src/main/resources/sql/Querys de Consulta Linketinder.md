Seleciona todas as empresas sem vagas registradas

```sql
SELECT e.nome
FROM empresas as e
         LEFT JOIN vagas as v ON e.id = v.id_empresa
WHERE v.id_empresa IS NULL;
```

Selecionar nome do candidato/vaga curtida:

```sql
SELECT c.nome, v.titulo
FROM candidatos AS c
         JOIN curtidas_candidato AS curt ON curt.id_candidato = c.id
         JOIN vagas AS v ON curt.id_vaga = v.id;
```

Selecionar nome da empresa/usuario curtido

```sql
SELECT e.nome, c.nome
FROM candidatos AS c
         JOIN curtidas_empresa as curt ON curt.id_candidato = c.id
         JOIN empresas AS e ON curt.id_empresa = e.id;
```

Selecionar Matches sem a tabela de matches:

```sql
SELECT DISTINCT c.nome AS nome_candidato, e.nome AS nome_empresa
FROM candidatos c
         JOIN curtidas_candidato cc ON cc.id_candidato = c.id
         JOIN vagas v ON cc.id_vaga = v.id
         JOIN curtidas_empresa ce ON ce.id_candidato = c.id AND ce.id_empresa = v.id_empresa
         JOIN empresas e ON e.id = v.id_empresa;
```